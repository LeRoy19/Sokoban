import java.io.IOException;
import Logic.Game;

/*
 * 
 * aggiungere la musichetta di vittoria o di sconfitta
 * aggiungere qualche mappa
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		new Thread(game).start();
	}
		
}
