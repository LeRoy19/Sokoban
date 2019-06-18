import java.io.IOException;

import javax.swing.JFrame;

import Graphics.GraphicsPlayer;
import Graphics.Gui;
import Graphics.Level;
import Graphics.PrincipalFrame;
import Logic.Game;



public class Main {
	
	
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		new Thread(game).start();
	}
		
}
