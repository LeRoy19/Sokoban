package Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Logic.GameManager;
import Logic.Map;

public class Level extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	public Map map = null;
	public GraphicsPlayer player = null;
	private GameManager gameManager = null;
	
	public Level(String playerColour) {
		this.addKeyListener(this);
		this.setFocusable(true);
		map = new Map("map1.txt");
		player = new GraphicsPlayer(playerColour);
		gameManager = new GameManager(this);
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(background, 0, 0, null);  disegno il suolo
		//disegno i blocchi fissi
		for(int i = 0; i< map.staticCrates.size(); i++) {
					g.drawImage(map.staticCrates.get(i).image, map.staticCrates.get(i).coordinate.x,
							map.staticCrates.get(i).coordinate.y, null );
		}
		
		//disegno i blocchi mobili
		for(int i = 0; i< map.mobileCrates.size(); i++) {
			g.drawImage(map.mobileCrates.get(i).image, map.mobileCrates.get(i).coordinate.x,
					map.mobileCrates.get(i).coordinate.y, null );
		}
		
		//disegno i target
		for(int i = 0; i< map.targets.size(); i++) {
			g.drawImage(map.targets.get(i).image, map.targets.get(i).coordinate.x,
					map.targets.get(i).coordinate.y, null );
		}
		
		//disegno il player
		g.drawImage(player.getImgCorrente(), map.player.getX(), map.player.getY(), null);
		
}
		
	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int event = arg0.getKeyCode();
		if(event==KeyEvent.VK_RIGHT) {
			gameManager.goRight();
		}
		
		if(event==KeyEvent.VK_LEFT) {
			gameManager.goLeft();
			repaint();
		}
		
		if(event==KeyEvent.VK_UP) {
			gameManager.goUp();
			repaint();
		}
		
		if(event==KeyEvent.VK_DOWN) {
			gameManager.goDown();
			repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
