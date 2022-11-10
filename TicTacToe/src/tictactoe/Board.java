package tictactoe;

import java.awt.Color;
import java.awt.Graphics;

public class Board {

	/*
		Indexes with the board
		[0] [1] [2]
		[3] [4] [5]
		[6] [7] [8]
	*/

	public final int winningConditions[][] = {
			{ 0, 1, 2 },
			{ 3, 4, 5 },
			{ 6, 7, 8 },
			{ 0, 3, 6 },
			{ 1, 4, 7 },
			{ 2, 5, 8 },
			{ 0, 4, 8 },
			{ 2, 4, 6 }
	};

	public boolean isBoardClear = true;
	public boolean isBoardFull = false;
	public Section[] sections = new Section[9];

	private Game game;

	public Board(Game game, ImageStorage imageStorage) {

		this.game = game;

		int count = 0;

		for (int i = 60; i < 750; i += 250) {
			for (int j = 200; j < 750; j += 250) {
				sections[count] = new Section(imageStorage, count, j, i, 250, 250);
				count++;
			}
		}

	}

	public void render(Graphics g) {

		for (int i = 0; i < 9; i++) {
			g.setColor(Color.MAGENTA);
			g.drawRect(sections[i].x, sections[i].y, sections[i].width, sections[i].height);
			if (!sections[i].hasInput) {
				if (sections[i].mouseOver() && Game.state == State.Game) {
					g.setColor(Color.ORANGE);
					g.drawRect(sections[i].x + 1, sections[i].y + 1, sections[i].width - 2, sections[i].height - 2);
				}
			} else if (sections[i].hasInput) {
				sections[i].renderInput(g);
			}
		}

	}

	public void clearSections() {
		for (int index = 0; index < sections.length; index++) {
			sections[index].hasInput = false;
			sections[index].input = Game.NONE;
		}
	}

	// checks the board for win conditions
	// * will set the winner in game class and will set state to EndRound if someone wins or TIE
	// * this is the only time game.state will be set to EndRound
	public void checkWinCondition() {
		boolean roundWon = false;
		for (int i = 0; i <= 7; i++) {
			final int[] winCondition = winningConditions[i];
			final int a = sections[winCondition[0]].coordinate;
			final int b = sections[winCondition[1]].coordinate;
			final int c = sections[winCondition[2]].coordinate;
			if (sections[a].input == Game.NONE || sections[b].input == Game.NONE || sections[c].input == Game.NONE)
				continue;
			if (sections[a].input == sections[b].input && sections[b].input == sections[c].input) {
				roundWon = true;
				break;
			}
		}

		if (roundWon) {
			if (game.turn == Turn.O) {
				game.winner = Winner.X;
				game.xScore++;
			} else {
				game.winner = Winner.O;
				game.oScore++;
			}
			Game.state = State.EndRound;
			return;
		}
		
		if (isBoardFull) {
			game.winner = Winner.None;
			Game.state = State.EndRound;
		}

	}

}
