package Logic;


import Graphics.GraphicsPlayer;
import Graphics.PrincipalFrame;



public class Game implements Runnable {

	PrincipalFrame frame = null;	
	GraphicsPlayer player1 = null;
	
	public Game() {
		
		frame = new PrincipalFrame();
		frame.setVisible(true);
	}
	
	
	@Override
	public void run() {
		while(true) {
			frame.getActualPane().repaint();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
