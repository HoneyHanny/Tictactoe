package tictactoe;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {

	public static final String TITLE = "TicTacToe";
	public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = screenSize.width;
	public static final int HEIGHT = screenSize.height;

	public static final int NONE = 0;
	public static final int X = 1;
	public static final int O = 2;

	public static State prevState;
	public static State state;
	public static Point mouseCoord;

	private Thread thread;
	private Menu menu;
	private MouseInput mouseInput;
	private Board board;
	private ImageStorage imageStorage;

	// public Sound click = new Sound("res/sounds.wav");

	public boolean quit = false;
	public boolean confirm = false;
	public boolean cancel = false;
	public boolean next = false;
	
	public Turn turn = Turn.X;
	public Winner winner = Winner.None;
	public int bgAnim = 40;
	public int players;
	public int xScore = 0;
	public int oScore = 0;
	public int round = 1;
	
	private boolean running;
	private int timer = 0;
	private Color lastColor;
	private boolean changeColor;

	public Game() {
		running = false;
		state = State.Menu;

		menu = new Menu();
		imageStorage = ImageStorage.getInstance();
		board = new Board(this, imageStorage);

		new Window(this);

		mouseInput = new MouseInput(this, menu, board);

		addKeyListener(new KeyInput());
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);

		start();
	}

	private synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	private synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		// long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
		}

		stop();
	}

	private void tick() {
		mouseCoord = MouseInfo.getPointerInfo().getLocation();

		switch (state) {
			case Menu:
				if (bgAnim < 41)
					bgAnim++;
				break;

			case Game:
				if (bgAnim > 0)
					bgAnim--;
				break;

			case ConfirmExit:
				if (bgAnim < 41)
					bgAnim++;
				break;
			
			case EndRound:
				timer++;
				if (timer == 21) {
					changeColor = true;
					timer = 0;
				} else {
					changeColor = false;
				}
				break;
		
			default:
				break;
		}
		
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setFont(new Font("Aquire", Font.PLAIN, 10));

		renderBackground(g);

		if (mouseCoord == null)
			mouseCoord = MouseInfo.getPointerInfo().getLocation();

		switch (state) {
			case Menu:
				menu.render(g);
				break;

			case Game:
				board.render(g);
				renderGameUI(g);
				break;

			case ConfirmQuit:
				board.render(g);
				renderGameUI(g);
				renderConfirmQuit(g);
				break;

			case EndRound:
				board.render(g);
				renderGameUI(g);
				break;

			case ConfirmExit:
				if (bgAnim < 41)
					bgAnim++;
				renderConfirmQuit(g);
				break;

			default:
				break;
		}

		if (state != State.Game && state != State.EndRound)
			renderForeGround(g);

		g.dispose();
		bs.show();
	}

	public void newRound() {
		round++;
		board.isBoardFull = false;
		board.isBoardClear = true;
		board.clearSections();
	}

	public void renderBackground(Graphics g) {
		g.setColor(new Color(bgAnim, bgAnim, bgAnim));
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	public void renderForeGround(Graphics g) {

		BufferedImage overlay = null;
		try {
			overlay = ImageIO.read(new File("res/overlay.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(overlay, 0, 0, WIDTH, HEIGHT, null);

	}

	public void renderConfirmQuit(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		// render overlay box
		g2d.setColor(Color.WHITE);
		g2d.drawRoundRect(416, 99, 701, 601, 50, 50);
		g2d.setColor(new Color(10, 10, 10));
		g2d.fillRoundRect(417, 100, 700, 600, 50, 50);

		g.setFont(new Font("JetBrains Mono", Font.PLAIN, 30));
		g.setColor(Color.WHITE);

		Text prompt = new Text("Are you sure you want to quit?", 0, 200);
		prompt.centerTextHorizontal(g, WIDTH);
		prompt.renderText(g);

		g.setFont(new Font("Aquire", Font.PLAIN, 20));

		Box confirmBox = new Box(0, 300, 200, 100, g);
		Box cancelBox = new Box(0, 450, 200, 100, g);
		confirmBox.centerBoxHorizontal(WIDTH);
		cancelBox.centerBoxHorizontal(WIDTH);

		Text confirmText = new Text("Confirm", 0, 200);
		Text cancelText = new Text("Cancel", 0, 450);

		Color yellow = new Color(250, 189, 47);
		if (GameUtilities.mouseOver(mouseCoord.x, mouseCoord.y, confirmBox.x, confirmBox.y, confirmBox.width, confirmBox.height)) {
			confirm = true;
			cancel = false;
			cancelText.renderCenteredTextInsideBox(g, cancelBox);
			g.setColor(yellow);
			confirmText.renderCenteredTextInsideBox(g, confirmBox);

			confirmBox.setColor(yellow);
		} else if (GameUtilities.mouseOver(mouseCoord.x, mouseCoord.y, cancelBox.x, cancelBox.y, cancelBox.width, cancelBox.height)) {
			confirm = false;
			cancel = true;
			confirmText.renderCenteredTextInsideBox(g, confirmBox);
			g.setColor(yellow);
			cancelText.renderCenteredTextInsideBox(g, cancelBox);

			cancelBox.setColor(yellow);
		} else {
			confirm = false;
			cancel = false;
			confirmText.renderCenteredTextInsideBox(g, confirmBox);
			cancelText.renderCenteredTextInsideBox(g, cancelBox);
		}

		confirmBox.drawRoundBox(20);
		cancelBox.drawRoundBox(20);
	}

	public void renderGameUI(Graphics g) {

		g.setFont(new Font("Courier New", Font.PLAIN, 30));

		Text roundText = new Text("Round: " + round, 1100, 200);
		
		g.setColor(Color.ORANGE);
		roundText.renderText(g);
		g.setColor(Color.CYAN);

		if (turn == Turn.X) {

			Text xScoreText = new Text("> Player X: " + xScore, 1050, 350);
			Text oScoreText = new Text("  Player O: " + oScore, 1050, 450);

			xScoreText.renderText(g);
			oScoreText.renderText(g);

		} else if (turn == Turn.O) {

			Text oScoreText = new Text("> Player O: " + oScore, 1050, 450);
			Text xScoreText = new Text("  Player X: " + xScore, 1050, 350);

			oScoreText.renderText(g);
			xScoreText.renderText(g);

		}

		g.setFont(new Font("Aquire", Font.PLAIN, 20));

		Box quitBox = new Box(1100, 700, 200, 100, g);
		Text quitText = new Text("Quit", quitBox.x, 700);

		if (GameUtilities.mouseOver(mouseCoord.x, mouseCoord.y, quitBox.x, quitBox.y, quitBox.width, quitBox.height)) {

			quit = true;

			g.setColor(Color.RED);
			quitText.renderCenteredTextInsideBox(g, quitBox);

			quitBox.setColor(Color.RED);

		} else {

			quit = false;

			g.setColor(Color.WHITE);
			quitText.renderCenteredTextInsideBox(g, quitBox);

			quitBox.setColor(Color.WHITE);

		}
		quitBox.drawRoundBox(20);

		// -------------------------------- tag EndRound state render
		if (state == State.EndRound) {
			Box nextBox = new Box(1100, 550, 200, 100, g, 20);
			Text nextText = new Text("Next", nextBox.x, nextBox.y);

			if (GameUtilities.mouseOver(mouseCoord.x, mouseCoord.y, nextBox.x, nextBox.y, nextBox.width, nextBox.height)) {
				nextBox.setColor(Color.GREEN);
			}

			nextBox.drawRoundBox();
			nextText.renderCenteredTextInsideBox(g, nextBox);
			BufferedImage bImage = null;
			try {
				if (winner == Winner.O) {
					bImage = ImageIO.read(new File("res/Winner O.png"));
				} else if (winner == Winner.X) {
					bImage = ImageIO.read(new File("res/Winner X.png"));
				} else {
					bImage = ImageIO.read(new File("res/Draw.png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			

			g.setFont(new Font("Valorant", Font.PLAIN, 101));
			g.setColor(new Color(31, 31, 31));
			// Point point = winnerOutline.getCenterTextOnScreenPosition(g);
			// point.x += 5;
			// point.y++;
			// winnerOutline.renderText(g, point);

			if (changeColor) {
				int rand = (int) Math.floor(Math.random() * (11 - 0 + 1) + 0);

				switch (rand) {
					case 0:
						g.setColor(Color.WHITE);
						break;
					case 1:
						g.setColor(new Color(190, 145, 100));
						break;

					case 2:
						g.setColor(Color.GRAY);
						break;

					case 3:
						g.setColor(new Color(130, 245, 140));
						break;

					case 4:
						g.setColor(Color.RED);
						break;

					case 5:
						g.setColor(Color.PINK);
						break;

					case 6:
						g.setColor(Color.ORANGE);
						break;

					case 7:
						g.setColor(Color.YELLOW);
						break;

					case 8:
						g.setColor(Color.GREEN);
						break;

					case 9:
						g.setColor(new Color(125, 104, 77));
						break;

					case 10:
						g.setColor(Color.CYAN);
						break;

					case 11:
						g.setColor(Color.BLUE);
						break;

					default:
						break;
				}
			} else {
				g.setColor(lastColor);
			}

			g.setFont(new Font("Valorant", Font.PLAIN, 100));
			lastColor = g.getColor();
			// winner.renderTextInCenter(g, WIDTH, HEIGHT);
			displayCenteredImage(g, bImage.getScaledInstance(720, 405, Image.SCALE_DEFAULT));
			
		}

	}

	private void displayCenteredImage(Graphics g, Image image) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		int x = WIDTH / 2 - width / 2;
		int y = HEIGHT / 2 - height / 2;
		g.drawImage(image, x, y, width, height, null);
	}

	public void resetGame() {
		xScore = 0;
		oScore = 0;
		round = 1;
		turn = Turn.X;
		winner = Winner.None;
		board.clearSections();
		board.isBoardClear = true;
		board.isBoardFull = false;
	}

	public static void main(String[] args) throws Exception {

		new Game();

		// String url = "res/electro.mp3";

		// Sound sound = new Sound(url);
		// sound.play();
	}

}
