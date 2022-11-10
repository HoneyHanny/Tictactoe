package tictactoe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{

	private Game game;
	private Menu menu;
	private Board board;

	public MouseInput(Game game, Menu menu, Board board) {
		this.game = game;
		this.menu = menu;
		this.board = board;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// game.click.stop();
		// game.click.play();

		if (Game.state == State.Menu) {

			if (menu.play) {
				Game.state = State.Game;
			} else if (menu.exit) {
				Game.state = State.ConfirmExit;
			}

		} else if (Game.state == State.Game) {

			if (game.quit) {
				Game.prevState = State.Game;
				Game.state = State.ConfirmQuit;
			}

			boolean fullFlag = true;
			for (int i = 0; i < board.sections.length; i++) {
				if (board.sections[i].mouseOver() && board.sections[i].hasInput == false) {
					board.sections[i].hasInput = true;
					board.isBoardClear = false;
					if (game.turn == Turn.X) {
						board.sections[i].input = Game.X;
						game.turn = Turn.O;
					} else if (game.turn == Turn.O) {
						board.sections[i].input = Game.O;
						game.turn = Turn.X;
					}
				}
				if (!board.sections[i].hasInput && fullFlag)
					fullFlag = false;
			}

			if (fullFlag)
				board.isBoardFull = true;
			else
				board.isBoardFull = false;
			board.checkWinCondition();

		} else if (Game.state == State.ConfirmQuit) {
			
			if (game.confirm) {
				game.resetGame();
				Game.state = State.Menu;
				game.confirm = false;
			} else if (game.cancel) {
				Game.state = Game.prevState;
				game.cancel = false;
			}

		} else if (Game.state == State.ConfirmExit) {

			if (game.confirm) {
				System.exit(0);
			} else if (game.cancel) {
				Game.state = State.Menu;
				game.cancel = false;
			}
			
		} else if (Game.state == State.EndRound) {

			if (game.quit) {
				Game.prevState = State.EndRound;
				Game.state = State.ConfirmQuit;
			}

			if (GameUtilities.mouseOver(Game.mouseCoord.x, Game.mouseCoord.y, 1100, 550, 200, 100)) {
				Game.state = State.Game;
				game.newRound();
				if (game.winner == Winner.X) {
					game.turn = Turn.X;
				} else if (game.winner == Winner.O) {
					game.turn = Turn.O;
				}
			} else if (GameUtilities.mouseOver(Game.mouseCoord.x, Game.mouseCoord.y, 1100, 700, 200, 100)) {
				Game.state = State.ConfirmQuit;
			}
			
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
