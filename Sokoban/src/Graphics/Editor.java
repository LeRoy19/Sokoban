package Graphics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Editor extends JPanel implements MouseMotionListener, MouseListener{

	private static final long serialVersionUID = -5665825017381029407L;
	Image background = null;
	int actualSCrate = 0;
	int actualMcrate = 0;
	int actualTarget = 0;
	GraphicsButton SCrateLeftArrow = null;
	GraphicsButton SCrateRightArrow = null;
	GraphicsButton MCrateLeftArrow = null;
	GraphicsButton MCrateRightArrow = null;
	GraphicsButton targetLeftArrow = null;
	GraphicsButton targetRightArrow = null;
	ArrayList<Image> targets = null;
	ArrayList<Image> mobileCrates = null;
	ArrayList<Image> staticCrates = null;
	ArrayList<Image> availableTargets = null;
	Image actualImage = null;
	Image homeButton = null;
	Image saveButton = null;
	Image trashButton = null;
	int actualValue = 0;
	int matrix [][] = null;
	boolean drag = false;
	int mousex, mousey;
	
	
	public Editor() {
		this.setLayout(new BorderLayout(0, 0));
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		matrix = new int[10][14];
		targets = new ArrayList<Image>();
		mobileCrates = new ArrayList<Image>();
		staticCrates = new ArrayList<Image>();
		availableTargets = new ArrayList<Image>();
		SCrateLeftArrow = new GraphicsButton("LeftArrow.png", "LeftArrow.png", 90, 168, 40, 30);
		SCrateRightArrow = new GraphicsButton("RightArrow.png", "RightArrow.png", 234, 168, 40, 30);
		MCrateLeftArrow = new GraphicsButton("LeftArrow.png", "LeftArrow.png", 90, 368, 40, 30);
		MCrateRightArrow = new GraphicsButton("RightArrow.png", "RightArrow.png", 234, 368, 40, 30);
		targetLeftArrow = new GraphicsButton("LeftArrow.png", "LeftArrow.png", 90, 568, 40, 30);
		targetRightArrow = new GraphicsButton("RightArrow.png", "RightArrow.png", 234, 568, 40, 30);
		try {
			for(int i = 1; i <= 15; i++) {
				Image t = ImageIO.read(new File("Images"+File.separator+"Components" + File.separator+i+".png"));
				if(i<=5)
					mobileCrates.add(t);
				else if (i<=10)
					staticCrates.add(t);
				else
					targets.add(t);
			}
			background = ImageIO.read(new File("Images"+File.separator+"Backgrounds"+File.separator+"editor.jpg"));
			saveButton = ImageIO.read(new File("Images"+File.separator+"Buttons"+File.separator+"save.png"));
			homeButton = ImageIO.read(new File("Images"+File.separator+"Buttons"+File.separator+"home.png"));
			trashButton = ImageIO.read(new File("Images"+File.separator+"Buttons"+File.separator+"trash.png"));
			
		} catch (Exception e) {
			System.out.println("Erroro nel caricamento delle immagini dell'editor");
		}
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(saveButton, 250, 20, null);
		g.drawImage(homeButton, 30, 20, null);
		g.drawImage(trashButton, 140, 20, null);
	
		g.drawImage(staticCrates.get(actualSCrate), 150, 150, null);
		g.drawImage(SCrateLeftArrow.getUnpressed(), SCrateLeftArrow.getX(), SCrateLeftArrow.getY(), SCrateLeftArrow.getWidth(), SCrateLeftArrow.getHeight(), null);
		g.drawImage(SCrateRightArrow.getUnpressed(), SCrateRightArrow.getX(), SCrateRightArrow.getY(), SCrateRightArrow.getWidth(), SCrateRightArrow.getHeight(), null);
		
		g.drawImage(mobileCrates.get(actualMcrate), 150, 350, null);
		g.drawImage(MCrateLeftArrow.getUnpressed(), MCrateLeftArrow.getX(), MCrateLeftArrow.getY(), MCrateLeftArrow.getWidth(), MCrateLeftArrow.getHeight(), null);
		g.drawImage(MCrateRightArrow.getUnpressed(), MCrateRightArrow.getX(), MCrateRightArrow.getY(), MCrateRightArrow.getWidth(), MCrateRightArrow.getHeight(), null);
		
		//disegno i target se disponibili dopo che ho inserito un blocco
		if(availableTargets.size()>0) {
			g.drawImage(availableTargets.get(actualTarget), 150, 550, null);
			g.drawImage(targetLeftArrow.getUnpressed(), targetLeftArrow.getX(), targetLeftArrow.getY(), targetLeftArrow.getWidth(), targetLeftArrow.getHeight(), null);
			g.drawImage(targetRightArrow.getUnpressed(), targetRightArrow.getX(), targetRightArrow.getY(), targetRightArrow.getWidth(), targetRightArrow.getHeight(), null);
			
		}
		
		Image grass = null;
		try {
			grass = ImageIO.read(new File("Images"+File.separator+"grass.png"));
		} catch (Exception e) {
		
		}
		int x = 350;
		int y = 70;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 14; j++) {
				g.drawImage(grass, x+(j*64), y+(i*64), null);
			}
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 14; j++) {
				switch(matrix[i][j]) {
				case 10:
					g.drawImage(mobileCrates.get(0), x+j*64, y+i*64, null);
					break;
				case 11:
					g.drawImage(mobileCrates.get(1), x+j*64, y+i*64, null);
					break;
				case 12:
					g.drawImage(mobileCrates.get(2), x+j*64, y+i*64, null);
					break;
				case 13:
					g.drawImage(mobileCrates.get(3), x+j*64, y+i*64, null);
					break;
				case 14:
					g.drawImage(mobileCrates.get(4), x+j*64, y+i*64, null);
					break;
				case 20:
					g.drawImage(staticCrates.get(0), x+j*64, y+i*64, null);
					break;
				case 21:
					g.drawImage(staticCrates.get(1), x+j*64, y+i*64, null);
					break;
				case 22:
					g.drawImage(staticCrates.get(2), x+j*64, y+i*64, null);
					break;
				case 23:
					g.drawImage(staticCrates.get(3), x+j*64, y+i*64, null);
					break;
				case 24:
					g.drawImage(staticCrates.get(4), x+j*64, y+i*64, null);
					break;
				case -10:
					g.drawImage(targets.get(0), x+j*64, y+i*64, null);
					break;
				case -11:
					g.drawImage(targets.get(1), x+j*64, y+i*64, null);
					break;
				case -12:
					g.drawImage(targets.get(2), x+j*64, y+i*64, null);
					break;
				case -13:
					g.drawImage(targets.get(3), x+j*64, y+i*64, null);
					break;
				case -14:
					g.drawImage(targets.get(4), x+j*64, y+i*64, null);
					break;
				default:
					break;
						
				
				}
			}
		}
		
		if(drag) {
			g.drawImage(actualImage, mousex-32, mousey-32, null);
		}
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY(); 
	
		if(x>=SCrateRightArrow.getX() && x <= SCrateRightArrow.getX()+SCrateRightArrow.getWidth() && y>=SCrateRightArrow.getY() && y <= SCrateRightArrow.getY()+SCrateRightArrow.getHeight()){
			if(actualSCrate==staticCrates.size()-1) actualSCrate=0;
			else actualSCrate++;
		}
		
		if(x>=SCrateLeftArrow.getX() && x <= SCrateLeftArrow.getX()+SCrateLeftArrow.getWidth() && y>=SCrateLeftArrow.getY() && y <= SCrateLeftArrow.getY()+SCrateLeftArrow.getHeight()){
			if(actualSCrate==0) actualSCrate=staticCrates.size()-1;
			else actualSCrate--;
		}
		
		if(x>=MCrateRightArrow.getX() && x <= MCrateRightArrow.getX()+MCrateRightArrow.getWidth() && y>=MCrateRightArrow.getY() && y <= MCrateRightArrow.getY()+MCrateRightArrow.getHeight()){
			if(actualMcrate==mobileCrates.size()-1) actualMcrate=0;
			else actualMcrate++;
		}
		
		if(x>=MCrateLeftArrow.getX() && x <= MCrateLeftArrow.getX()+MCrateLeftArrow.getWidth() && y>=MCrateLeftArrow.getY() && y <= MCrateLeftArrow.getY()+MCrateLeftArrow.getHeight()){
			if(actualMcrate==0) actualMcrate=mobileCrates.size()-1;
			else actualMcrate--;
		}
		
		if(x>=targetRightArrow.getX() && x <= targetRightArrow.getX()+targetRightArrow.getWidth() && y>=targetRightArrow.getY() && y <= targetRightArrow.getY()+targetRightArrow.getHeight()){
			if(actualTarget==availableTargets.size()-1) actualTarget=0;
			else actualTarget++;
		}
		
		if(x>=targetLeftArrow.getX() && x <= targetLeftArrow.getX()+targetLeftArrow.getWidth() && y>=targetLeftArrow.getY() && y <= targetLeftArrow.getY()+targetLeftArrow.getHeight()){
			if(actualTarget==0) actualTarget=availableTargets.size()-1;
			else actualTarget--;
		}
		
		if(x >= 140 && x <= 204 && y >= 20 && y <= 84 ) {//sono nel trash button
			for(int i=0; i<10; i++) {
				for(int j=0; j<14; j++) {
					matrix[i][j]=0;
				}
			}
			availableTargets.clear();
			actualTarget=0;
		}
		
		if(x >= 30 && x <= 94 && y >= 20 && y <= 84) { //sono nell home button
			PrincipalFrame f = (PrincipalFrame) this.getTopLevelAncestor();
			Gui g = new Gui();
			f.setAcutalPane(g);
			g.requestFocusInWindow();
		}
		
		
		if(x >= 250 && x <= 314 && y >= 20 && y <= 84) { //sono nel save button
			
			if(availableTargets.isEmpty()) {
			try {
				
				BufferedWriter bOut = new BufferedWriter(new FileWriter("mapx.txt"));
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
		if(e.getX()>=150 && e.getX()<=150+64 && e.getY()>=150 && e.getY()<=150+64){
			drag = true;
			actualValue = 20+actualSCrate;
			actualImage = staticCrates.get(actualSCrate);
		}
		
		if(e.getX()>=150 && e.getX()<=150+64 && e.getY()>=350 && e.getY()<=350+64){
			drag = true;
			actualValue = 10+actualMcrate;
			actualImage = mobileCrates.get(actualMcrate);
		}
		
		if(e.getX()>=150 && e.getX()<=150+64 && e.getY()>=550 && e.getY()<=550+64 && !availableTargets.isEmpty()){
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
		
		
		for (int i = 0; i < 10; i++) { //se sono nella matrice
			for (int j = 0; j < 14; j++) {
				if(mousex>=350 + (j*64) && mousex < (350 + ((j+1)*64)) && mousey >= 70 +(i*64) && mousey < 70 + (i+1)*64){
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
