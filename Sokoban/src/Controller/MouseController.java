package Controller;
import Logic.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Graphics.Gui;

public class MouseController implements MouseListener {

	Gui p = null;
	public MouseController(Gui gui) {
		p=gui;
	}
	
	public MouseEvent lastEvent = null;
	public boolean thereIsAnEvent;
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		sendCoordinate(arg0.getX(), arg0.getY());
		lastEvent = arg0;
		thereIsAnEvent = true;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		sendCoordinate(arg0.getX(), arg0.getY());
	}
	
	public Coordinate sendCoordinate(int x, int y) {
		Coordinate c = new Coordinate(x,y);
		return c;
	}

	public void reset() {
		lastEvent = null;
		thereIsAnEvent = false;
		
	}
	

 
}
