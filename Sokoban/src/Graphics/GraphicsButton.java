package Graphics;

import java.awt.Dimension;
import java.awt.Image;



public class GraphicsButton{
	
	public MyImage pressed, unpressed;
	public boolean isPressed;
	
	public GraphicsButton() {
		super();
		pressed = null;
		unpressed = null;
		isPressed = false;
	}
	
	public GraphicsButton(Dimension d, String pressed, String unpressed, int x, int y, int width, int height) {
		this.pressed = new MyImage(d, pressed, x, y, width, height);
		this.unpressed = new MyImage(d, unpressed, x, y, width, height);
		isPressed = false;
	}
	
	public MyImage getPressed() {
		return pressed;
	}
	
	public void setPressed(Image pressed) {
		this.pressed.image=pressed;
	}
	
	public Image getUnpressed() {
		return this.unpressed.image;
	}
	
	public void setUnpressed(Image unpressed) {
		this.unpressed.image = unpressed;
	}

	public Image getActual() {
		if(isPressed)
			return this.pressed.image;
		else 
			return this.unpressed.image;
	}
	
	public void setStatus(boolean isPressed) {
		this.isPressed = isPressed;
	}
	

}
