package Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Logic.GameManager;
import Logic.Map;

public class Level extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	public Map map = null;
	public GraphicsPlayer player = null;
	private GameManager gameManager = null;
	Image background = null;
	Image grass = null;
	ArrayList<Image> targets = null;
	ArrayList<Image> mobileCrates = null;
	ArrayList<Image> staticCrates = null;
	ArrayList<Image> fullTarget = null;
	
	
	public Level(String playerColour) {
		
		fullTarget = new ArrayList<Image>();
		targets = new ArrayList<Image>();
		mobileCrates = new ArrayList<Image>();
		staticCrates = new ArrayList<Image>();
		this.addKeyListener(this);
		this.setFocusable(true);
		map = new Map("mapx.txt");
		player = new GraphicsPlayer(playerColour);
		gameManager = new GameManager(this);
		try {
			background = ImageIO.read(new File("Images"+File.separator+"background.jpg"));
			grass = ImageIO.read(new File("Images"+File.separator+"grass.png")); 
			for(int i = 1; i <= 15; i++) {
				Image t = ImageIO.read(new File("Images"+File.separator+"Components" + File.separator+i+".png"));
				if(i<=5) {
					Image x = ImageIO.read(new File("Images"+File.separator+"Components" + File.separator+"e"+i+".png"));
					fullTarget.add(x);
					mobileCrates.add(t);
				}
				else if (i<=10)
					staticCrates.add(t);
				else
					targets.add(t);
			}
		}catch (Exception e) {
			System.out.println("Errore nel caricamento delle immagini del livello");
		}
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(),null);
		g.drawImage(staticCrates.get(0), 0, 0, null);
		for(int i = 0; i < map.rows; i++) {
			for (int j = 0; j < map.columns; j++) {
				g.drawImage(grass, j*64, i*64, null);	
				switch(map.matrix[i][j]) {
				case 1:
					g.drawImage(fullTarget.get(0), j*64, i*64, null);
					break;
				case 2:
					g.drawImage(fullTarget.get(1), j*64, i*64, null);
					break;
				case 3:
					g.drawImage(fullTarget.get(2), j*64, i*64, null);
					break;
				case 4:
					g.drawImage(fullTarget.get(3), j*64, i*64, null);
					break;
				case 5:
					g.drawImage(fullTarget.get(4), j*64, i*64, null);
					break;
				case 10:
					g.drawImage(mobileCrates.get(0), j*64, i*64, null);
					break;
				case 11:
					g.drawImage(mobileCrates.get(1), j*64, i*64, null);
					break;
				case 12:
					g.drawImage(mobileCrates.get(2), j*64, i*64, null);
					break;
				case 13:
					g.drawImage(mobileCrates.get(3), j*64, i*64, null);
					break;
				case 14:
					g.drawImage(mobileCrates.get(4), j*64, i*64, null);
					break;
				case 20:
					g.drawImage(staticCrates.get(0), j*64, i*64, null);
					break;
				case 21:
					g.drawImage(staticCrates.get(1), j*64, i*64, null);
					break;
				case 22:
					g.drawImage(staticCrates.get(2), j*64, i*64, null);
					break;
				case 23:
					g.drawImage(staticCrates.get(3), j*64, i*64, null);
					break;
				case 24:
					g.drawImage(staticCrates.get(4), j*64, i*64, null);
					break;
				case -10:
					g.drawImage(targets.get(0), j*64, i*64, null);
					break;
				case -11:
					g.drawImage(targets.get(1), j*64, i*64, null);
					break;
				case -12:
					g.drawImage(targets.get(2), j*64, i*64, null);
					break;
				case -13:
					g.drawImage(targets.get(3), j*64, i*64, null);
					break;
				case -14:
					g.drawImage(targets.get(4), j*64, i*64, null);
					break;
				
				}
				

			}
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
