package Graphics;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import Logic.Coordinate;

public class MobileCrate {
	final int value = -8;
	public Coordinate coordinate = null;
	Image image = null;
	String color = null;
	
	public MobileCrate(String color, int i, int j) {
		this.color = color;
		try {
			this.image = ImageIO.read(new File("Images"+File.separator+"mobile"+color+"png"));
		}
		catch(Exception e) {
			System.out.println("Errore nel caricamento dell'immagine del target statico");
		}
		coordinate = new Coordinate(i, j);
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public int getValue() {
		return value;
	}
}
