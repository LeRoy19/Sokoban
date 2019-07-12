package Graphics;

import java.awt.Dimension;
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
	MyImage background = null;
	MyImage grass = null;
	ArrayList<MyImage> targets = null;
	ArrayList<MyImage> mobileCrates = null;
	ArrayList<MyImage> staticCrates = null;
	ArrayList<MyImage> fullTarget = null;
	Dimension d = null;
	MyImage victory = null;
	
	int timer=0;
	
	
	public Level(Dimension d, int playerColour, int map) {
		this.d = d;
		fullTarget = new ArrayList<MyImage>();
		targets = new ArrayList<MyImage>();
		mobileCrates = new ArrayList<MyImage>();
		staticCrates = new ArrayList<MyImage>();
		this.addKeyListener(this);
		this.setFocusable(true);
		this.map = new Map("map"+map+".txt");  //letture da mappe
		player = new GraphicsPlayer(playerColour);
		gameManager = new GameManager(this);
		try {
			background = new MyImage(d, "Images"+File.separator+"background.jpg", 0, 0, d.width, d.height);
			grass = new MyImage(d, "Images"+File.separator+"grass.png", 0, 0, 64, 64); 
			for(int i = 1; i <= 15; i++) {
				MyImage t = new MyImage(d, "Images"+File.separator+"Components" + File.separator+i+".png", 0, 0, 64, 64);
				if(i<=5) {
					//buchi pieni
					MyImage x = new MyImage(d, "Images"+File.separator+"Components" + File.separator+"e"+i+".png", 0, 0, 64, 64);
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
		victory = new MyImage(d, "Images"+File.separator+"LevelCompleted (2).png", 2, 300, 1366, 154);
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.image, 0, 0, getWidth(), getHeight(),null);
		//la larghezza e l'altezza di un qualsiasi componente
		int w = grass.width;
		int h = grass.height;
		int x = (int) (242*grass.scalex);
		int y = (int) (65*grass.scaley);
		
		for(int i = 0; i < map.rows; i++) {
			for (int j = 0; j < map.columns; j++) {
				g.drawImage(grass.image, x+j*w, y+i*h, w, h, null);	
			}
		}
		
		for(int i = 0; i < map.targets.size(); i++) {
			switch(map.targets.get(i).getActualValue()) {
			case 1:
				g.drawImage(fullTarget.get(0).image, x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;
			case 2:
				g.drawImage(fullTarget.get(1).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;
			case 3:
				g.drawImage(fullTarget.get(2).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;
			case 4:
				g.drawImage(fullTarget.get(3).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;
			case 5:
				g.drawImage(fullTarget.get(4).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;
			case -10:
				g.drawImage(targets.get(0).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h,null);
				break;
			case -11:
				g.drawImage(targets.get(1).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;
			case -12:
				g.drawImage(targets.get(2).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;
			case -13:
				g.drawImage(targets.get(3).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;
			case -14:
				g.drawImage(targets.get(4).image,x+ map.targets.get(i).getJ()*w,y+ map.targets.get(i).getI()*h, w, h, null);
				break;	
			}	
		}
		
		for(int i = 0; i < map.rows; i++) {
			for (int j = 0; j < map.columns; j++) {
				switch(map.matrix[i][j]) {
				case 10:
					g.drawImage(mobileCrates.get(0).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 11:
					g.drawImage(mobileCrates.get(1).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 12:
					g.drawImage(mobileCrates.get(2).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 13:
					g.drawImage(mobileCrates.get(3).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 14:
					g.drawImage(mobileCrates.get(4).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 20:
					g.drawImage(staticCrates.get(0).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 21:
					g.drawImage(staticCrates.get(1).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 22:
					g.drawImage(staticCrates.get(2).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 23:
					g.drawImage(staticCrates.get(3).image,x+ j*w,y+ i*h, w, h, null);
					break;
				case 24:
					g.drawImage(staticCrates.get(4).image,x+ j*w,y+ i*h, w, h, null);
					break;
				}
			}
		}
		
		//disegno il player
		g.drawImage(player.getImgCorrente(),x+(int) (map.player.getX()*grass.scalex) ,y+(int) (map.player.getY()*grass.scaley), w, h, null);
		
		
		if(map.isComplete()) {
			g.drawImage(victory.image, victory.x, victory.y, victory.width, victory.height, null);
			timer++;
		}
		
		if(timer > 10) {
			//change panel
			PrincipalFrame k = (PrincipalFrame) this.getTopLevelAncestor();
			GameSelection q = new GameSelection(d);
			k.setAcutalPane(q);
			q.requestFocusInWindow();
		}
}
		
	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(!map.isComplete()) {
			int event = arg0.getKeyCode();
			if(event==KeyEvent.VK_RIGHT) {
				gameManager.goRight();
			}
			
			if(event==KeyEvent.VK_LEFT) {
				gameManager.goLeft();
			}
			
			if(event==KeyEvent.VK_UP) {
				gameManager.goUp();
			}
			
			if(event==KeyEvent.VK_DOWN) {
				gameManager.goDown();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
