package Graphics;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import Logic.Button;

public class GraphicsButton extends Button{
	
	Image pressed, unpressed;
	
	public GraphicsButton() {
		super();
		pressed = null;
		unpressed = null;
	}
	
	public GraphicsButton(String pressed, String unpressed, int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setHeight(height);
		this.setWidth(width);
		try {
			this.pressed = ImageIO.read(new File("Images"+File.separator+pressed));
			this.unpressed = ImageIO.read(new File("Images"+File.separator+unpressed));
		}
		catch(Exception e) {
			System.out.println("Errore nel caricamento dell'immagine del bottone");
		}
	}
	
	public Image getPressed() {
		return pressed;
	}
	
	public boolean setPressed(String pressed) {
		try {
			this.pressed = ImageIO.read(new File("Images"+File.separator+pressed));
		}
		catch(Exception e) {
			System.out.println("Errore nel caricamento dell'immagine del bottone");
			return false;
		}
		return true;
	}
	
	public Image getUnpressed() {
		return unpressed;
	}
	
	public boolean setUnpressed(String unpressed) {
		try {
			this.unpressed = ImageIO.read(new File("Images"+File.separator+unpressed));
		}
		catch(Exception e) {
			System.out.println("Errore nel caricamento dell'immagine del bottone");
			return false;
		}
		return true;
	}

	
	public Image getActual() {
		if(isPressed)
			return pressed;
		else 
			return unpressed;
	}
	

}
