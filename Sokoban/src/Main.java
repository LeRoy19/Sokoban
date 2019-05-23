import Graphics.*;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		
	
	    
		JFrame frame = new JFrame("Sokoban");
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Gui g = new Gui();
		GraphicsButton b1 = new GraphicsButton( "pressed - Copia (2).png", "unpressed - Copia.png", 600, 600, 150, 50);
		g.AddButton(b1);
		g.setFocusable(true);
		frame.add(g, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	     
	      
	      
		
		
		
	    
	   
	}
		
}
