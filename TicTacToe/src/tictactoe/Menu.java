package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

	public boolean play = false;
	public boolean exit = false;
	public boolean onePlayer = false;
	public boolean twoPlayers = false;
	public boolean back = false;

	public void render(Graphics g) {

		g.setColor(Color.WHITE);

		if (Game.state == State.Menu) {

			g.setFont(new Font("Aquire", Font.PLAIN, 50));

			Text titleText = new Text("T i c  T a c  T o e", 0, 200);
			Color yellow = new Color(250, 189, 47);
			g.setColor(yellow);
			titleText.renderTextInCenterHorizontal(g, Game.WIDTH);
			g.setColor(Color.WHITE);
			
			Box playBox = new Box(0, 400, 200, 100, g);
			Box exitBox = new Box(0, 600, 200, 100, g);
			playBox.centerBoxHorizontal(Game.WIDTH);
			exitBox.centerBoxHorizontal(Game.WIDTH);

			Text playText = new Text("Play", playBox.x, 400);
			Text exitText = new Text("Exit", exitBox.x, 600);

			g.setFont(new Font("Aquire", Font.PLAIN, 20));

			if (GameUtilities.mouseOver(Game.mouseCoord.x, Game.mouseCoord.y, playBox.x, playBox.y, playBox.width, playBox.height)) {

				play = true;
				exit = false;

				exitText.renderCenteredTextInsideBox(g, exitBox);
				exitBox.drawRoundBox(20);
				
				
				g.setColor(yellow);
				playText.renderCenteredTextInsideBox(g, playBox);
				playBox.drawRoundBox(yellow, 20);

			} else if (GameUtilities.mouseOver(Game.mouseCoord.x, Game.mouseCoord.y, exitBox.x, exitBox.y, exitBox.width, exitBox.height)) {

				play = false;
				exit = true;

				playText.renderCenteredTextInsideBox(g, playBox);
				playBox.drawRoundBox(20);

				g.setColor(yellow);
				exitText.renderCenteredTextInsideBox(g, exitBox);
				exitBox.drawRoundBox(yellow, 20);

			} else { // no mouse over condition on either buttons | will not animate

				play = false;
				exit = false;

				playText.renderCenteredTextInsideBox(g, playBox);
				exitText.renderCenteredTextInsideBox(g, exitBox);

				playBox.drawRoundBox(20);
				exitBox.drawRoundBox(20);
			}
	
		}
		
	}
	
}
