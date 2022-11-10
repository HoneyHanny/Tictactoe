package tictactoe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// this is a singleton class
public class ImageStorage {

	private static ImageStorage instance = null;

	public BufferedImage xImg;
	public BufferedImage oImg;

	private ImageStorage() {
		
		try {
			xImg = ImageIO.read(new File("res/X.png"));
			oImg = ImageIO.read(new File("res/O.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static ImageStorage getInstance() {
		if (instance == null) {
			instance = new ImageStorage();
		}
		return instance;
	}
	
}
