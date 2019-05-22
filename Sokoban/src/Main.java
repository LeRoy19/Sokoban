import Graphics.*;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    if (gd.isFullScreenSupported()) {
		JFrame frame = new JFrame("Sokoban");
		frame.setUndecorated(true);
		frame.setResizable(false);
		gd.setFullScreenWindow(frame);
		Gui g = new Gui();
		frame.add(g, BorderLayout.CENTER);
		g.repaint();
		
		frame.setVisible(true);
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					
				}
			}
		});
	
	     
	      
	      
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	   
	}
		
}
