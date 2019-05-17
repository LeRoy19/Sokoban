import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) throws IOException {
	    Button b1= new Button("pressed - Copia.png", "unpressed - Copia.png", 500, 500);
		JFrame frame = new JFrame("Sokoban");
		frame.setSize(600,600);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		Gui pane = new Gui("ui.png");
		frame.setLayout(new BorderLayout());
		pane.AddButton(b1);
		
		frame.add(pane);
		frame.setVisible(true);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		
}
