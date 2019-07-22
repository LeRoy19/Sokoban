import java.io.IOException;
import Logic.Game;

/*
 * SOKOBAN LORENZO GUASTALEGNAME
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		new Thread(game).start();
	}
		
}
