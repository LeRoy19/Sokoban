import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Button {
	Image pressed =null, unpressed=null, current=null;
	int x, y;
	
	public Button(String pressed, String unpressed, int x, int y){
		this.x=x;
		this.y=y;
		try {
			this.unpressed = ImageIO.read(new File(unpressed));
			this.pressed = ImageIO.read(new File(pressed)); 
			current=this.unpressed;
		}
		catch(Exception e) {
			System.out.println("Errore nel caricamento dell'immagine");
		}
		
	}

	public Image getPressed() {
		return pressed;
	}

	public void setPressed(Image pressed) {
		this.pressed = pressed;
	}

	public Image getUnpressed() {
		return unpressed;
	}

	public void setUnpressed(Image unpressed) {
		this.unpressed = unpressed;
	}

	public Image getCurrent() {
		return current;
	}

	public void setCurrent(int a) {
		if(a==0)
			current = pressed;
		else
			current = unpressed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	

	

}
