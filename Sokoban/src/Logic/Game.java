package Logic;


import javax.swing.JFrame;

import Graphics.GraphicsPlayer;
import Graphics.Gui;
import Graphics.Level;
import Graphics.PrincipalFrame;



public class Game implements Runnable {

	PrincipalFrame frame = null;	
	GraphicsPlayer player1 = null;
	
	public Game() {
		
		frame = new PrincipalFrame();
		Gui g = new Gui("ui.png");
		frame.setAcutalPane(g);
		frame.setVisible(true);
	}
	
	
	@Override
	public void run() {
		while(true) {
			frame.getActualPane().repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
