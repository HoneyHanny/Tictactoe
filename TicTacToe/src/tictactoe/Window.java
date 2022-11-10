package tictactoe;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends JFrame {

	public Window(Game game) {

		setTitle(Game.TITLE);
		add(game);

		try {
			Image icon = ImageIO.read(new File("res/icon.png"));
			setIconImage(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);		
	}
	
}
