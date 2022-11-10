package tictactoe;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

// --------------
// |			|
// |			|
// |			|
// |			|
// *-------------
// ^
// |
//point is here (bottom right)

public class Text {

	private String text;
	private Point point;

	public Text() {
		
	}

	public Text(String text) {
		this.text = text;
	}

	public Text(String text, Point point) {
		this.text = text;
		this.point = point;
	}
	
	public Text(String text, int x, int y) {
		this(text, new Point(x, y));
	}

	public Point animate(int xOffset, int yOffset) {
		return new Point (point.x += xOffset, point.y += yOffset);
	}

	public void renderCenteredTextInsideBox(Graphics g, Rectangle box) {
		Point centerPoint = getCenterTextInBoxPosition(g, box.x, box.y, box.width, box.height);
		g.drawString(text, centerPoint.x, centerPoint.y);
	}

	public void renderCenteredTextInsideBox(Graphics g, Point boxPoint, int boxWidth, int boxHeight) {
		renderCenteredTextInsideBox(g, new Rectangle(boxPoint.x, boxPoint.y, boxWidth, boxHeight));
	}

	public void renderCenteredTextInsideBox(Graphics g, int x, int y, int boxWidth, int boxHeight) {
		renderCenteredTextInsideBox(g, new Point(x, y), boxWidth, boxHeight);
	}

	public void renderTextInCenter(Graphics g, int screenWidth, int screenHeight) {
		Point center = getCenterTextOnScreenPosition(g, screenWidth, screenHeight);
		g.drawString(text, center.x, center.y);
	}

	public void renderTextInCenter(Graphics g) {
		Point center = getCenterTextOnScreenPosition(g);
		g.drawString(text, center.x, center.y);
	}

	public void renderText(Graphics g) {
		g.drawString(text, point.x, point.y);
	}

	public void renderText(Graphics g, Point point) {
		g.drawString(text, point.x, point.y);
	}

	public void renderText(Graphics g, int x, int y) {
		g.drawString(text, x, y);
	}

	public Point getCenterTextOnScreenPosition(Graphics g, int screenWidth, int screenHeight) {
		int x, y;
		x = screenWidth / 2 - g.getFontMetrics().stringWidth(text) / 2;
		y = screenHeight / 2 + g.getFontMetrics().getHeight() / 2;
		return new Point(x, y);
	}

	public Point getCenterTextOnScreenPosition(Graphics g) {
		return getCenterTextOnScreenPosition(g, Game.WIDTH, Game.HEIGHT);
	}

	public Point getCenterTextInBoxPosition(Graphics g, Rectangle box) {
		int cx, cy;
		FontMetrics fm = g.getFontMetrics();

		cx = box.x + box.width / 2 - fm.stringWidth(text) / 2;
		cy = box.y + box.height / 2 + fm.getHeight() / 2;
		return new Point(cx, cy);
	}

	public Point getCenterTextInBoxPosition(Graphics g, Point boxPoint, int boxWidth, int boxHeight) {
		return getCenterTextInBoxPosition(g, new Rectangle(boxPoint.x, boxPoint.y, boxWidth, boxHeight));
	}

	public Point getCenterTextInBoxPosition(Graphics g, int x, int y, int boxWidth, int boxHeight) {
		return getCenterTextInBoxPosition(g, new Point(x, y), boxWidth, boxHeight);
	}

	public void centerText(Graphics g, Dimension screenSize) {
		Point center = getCenterTextOnScreenPosition(g, screenSize.width, screenSize.height);
		point.x = center.x;
		point.y = point.y;
	}

	public void centerText(Graphics g) {
		Point center = getCenterTextOnScreenPosition(g, Game.WIDTH, Game.HEIGHT);
		point.x = center.x;
		point.y = point.y;
	}

	public void centerText(Graphics g, int screenWidth, int screenHeight) {
		Point center = getCenterTextOnScreenPosition(g, screenWidth, screenHeight);
		point.x = center.x;
		point.y = point.y;
	}

	public int getCenterHorizontalPosition(Graphics g, int screenWidth) {
		return (screenWidth / 2) - (g.getFontMetrics().stringWidth(text) / 2);
	}

	public int getCenterVerticalPosition(Graphics g, int screenHeight) {
		return (screenHeight / 2) - (g.getFontMetrics().getHeight() / 2);
	}

	public void centerTextHorizontal(Graphics g, int screenWidth) {
		point.x = getCenterHorizontalPosition(g, screenWidth);
	}

	public void centerTextVertical(Graphics g, int screenHeight) {
		point.y = getCenterVerticalPosition(g, screenHeight);
	}

	public void centerTextInBoxHorizontal(Graphics g, int boxX, int boxWidth) {
		point.x = boxX + boxWidth / 2 - g.getFontMetrics().stringWidth(text) / 2;
	}

	public void centerTextInBoxVertical(Graphics g, int boxY, int boxHeight) {
		point.y = boxY + boxHeight / 2 + g.getFontMetrics().getHeight() / 2;
	}

	public void renderTextInCenterHorizontal(Graphics g, int screenWidth) {
		int x = getCenterHorizontalPosition(g, screenWidth);
		renderText(g, x, point.y);
	}

	public String getText() {
		return this.text;
	}

	public Point getPoint() {
		return this.point;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}
