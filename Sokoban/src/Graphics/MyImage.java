package Graphics;

import java.awt.Dimension;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;


public class MyImage{

	public int x, y, width, height;
	public Image image = null;
	
	public MyImage(Dimension d, Image im, int x, int y, int width, int height) {
		float scalex = (float) (d.getWidth() / (float) 1366);
		float scaley = (float) (d.getHeight() / (float) 768);
		this.x = (int) (x * scalex);
		this.y = (int) (y * scaley);
		this.height = (int) (height * scaley);
		this.width = (int) (width * scalex);
		image = im.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		System.out.println(d.getWidth());
	}
	
	public MyImage(Dimension d, String im, int x, int y, int width, int height) {
		try {
			image = ImageIO.read(new File(im));
		} catch (Exception e) {
			System.out.println("errore nel caricamento della MyImage");
		}
		float scalex = (float) (d.getWidth()) / (float) 1366;
		float scaley = (float) (d.getHeight()) / (float) 768;
		this.x = (int) (x * scalex);
		this.y = (int) (y * scaley);
		this.height = (int) (height * scaley);
		this.width = (int) (width * scalex);
	}
	

	
}
