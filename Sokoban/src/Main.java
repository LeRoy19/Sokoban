import java.io.IOException;
import Logic.Game;

/*
 * aggiungere qualche mappa
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		new Thread(game).start();
	}
		
}
