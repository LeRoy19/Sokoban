package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.io.File;

import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Logic.LogicEditor;
import Sound.SoundEffects;

public class Editor extends JPanel implements MouseMotionListener, MouseListener{

	private static final long serialVersionUID = -5665825017381029407L;
	LogicEditor logicEditor = null;
	MyImage background = null;
	int actualSCrate = 0;
	int actualMcrate = 0;
	int actualTarget = 0;
	MyImage SCrateLeftArrow = null;
	MyImage SCrateRightArrow = null;
	MyImage MCrateLeftArrow = null;
	MyImage MCrateRightArrow = null;
	MyImage targetLeftArrow = null;
	MyImage targetRightArrow = null;
	MyImage stepsLeftArrow = null;
	MyImage stepsRightArrow = null;
	ArrayList<MyImage> targets = null;
	ArrayList<MyImage> mobileCrates = null;
	ArrayList<MyImage> staticCrates = null;
	ArrayList<MyImage> availableTargets = null;
	ArrayList<MyImage> stepsNumbers = null;
	MyImage actualImage = null;
	MyImage homeButton = null;
	MyImage saveButton = null;
	MyImage trashButton = null;
	MyImage steps = null;
	int actualValue = 0;
	boolean drag = false;
	int mousex, mousey;
	private Dimension d = null;
	
	int stepsNumber = 0;
	
	
	SoundEffects click = null;
	
	public Editor(Dimension d) {
		this.d = d;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		logicEditor = new LogicEditor();
		targets = new ArrayList<MyImage>();
		mobileCrates = new ArrayList<MyImage>();
		staticCrates = new ArrayList<MyImage>();
		availableTargets = new ArrayList<MyImage>();
		stepsNumbers = new ArrayList<MyImage>();
		SCrateLeftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 100, 168, 40, 30);
		SCrateRightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 244, 168, 40, 30);
		MCrateLeftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 100, 288, 40, 30);
		MCrateRightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 244, 288, 40, 30);
		targetLeftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 100, 408, 40, 30);
		targetRightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 244, 408, 40, 30);
		stepsLeftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 165, 533, 40, 30);
		stepsRightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 315, 533, 40, 30);
		
		click = new SoundEffects("Sounds"+File.separator+"click1.wav");
		try {
			for(int i = 1; i <= 15; i++) {
				Image j = ImageIO.read(new File("Images"+File.separator+"Components" + File.separator+i+".png"));
				if(i<=5) {
					MyImage t = new MyImage(d, j, 160, 275, 64, 64);
					mobileCrates.add(t);
					stepsNumbers.add(new MyImage(d,"Images"+File.separator+"Steps"+File.separator+""+(i-1)+".png", 0,0,70,55));
				}
				else if (i<=10) {
					MyImage t = new MyImage(d, j, 160, 155, 64, 64);
					staticCrates.add(t);
					stepsNumbers.add(new MyImage(d,"Images"+File.separator+"Steps"+File.separator+""+(i-1)+".png", 0,0,70,55));
				}
				else {
					MyImage t = new MyImage(d, j, 160, 395, 64, 64);
					targets.add(t);
				}
			}
			background = new MyImage(d, "Images"+File.separator+"Backgrounds"+File.separator+"editor.jpg", 0 , 0, d.width, d.height);
			saveButton = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"save.png", 250, 20, 64, 64);
			homeButton = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"home.png", 30, 20, 64, 64);
			trashButton = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"trash.png", 140, 20, 64, 64);
			steps = new MyImage(d, "Images"+File.separator+"Steps"+File.separator+"Passi.png", 10, 520, 150, 55);
			
		} catch (Exception e) {
			System.out.println("Errore nel caricamento delle immagini dell'editor");
		}
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background.image, background.x, background.y, background.width, background.height, null);
		g.drawImage(saveButton.image, saveButton.x, saveButton.y, saveButton.width, saveButton.height, null);
		g.drawImage(homeButton.image, homeButton.x, homeButton.y, homeButton.width, homeButton.height,null);
		g.drawImage(trashButton.image, trashButton.x, trashButton.y, trashButton.width, trashButton.height, null);
	
		g.drawImage(staticCrates.get(actualSCrate).image, staticCrates.get(actualSCrate).x, staticCrates.get(actualSCrate).y, staticCrates.get(actualSCrate).width, staticCrates.get(actualSCrate).height, null);
		g.drawImage(SCrateLeftArrow.image, SCrateLeftArrow.x, SCrateLeftArrow.y, SCrateLeftArrow.width, SCrateLeftArrow.height, null);
		g.drawImage(SCrateRightArrow.image, SCrateRightArrow.x, SCrateRightArrow.y, SCrateRightArrow.width, SCrateRightArrow.height, null);
		
		g.drawImage(mobileCrates.get(actualMcrate).image,mobileCrates.get(actualMcrate).x, mobileCrates.get(actualMcrate).y,mobileCrates.get(actualMcrate).width,mobileCrates.get(actualMcrate).height, null);
		g.drawImage(MCrateLeftArrow.image, MCrateLeftArrow.x, MCrateLeftArrow.y, MCrateLeftArrow.width, MCrateLeftArrow.height, null);
		g.drawImage(MCrateRightArrow.image, MCrateRightArrow.x, MCrateRightArrow.y, MCrateRightArrow.width, MCrateRightArrow.height, null);
		g.drawImage(stepsLeftArrow.image, stepsLeftArrow.x, stepsLeftArrow.y, stepsLeftArrow.width, stepsLeftArrow.height, null);
		g.drawImage(stepsRightArrow.image, stepsRightArrow.x, stepsRightArrow.y, stepsRightArrow.width, stepsRightArrow.height, null);
		g.drawImage(steps.image, steps.x, steps.y, steps.width, steps.height, null);
		//disegno i target se disponibili dopo che ho inserito un blocco
		if(availableTargets.size()>0) {
			g.drawImage(availableTargets.get(actualTarget).image, availableTargets.get(actualTarget).x, availableTargets.get(actualTarget).y, availableTargets.get(actualTarget).width,  availableTargets.get(actualTarget).height, null);
			g.drawImage(targetLeftArrow.image, targetLeftArrow.x, targetLeftArrow.y, targetLeftArrow.width, targetLeftArrow.height, null);
			g.drawImage(targetRightArrow.image, targetRightArrow.x, targetRightArrow.y, targetRightArrow.width, targetRightArrow.height, null);
		}
		
		MyImage grass = null;
	    grass = new MyImage(d, "Images"+File.separator+"grass.png", 0, 0, 64, 64);
	    
		int x = (int) (380*grass.scalex);
		int y = (int) (70*grass.scaley);
		int w = grass.width;
		int h = grass.height;
		
		//disegno il prato
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 14; j++) {
				g.drawImage(grass.image, x+(j*grass.width), y+(i*grass.height), grass.width, grass.height, null);
			}
		}
		//disegno i blocchi in base al valore della matrice
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 14; j++) {
				switch(logicEditor.matrix[i][j]) {
				case 10:
					g.drawImage(mobileCrates.get(0).image, x+j*w, y+i*h, w, h, null);
					break;
				case 11:
					g.drawImage(mobileCrates.get(1).image, x+j*w, y+i*h, w, h, null);
					break;
				case 12:
					g.drawImage(mobileCrates.get(2).image, x+j*w, y+i*h, w, h, null);
					break;
				case 13:
					g.drawImage(mobileCrates.get(3).image, x+j*w, y+i*h, w, h, null);
					break;
				case 14:
					g.drawImage(mobileCrates.get(4).image, x+j*w, y+i*h, w, h, null);
					break;
				case 20:
					g.drawImage(staticCrates.get(0).image,x+j*w, y+i*h, w, h,null);
					break;
				case 21:
					g.drawImage(staticCrates.get(1).image,x+j*w, y+i*h, w, h,null);
					break;
				case 22:
					g.drawImage(staticCrates.get(2).image,x+j*w, y+i*h, w, h,null);
					break;
				case 23:
					g.drawImage(staticCrates.get(3).image,x+j*w, y+i*h, w, h,null);
					break;
				case 24:
					g.drawImage(staticCrates.get(4).image,x+j*w, y+i*h, w, h,null);
					break;
				case -10:
					g.drawImage(targets.get(0).image, x+j*w, y+i*h, w, h,null);
					break;
				case -11:
					g.drawImage(targets.get(1).image, x+j*w, y+i*h, w, h,null);
					break;
				case -12:
					g.drawImage(targets.get(2).image, x+j*w, y+i*h, w, h,null);
					break;
				case -13:
					g.drawImage(targets.get(3).image, x+j*w, y+i*h, w, h,null);
					break;
				case -14:
					g.drawImage(targets.get(4).image, x+j*w, y+i*h, w, h,null);
					break;
				default:
					break;				
				}
			}
		}
		
		if(drag) {
			g.drawImage(actualImage.image, mousex-actualImage.width/2, mousey-actualImage.height/2, actualImage.width, actualImage.height, null);
		}
		
		//disegno il numero di passi attuale
		int v = stepsNumber;
    	int xk = 0;
    	if(v==0){
            g.drawImage(stepsNumbers.get(v).image,(int) ((258 - xk) * grass.scalex),(int) (520 * grass.scaley) ,stepsNumbers.get(0).width, stepsNumbers.get(0).height, null );
        }
    	else {
	        while(v > 0){
	            int k = v % 10;
	            g.drawImage(stepsNumbers.get(k).image,(int) ((258 - xk) * grass.scalex),(int) (520 * grass.scaley), stepsNumbers.get(0).width, stepsNumbers.get(0).height, null);
	            v/=10;
	            xk+=35;
	        }
    	}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY(); 
	
		if(x>=SCrateRightArrow.x && x <= SCrateRightArrow.x+SCrateRightArrow.width && y>=SCrateRightArrow.y && y <= SCrateRightArrow.y+SCrateRightArrow.height){
			if(actualSCrate==staticCrates.size()-1) actualSCrate=0;
			else actualSCrate++;
			click.playSound();
		}
		
		if(x>=SCrateLeftArrow.x && x <= SCrateLeftArrow.x+SCrateLeftArrow.width && y>=SCrateLeftArrow.y && y <= SCrateLeftArrow.y+SCrateLeftArrow.height){
			if(actualSCrate==0) actualSCrate=staticCrates.size()-1;
			else actualSCrate--;
			click.playSound();
		}
		
		if(x>=MCrateRightArrow.x && x <= MCrateRightArrow.x+MCrateRightArrow.width && y>=MCrateRightArrow.y && y <= MCrateRightArrow.y+MCrateRightArrow.height){
			if(actualMcrate==mobileCrates.size()-1) actualMcrate=0;
			else actualMcrate++;
			click.playSound();
		}
		
		if(x>=MCrateLeftArrow.x && x <= MCrateLeftArrow.x+MCrateLeftArrow.width && y>=MCrateLeftArrow.y && y <= MCrateLeftArrow.y+MCrateLeftArrow.height){
			if(actualMcrate==0) actualMcrate=mobileCrates.size()-1;
			else actualMcrate--;
			click.playSound();
		}
		
		if(x>=targetRightArrow.x && x <= targetRightArrow.x+targetRightArrow.width && y>=targetRightArrow.y && y <= targetRightArrow.y+targetRightArrow.y){
			if(actualTarget==availableTargets.size()-1) actualTarget=0;
			else actualTarget++;
			click.playSound();
		}
		
		if(x>=targetLeftArrow.x && x <= targetLeftArrow.x+targetLeftArrow.width && y>=targetLeftArrow.y && y <= targetLeftArrow.y+targetLeftArrow.height){
			if(actualTarget==0) actualTarget=availableTargets.size()-1;
			else actualTarget--;
			click.playSound();
		}
		
		if(x>=stepsLeftArrow.x && x <= stepsLeftArrow.x+stepsLeftArrow.width && y>=stepsLeftArrow.y && y <= stepsLeftArrow.y+stepsLeftArrow.height){
			if(stepsNumber>0) {  stepsNumber--; logicEditor.steps--; }
			
			click.playSound();
		}
		
		if(x>=stepsRightArrow.x && x <= stepsRightArrow.x+stepsRightArrow.width && y>=stepsRightArrow.y && y <= stepsRightArrow.y+stepsRightArrow.height){
			if(stepsNumber<999) {stepsNumber++; logicEditor.steps++; }
			click.playSound();
		}
		
		if(x >= trashButton.x && x <= trashButton.x + trashButton.width && y >= trashButton.y && y <= trashButton.y + trashButton.height) {//sono nel trash button
			click.playSound();
			logicEditor.clearMap();
			availableTargets.clear();
			actualTarget=0;
			
		}
		
		if(x >= homeButton.x && x <= homeButton.x + homeButton.width && y >= homeButton.y && y <= homeButton.y + homeButton.height) { //sono nell home button
			click.playSound();
			PrincipalFrame f = (PrincipalFrame) this.getTopLevelAncestor();
			Gui g = new Gui(f.getSize());
			f.setAcutalPane(g);
			g.requestFocusInWindow();
		}
		
		
		if(x >= saveButton.x && x <= saveButton.x + saveButton.width && y >= saveButton.y && y <= saveButton.y + saveButton.height) { //sono nel save button
			click.playSound();
			if(logicEditor.SaveMap()) {
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Map saved!");
			}
			else {
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Map not saved, check targets or steps!");
			}
		}
	}



	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		//ho cliccato in un blocco statico
		if(x>=staticCrates.get(0).x && x<=staticCrates.get(0).x + staticCrates.get(0).width && y>=staticCrates.get(0).y && y<=staticCrates.get(0).y + staticCrates.get(0).height){
			drag = true;
			actualValue = 20+actualSCrate;
			actualImage = staticCrates.get(actualSCrate);
			click.playSound();
		}
		//ho cliccato in un blocco mobile
		if(x>=mobileCrates.get(0).x && x<=mobileCrates.get(0).x + mobileCrates.get(0).width && y>=mobileCrates.get(0).y && y<=mobileCrates.get(0).y + mobileCrates.get(0).height){
			drag = true;
			actualValue = 10+actualMcrate;
			actualImage = mobileCrates.get(actualMcrate);
			click.playSound();
		}
		
		//ho cliccato in un target
		if(x>=targets.get(0).x && x<=targets.get(0).x + targets.get(0).width && y>=targets.get(0).y && y<=targets.get(0).y + targets.get(0).height && !availableTargets.isEmpty()){
			drag = true;
			for (int i = 0; i<targets.size(); i++) {
				if(targets.get(i)==availableTargets.get(actualTarget)) {
					actualValue = -10-i;
				}
			}
			actualImage = availableTargets.get(actualTarget);
			click.playSound();
		}
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		
		int x = (int) (350*staticCrates.get(0).scalex);
		int y = (int) (70*staticCrates.get(0).scaley);
		int w = staticCrates.get(0).width; // la larghezza di un blocco
		int h = staticCrates.get(0).height; //l'altezza di un blocco
		for (int i = 0; i < 10; i++) { //se sono nella matrice
			for (int j = 0; j < 14; j++) {
				if(mousex>= x + (j*w) && mousex < (x + ((j+1)*w)) && mousey >= y +(i*h) && mousey < y + (i+1)*h){
					if(logicEditor.matrix[i][j]==0 && drag) { //se non ho gia aggiunto qualcosa
						logicEditor.matrix[i][j] = actualValue;
						if(actualValue>=10 && actualValue<20) {
							availableTargets.add(targets.get(actualValue-10));	
							logicEditor.availableTargets++;
						}
						
						if(actualValue<0) {
							if(!availableTargets.isEmpty()) {
								availableTargets.remove(actualTarget);
								actualTarget=0;
								logicEditor.availableTargets--;
							}
							else logicEditor.matrix[i][j] = 0;
						}
				
					}
				}
			}
		}
		drag = false;
	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(drag) {
			mousex = arg0.getX();
			mousey = arg0.getY();		
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		mousex = arg0.getX();
		mousey = arg0.getY(); 
		
	}

		
	
}
