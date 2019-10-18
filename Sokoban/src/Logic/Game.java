package Logic;

import Graphics.PrincipalFrame;

/*thread che ridisegna ogni tot milliseondi il pannello che è contenuto nel frame principale*/

public class Game implements Runnable {

	PrincipalFrame frame = null;	
	
	public Game() {
		
		frame = new PrincipalFrame();
		frame.setVisible(true);
	}
	
	
	@Override
	public void run() {
		while(true) {
			frame.getActualPane().repaint();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
