import java.io.IOException;
import Logic.Game;



public class Main {
	
	
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		new Thread(game).start();
	}
		
}
