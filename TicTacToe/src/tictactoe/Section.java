package tictactoe;

import java.awt.Graphics;

public class Section {

	private ImageStorage imageStorage;
	public int coordinate;
	public int x, y;
	public int width, height;
	public int input = Game.NONE;
	public boolean hasInput = false;

	public Section(ImageStorage imageStorage, int coordinate) {
		this.coordinate = coordinate;
		this.x = 0;
		this.y = 0;
		this.width = 100;
		this.height = 100;
	}

	public Section(ImageStorage imageStorage, int coordinate, int x, int y, int width, int height) {
		this.imageStorage = imageStorage;
		this.coordinate = coordinate;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean mouseOver() {
		return GameUtilities.mouseOver(Game.mouseCoord.x, Game.mouseCoord.y, x, y, width, height);
	}

	public void renderSection(Graphics g, int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	public void renderSection(Graphics g) {
		renderSection(g, x, y, width, height);
	}

	public void renderInput(Graphics g) {
		if (input == Game.X) {
			g.drawImage(imageStorage.xImg, x, y, width, height, null);
		} else if (input == Game.O) {
			g.drawImage(imageStorage.oImg, x, y, width, height, null);
		}
	}
	
}
