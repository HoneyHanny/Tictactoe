package tictactoe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	
	@Override
	public void keyTyped(KeyEvent e) {
				
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (Game.state == State.Game) {

			if (KeyEvent.VK_ESCAPE == e.getKeyCode())
				Game.state = State.ConfirmQuit;

		} else if (Game.state == State.ConfirmQuit) {

			if (KeyEvent.VK_ESCAPE == e.getKeyCode())
				Game.state = State.Game;

		} else if (Game.state == State.ConfirmExit) {

			if (KeyEvent.VK_ESCAPE == e.getKeyCode())
				Game.state = State.Menu;
			
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
				
	}
	
}
