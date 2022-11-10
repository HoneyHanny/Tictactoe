package tictactoe;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

// point is here (top left)
// |
// v
// *-------------
// |			|
// |			|
// |			|
// |			|
// --------------


public class Box extends Rectangle {

	private Color color = Color.WHITE;
	private Graphics g;
	private int borderRadius = 0;

	public Box(Graphics g) {
		super(0, 0, 10, 10);
		this.g = g;
	}

	public Box(int width, int height, Graphics g) {
		super(width, height);
		this.g = g;
	}

	public Box(int x, int y, int width, int height, Graphics g) {
		super(x, y, width, height);
		this.g = g;
	}

	public Box(int x, int y, int width, int height, Color color, Graphics g) {
		super(x, y, width, height);
		this.color = color;
		this.g = g;
	}
	
	public Box(Graphics g, int borderRadius) {
		super(0, 0, 10, 10);
		this.g = g;
		this.borderRadius = borderRadius;
	}

	public Box(int width, int height, Graphics g, int borderRadius) {
		super(width, height);
		this.g = g;
		this.borderRadius = borderRadius;
	}

	public Box(int x, int y, int width, int height, Graphics g, int borderRadius) {
		super(x, y, width, height);
		this.g = g;
		this.borderRadius = borderRadius;

	}

	public Box(int x, int y, int width, int height, Color color, Graphics g, int borderRadius) {
		super(x, y, width, height);
		this.color = color;
		this.g = g;
		this.borderRadius = borderRadius;

	}

	public void drawBox(Graphics g, Rectangle rect) {
		g.setColor(color);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void drawBox(Graphics g, Point point, int width, int height) {
		drawBox(g, new Rectangle(point.x, point.y, width, height));
	}

	public void drawBox(Graphics g, int x, int y, int width, int height) {
		drawBox(g, new Point(x, y), width, height);
	}

	public void drawBox(Graphics g) {
		drawBox(g, x, y, width, height);
	}

	public void drawBox(Graphics g, Rectangle rect, Color color) {
		g.setColor(color);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void drawBox(Graphics g, Point point, int width, int height, Color color) {
		drawBox(g, new Rectangle(point.x, point.y, width, height), color);
	}

	public void drawBox(Graphics g, int x, int y, int width, int height, Color color) {
		drawBox(g, new Point(x, y), width, height, color);
	}

	public void drawBox(Graphics g, Color color) {
		drawBox(g, x, y, width, height, color);
	}

	public void drawBox(Point point, int width, int height) {
		drawBox(g, new Rectangle(point.x, point.y, width, height));
	}

	public void drawBox(int x, int y, int width, int height) {
		drawBox(g, new Point(x, y), width, height);
	}

	public void drawBox() {
		drawBox(g, x, y, width, height);
	}

	public void drawBox(Rectangle rect, Color color) {
		g.setColor(color);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void drawBox(Point point, int width, int height, Color color) {
		drawBox(g, new Rectangle(point.x, point.y, width, height), color);
	}

	public void drawBox(int x, int y, int width, int height, Color color) {
		drawBox(g, new Point(x, y), width, height, color);
	}

	public void drawBox(Color color) {
		drawBox(g, x, y, width, height, color);
	}
	
	public void drawBoxX(int x) {
		drawBox(g, x, y, width, height);
	}

	public void drawBoxY(int y) {
		drawBox(g, x, y, width, height);
	}

	public void drawRoundBox(int borderRadius) {
		g.setColor(color);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRoundRect(x, y, width, height, borderRadius, borderRadius);
	}

	public void drawRoundBox(Graphics g, Rectangle rect, int borderRadius) {
		g.setColor(color);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRoundRect(rect.x, rect.y, rect.width, rect.height, borderRadius, borderRadius);
	}

	public void drawRoundBox(Graphics g, Point point, int width, int height, int borderRadius) {
		drawRoundBox(g, new Rectangle(point.x, point.y, width, height), borderRadius);
	}

	public void drawRoundBox(Graphics g, int x, int y, int width, int height, int borderRadius) {
		drawRoundBox(g, new Point(x, y), width, height, borderRadius);
	}

	public void drawRoundBox(Graphics g, int borderRadius) {
		drawRoundBox(g, x, y, width, height, borderRadius);
	}

	public void drawRoundBox(Graphics g, Rectangle rect, Color color, int borderRadius) {
		g.setColor(color);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRoundRect(rect.x, rect.y, rect.width, rect.height, borderRadius, borderRadius);
	}
	
	public void drawRoundBox(Graphics g, Point point, int width, int height, Color color, int borderRadius) {
		drawRoundBox(g, new Rectangle(point.x, point.y, width, height), color, borderRadius);
	}

	public void drawRoundBox(Graphics g, int x, int y, int width, int height, Color color, int borderRadius) {
		drawRoundBox(g, new Point(x, y), width, height, color, borderRadius);
	}

	public void drawRoundBox(Graphics g, Color color, int borderRadius) {
		drawRoundBox(g, x, y, width, height, color, borderRadius);
	}

	public void drawRoundBox(Point point, int width, int height, int borderRadius) {
		drawRoundBox(g, new Rectangle(point.x, point.y, width, height), borderRadius);
	}

	public void drawRoundBox(int x, int y, int width, int height, int borderRadius) {
		drawRoundBox(g, new Point(x, y), width, height, borderRadius);
	}

	public void drawRoundBox() {
		drawRoundBox(g, x, y, width, height, borderRadius);
	}

	public void drawRoundBox(Rectangle rect, Color color, int borderRadius) {
		g.setColor(color);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	public void drawRoundBox(Point point, int width, int height, Color color, int borderRadius) {
		drawRoundBox(g, new Rectangle(point.x, point.y, width, height), color, borderRadius);
	}

	public void drawRoundBox(int x, int y, int width, int height, Color color, int borderRadius) {
		drawRoundBox(g, new Point(x, y), width, height, color, borderRadius);
	}

	public void drawRoundBox(Color color, int borderRadius) {
		drawRoundBox(g, x, y, width, height, color, borderRadius);
	}

	public void drawRoundBoxX(int x, int borderRadius) {
		drawRoundBox(g, x, y, width, height, borderRadius);
	}

	public void drawRoundBoxY(int y, int borderRadius) {
		drawRoundBox(g, x, y, width, height, borderRadius);
	}

	public void centerBoxHorizontal(int screenWidth) {
		x = screenWidth / 2 - width / 2;
	}

	public void centerBoxVertical(int screenHeight) {
		y = screenHeight / 2 - height / 2;
	}

	public void centerBox(int screenWidth, int screenHeight) {
		centerBoxHorizontal(screenWidth);
		centerBoxVertical(screenHeight);
	}

	public Color getColor() {
		return color;
	}

	public int getBorderRadius() {
		return borderRadius;
	}

	public Graphics getGraphics() {
		return g;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setGraphics(Graphics g) {
		this.g = g;
	}

	public void setBorderRadius(int borderRadius) {
		this.borderRadius = borderRadius;
	}

	public void drawRoundBox(int i, Color green) {
	}

}
