package Graphics;

import java.awt.Dimension;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;


public class MyImage{

	public int x, y, width, height;
	float scalex, scaley;
	public Image image = null;
	
	public MyImage(Dimension d, Image im, int x, int y, int width, int height) {
		scalex = (float) (d.getWidth() / (float) 1366);
		scaley = (float) (d.getHeight() / (float) 768);
		this.x = (int) (x * scalex);
		this.y = (int) (y * scaley);
		this.height = (int) (height * scaley);
		this.width = (int) (width * scalex);
		image = im.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public MyImage(Dimension d, String im, int x, int y, int width, int height) {
		Image img = null;
		try {
			img = ImageIO.read(new File(im));
		} catch (Exception e) {
			System.out.println("errore nel caricamento della MyImage");
		}
		scalex = (float) (d.getWidth()) / (float) 1366;
		scaley = (float) (d.getHeight()) / (float) 768;
		this.x = (int) (x * scalex);
		this.y = (int) (y * scaley);
		this.height = (int) (height * scaley);
		this.width = (int) (width * scalex);
		image = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	

	
}
