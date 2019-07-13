package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Editor extends JPanel implements MouseMotionListener, MouseListener{

	private static final long serialVersionUID = -5665825017381029407L;
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
	ArrayList<MyImage> targets = null;
	ArrayList<MyImage> mobileCrates = null;
	ArrayList<MyImage> staticCrates = null;
	ArrayList<MyImage> availableTargets = null;
	MyImage actualImage = null;
	MyImage homeButton = null;
	MyImage saveButton = null;
	MyImage trashButton = null;
	int actualValue = 0;
	int matrix [][] = null;
	boolean drag = false;
	int mousex, mousey;
	private Dimension d = null;
	
	public Editor(Dimension d) {
		this.d = d;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		matrix = new int[10][14];
		targets = new ArrayList<MyImage>();
		mobileCrates = new ArrayList<MyImage>();
		staticCrates = new ArrayList<MyImage>();
		availableTargets = new ArrayList<MyImage>();
		SCrateLeftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 90, 168, 40, 30);
		SCrateRightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 234, 168, 40, 30);
		MCrateLeftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 90, 368, 40, 30);
		MCrateRightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 234, 368, 40, 30);
		targetLeftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 90, 568, 40, 30);
		targetRightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 234, 568, 40, 30);
		try {
			for(int i = 1; i <= 15; i++) {
				Image j = ImageIO.read(new File("Images"+File.separator+"Components" + File.separator+i+".png"));
				if(i<=5) {
					MyImage t = new MyImage(d, j, 150, 350, 64, 64);
					mobileCrates.add(t);
				}
				else if (i<=10) {
					MyImage t = new MyImage(d, j, 150, 150, 64, 64);
					staticCrates.add(t);
				}
				else {
					MyImage t = new MyImage(d, j, 150, 550, 64, 64);
					targets.add(t);
				}
			}
			background = new MyImage(d, "Images"+File.separator+"Backgrounds"+File.separator+"editor.jpg", 0 , 0, d.width, d.height);
			saveButton = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"save.png", 250, 20, 64, 64);
			homeButton = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"home.png", 30, 20, 64, 64);
			trashButton = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"trash.png", 140, 20, 64, 64);
			
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
		
		//disegno i target se disponibili dopo che ho inserito un blocco
		if(availableTargets.size()>0) {
			g.drawImage(availableTargets.get(actualTarget).image, availableTargets.get(actualTarget).x, availableTargets.get(actualTarget).y, availableTargets.get(actualTarget).width,  availableTargets.get(actualTarget).height, null);
			g.drawImage(targetLeftArrow.image, targetLeftArrow.x, targetLeftArrow.y, targetLeftArrow.width, targetLeftArrow.height, null);
			g.drawImage(targetRightArrow.image, targetRightArrow.x, targetRightArrow.y, targetRightArrow.width, targetRightArrow.height, null);
		}
		
		MyImage grass = null;
	    grass = new MyImage(d, "Images"+File.separator+"grass.png", 0, 0, 64, 64);

	    
		int x = (int) (350*grass.scalex);
		int y = (int) (70*grass.scaley);
		int w = grass.width;
		int h = grass.height;
		
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 14; j++) {
				g.drawImage(grass.image, x+(j*grass.width), y+(i*grass.height), grass.width, grass.height, null);
			}
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 14; j++) {
				switch(matrix[i][j]) {
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
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY(); 
	
		if(x>=SCrateRightArrow.x && x <= SCrateRightArrow.x+SCrateRightArrow.width && y>=SCrateRightArrow.y && y <= SCrateRightArrow.y+SCrateRightArrow.height){
			if(actualSCrate==staticCrates.size()-1) actualSCrate=0;
			else actualSCrate++;
		}
		
		if(x>=SCrateLeftArrow.x && x <= SCrateLeftArrow.x+SCrateLeftArrow.width && y>=SCrateLeftArrow.y && y <= SCrateLeftArrow.y+SCrateLeftArrow.height){
			if(actualSCrate==0) actualSCrate=staticCrates.size()-1;
			else actualSCrate--;
		}
		
		if(x>=MCrateRightArrow.x && x <= MCrateRightArrow.x+MCrateRightArrow.width && y>=MCrateRightArrow.y && y <= MCrateRightArrow.y+MCrateRightArrow.height){
			if(actualMcrate==mobileCrates.size()-1) actualMcrate=0;
			else actualMcrate++;
		}
		
		if(x>=MCrateLeftArrow.x && x <= MCrateLeftArrow.x+MCrateLeftArrow.width && y>=MCrateLeftArrow.y && y <= MCrateLeftArrow.y+MCrateLeftArrow.height){
			if(actualMcrate==0) actualMcrate=mobileCrates.size()-1;
			else actualMcrate--;
		}
		
		if(x>=targetRightArrow.x && x <= targetRightArrow.x+targetRightArrow.width && y>=targetRightArrow.y && y <= targetRightArrow.y+targetRightArrow.y){
			if(actualTarget==availableTargets.size()-1) actualTarget=0;
			else actualTarget++;
		}
		
		if(x>=targetLeftArrow.x && x <= targetLeftArrow.x+targetLeftArrow.width && y>=targetLeftArrow.y && y <= targetLeftArrow.y+targetLeftArrow.height){
			if(actualTarget==0) actualTarget=availableTargets.size()-1;
			else actualTarget--;
		}
		
		if(x >= trashButton.x && x <= trashButton.x + trashButton.width && y >= trashButton.y && y <= trashButton.y + trashButton.height) {//sono nel trash button
			for(int i=0; i<10; i++) {
				for(int j=0; j<14; j++) {
					matrix[i][j]=0;
				}
			}
			availableTargets.clear();
			actualTarget=0;
		}
		
		if(x >= homeButton.x && x <= homeButton.x + homeButton.width && y >= homeButton.y && y <= homeButton.y + homeButton.height) { //sono nell home button
			PrincipalFrame f = (PrincipalFrame) this.getTopLevelAncestor();
			Gui g = new Gui(f.getSize());
			f.setAcutalPane(g);
			g.requestFocusInWindow();
		}
		
		
		if(x >= saveButton.x && x <= saveButton.x + saveButton.width && y >= saveButton.y && y <= saveButton.y + saveButton.height) { //sono nel save button
			int map = 0;
			if(availableTargets.isEmpty()) {
			try {
				BufferedReader bIn = new BufferedReader(new FileReader("NumMaps.txt"));
				while(bIn.ready()) {
					String line = bIn.readLine();
					map = Integer.parseInt(line);
					map++;
				}
				bIn.close();
			
				BufferedWriter bOut = new BufferedWriter(new FileWriter("map"+(map-1)+".txt"));
				BufferedWriter bOut2 = new BufferedWriter(new FileWriter("NumMaps.txt"));
				bOut2.append(Integer.toString(map));
				bOut2.close();
				bOut.append("10");
				bOut.newLine();
				bOut.append("14");
				bOut.newLine();
				for (int i = 0; i < 10; i++) {
					StringBuilder riga = new StringBuilder();
					for (int j = 0; j < 14; j++) {
						riga.append(Integer.toString(matrix[i][j])+" ");
					}
					bOut.append(riga.toString());
					bOut.newLine();
				}
				bOut.close();
				System.out.println("Mappa Salvata");
				}
				catch(IOException k) {
					k.printStackTrace();
				}
			}
			//se non è vuota dire che non si puo risovere
			else {
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Not all targets were placed");
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
		}
		//ho cliccato in un blocco mobile
		if(x>=mobileCrates.get(0).x && x<=mobileCrates.get(0).x + mobileCrates.get(0).width && y>=mobileCrates.get(0).y && y<=mobileCrates.get(0).y + mobileCrates.get(0).height){
			drag = true;
			actualValue = 10+actualMcrate;
			actualImage = mobileCrates.get(actualMcrate);
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
					if(matrix[i][j]==0 && drag) { //se non ho gia aggiunto qualcosa
						matrix[i][j] = actualValue;
						if(actualValue>=10 && actualValue<20) {
							availableTargets.add(targets.get(actualValue-10));	
						}
						
						if(actualValue<0) {
							if(!availableTargets.isEmpty()) {
								availableTargets.remove(actualTarget);
								actualTarget=0;
							}
							else matrix[i][j] = 0;
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
		// TODO Auto-generated method stub
		mousex = arg0.getX();
		mousey = arg0.getY(); 
		
	}

		
	
}
