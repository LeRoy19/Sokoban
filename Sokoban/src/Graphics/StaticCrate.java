package Graphics;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import Logic.Coordinate;

public class StaticCrate {

	final int value = 10;
	public Coordinate coordinate = null;
	Image image = null;
	String color = null;
	
	public StaticCrate(String color, int i, int j) {
		this.color = color;
		try {
			this.image = ImageIO.read(new File("Images"+File.separator+"static"+color+"png"));
		}
		catch(Exception e) {
			System.out.println("Errore nel caricamento dell'immagine del blocco statico");
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
