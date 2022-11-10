package tictactoe;

// Utility methods for the game
public final class GameUtilities {

	public static final boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if ((mx > x && mx < x + width) && (my > y && my < y + height))
			return true;
		return false;
	}

	public static final int clamp(int val, int min, int max) {
		return Math.max(min, Math.min(max, val));
	}

	public static final float clamp(float val, float min, float max) {
		if (val >= max)
			return max;
		else if (val <= min)
			return min;
		else
			return val;
	}

}
