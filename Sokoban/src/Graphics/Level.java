package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import Logic.GameManager;
import Logic.Map;
import Sound.SoundEffects;

/*pannello contenente un livello*/

public class Level extends JPanel implements KeyListener {

	private static final long serialVersionUID = -4320181682285658101L;
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
	MyImage stepsFinished = null;
	MyImage timeFinished = null;
	
	private boolean alreadyPlayed = false;
	SoundEffects steps = null;
	SoundEffects win = null;
	SoundEffects lose = null;
	int mode;
	boolean Key=false;
	int movementRight=0;
	int movementUp=0;
	int movementDown=0;
	int movementLeft=0;
	ArrayList<MyImage> numbers = null;
	MyImage stepsImage = null;
	MyImage slashImage = null;
	MyImage timeImage = null;
	int timer=0;
	
	
	public Level(Dimension d, int playerColour, int map, int mode) {
		super();
		this.d = d;
		this.mode = mode;
		fullTarget = new ArrayList<MyImage>();
		targets = new ArrayList<MyImage>();
		mobileCrates = new ArrayList<MyImage>();
		staticCrates = new ArrayList<MyImage>();
		this.addKeyListener(this);
		this.setFocusable(true);
		this.map = new Map("map"+map+".txt", mode);  //letture da mappe
		player = new GraphicsPlayer(playerColour);
		gameManager = new GameManager(this);
		grass = new MyImage(d, "Images"+File.separator+"Components"+File.separator+"grass.png", 0, 0, 64, 64); 
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
		
		victory = new MyImage(d, "Images"+File.separator+"LevelCompleted.png", 2, 300, 1366, 154);
		steps = new SoundEffects("Sounds"+File.separator+"steps.wav");
		win = new SoundEffects("Sounds"+File.separator+"win.wav");
		if(mode == 0) {
			background = new MyImage(d, "Images"+File.separator+"Backgrounds"+File.separator+"CmodeBackground.jpg", 0, 0, d.width, d.height);
		}
		else if(mode == 1) {
			background = new MyImage(d, "Images"+File.separator+"Backgrounds"+File.separator+"SmodeBackground.jpg", 0, 0, d.width, d.height);
			stepsImage = new MyImage(d, "Images"+File.separator+"Steps"+File.separator+"Passi.png", 0, 0, 150, 55);
			slashImage = new MyImage(d, "Images"+File.separator+"Steps"+File.separator+"Slash.png", 0, 0, 30, 55);
			numbers = new ArrayList<MyImage>();
			for(int i = 0; i < 10; i++) {
				MyImage image = new MyImage(d, "Images"+File.separator+"Steps"+File.separator+""+i+".png", 0, 0, 70, 55);
				numbers.add(image);
			}
			stepsFinished = new MyImage(d, "Images"+File.separator+"StepsFinished.png", 2, 300, 1366, 155);
			lose = new SoundEffects("Sounds"+File.separator+"lose.wav");
		}
		else if (mode == 2) {
			background = new MyImage(d, "Images"+File.separator+"Backgrounds"+File.separator+"TmodeBackground.jpg", 0, 0, d.width, d.height);
			timeImage = new MyImage(d, "Images"+File.separator+"Steps"+File.separator+"Time.png", 0, 0, 150, 55);
			numbers = new ArrayList<MyImage>();
			for(int i = 0; i < 10; i++) {
				MyImage image = new MyImage(d, "Images"+File.separator+"Steps"+File.separator+""+i+".png", 0, 0, 70, 55);
				numbers.add(image);
			}
			timeFinished = new MyImage(d, "Images"+File.separator+"TimeFinished.png", 2, 300, 1366, 155);
			lose = new SoundEffects("Sounds"+File.separator+"lose.wav");
		}
			
	
	}
	
	
	public int getMode() {
		return mode;
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Key=false;
		g.drawImage(background.image, 0, 0, getWidth(), getHeight(),null);
		
		//la larghezza e l'altezza di un qualsiasi componente
		int w = grass.width;
		int h = grass.height;
		int x = (int) (242*grass.scalex);
		int y = (int) (65*grass.scaley);
		
		//disegno il prato
		for(int i = 0; i < map.rows; i++) {
			for (int j = 0; j < map.columns; j++) {
				g.drawImage(grass.image, x+j*w, y+i*h, w, h, null);	
			}
		}
		
		//disegno i componenti
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
		
		
	    if(mode == 2) {
	    	//modalita a tempo
			int t = map.totalTime-map.time.getTime();
			int xk =  0;
			while(t > 0){
	            int k = t % 10;
	            g.drawImage(numbers.get(k).image,(int) ((1300 - xk) * grass.scalex),(int) (5 * grass.scaley), numbers.get(0).width, numbers.get(0).height, null);
	            t/=10;
	            xk+=35;
	        }
			xk+=85;
			g.drawImage(timeImage.image, (int) ((1300 - xk) * grass.scalex), (int) (0 * grass.scaley), timeImage.width, timeImage.height,null);     
	    }
	    
		//disegno il numero di passi
		 if(mode==1){ //sono nella modalità a passi
			 
			int v = map.getSteps();
	    	int xk = 0;
	    	//passi totali
	        while(v > 0){
	            int k = v % 10;
	            g.drawImage(numbers.get(k).image,(int) ((1300 - xk) * grass.scalex),(int) (5 * grass.scaley), numbers.get(0).width, numbers.get(0).height, null);
	            v/=10;
	            xk+=35;
	        }
	        //slash
	            g.drawImage(slashImage.image,(int) ((1300 - xk + 20) * grass.scalex),(int) (5 * grass.scaley), slashImage.width, slashImage.height, null); //slash
	            xk+=35;
	        
	        //passi attuali
	        v=map.getActualSteps();
	        if(v==0){
	            g.drawImage(numbers.get(v).image,(int) ((1300 - xk) * grass.scalex),(int) (5 * grass.scaley) ,numbers.get(0).width, numbers.get(0).height, null );
	            xk+=35;
	        }
	        else{
	            while(v > 0){
	                int k = v % 10;
	                g.drawImage(numbers.get(k).image,(int) ((1300 - xk) * grass.scalex),(int) (5 * grass.scaley),numbers.get(0).width, numbers.get(0).height, null);
	                v/=10;
	                xk+=35;
	            }
	        }
	        //steps:
	        xk+=(int)(100*grass.scalex);
	        g.drawImage(stepsImage.image,(int) ((1300 - xk) * grass.scalex),(int) (5 * grass.scaley), stepsImage.width, stepsImage.height,  null);
		 }
			
		//disegnare tempo finito oppure passi superati	
		if(map.isComplete()) {
			if(!alreadyPlayed) {
				win.playSound();
				alreadyPlayed = true;
			}
			g.drawImage(victory.image, victory.x, victory.y, victory.width, victory.height, null);
			timer++;
		}
		
		if(map.time!= null && map.time.getTime()>=map.totalTime) {
			if(!alreadyPlayed) {
				lose.playSound();
				alreadyPlayed = true;
			}
			g.drawImage(timeFinished.image, timeFinished.x, timeFinished.y, timeFinished.width, timeFinished.height, null);
			timer++;
		}
		
		if(map.actualSteps>=map.totalSteps) {
			if(!alreadyPlayed) {
				lose.playSound();
				alreadyPlayed = true;
			}
			g.drawImage(stepsFinished.image, stepsFinished.x, stepsFinished.y, stepsFinished.width, stepsFinished.height, null);
			timer++;
		}
			
		
		if(timer > 15) {
			//gioco finito, torno alla selezione del livello
			PrincipalFrame k = (PrincipalFrame) this.getTopLevelAncestor();
			GameSelection q = new GameSelection(d, this.mode);
			k.setAcutalPane(q);
			q.requestFocusInWindow();
		}
	
	}
		
	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(!map.isComplete() && timer == 0) {
			
			int event = arg0.getKeyCode();
			if(event==KeyEvent.VK_R) {
				map.reset();
			}
			else if(event==KeyEvent.VK_RIGHT && !Key) {
				Key=true;
				player.incrementMovement("right");
				gameManager.goRight();
				steps.playSound();
			}
			
			else if(event==KeyEvent.VK_LEFT && !Key) {
				Key=true;
				player.incrementMovement("left");
				gameManager.goLeft();
				steps.playSound();
			}
			
			else if(event==KeyEvent.VK_UP && !Key) {
				Key=true;
				player.incrementMovement("up");
				gameManager.goUp();
				steps.playSound();
			}
			
			else if(event==KeyEvent.VK_DOWN && !Key) {
				Key=true;
				player.incrementMovement("down");
				gameManager.goDown();
				steps.playSound();
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getExtendedKeyCode()==KeyEvent.VK_ESCAPE) {
			PrincipalFrame k = (PrincipalFrame) this.getTopLevelAncestor();
			if(k.getActualPane() == this) {
			GameSelection q = new GameSelection(d, this.mode);
			k.setAcutalPane(q);
			q.requestFocusInWindow();
			}
		}
		if(event.getExtendedKeyCode()==KeyEvent.VK_RIGHT || event.getExtendedKeyCode()==KeyEvent.VK_LEFT || event.getExtendedKeyCode()==KeyEvent.VK_UP || event.getExtendedKeyCode()==KeyEvent.VK_DOWN) player.resetMovement();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	

}
