package Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Logic.Player;

public class GraphicsPlayer extends Player {
	public ArrayList<Image> right;
	public ArrayList<Image> left;
	public ArrayList<Image> down;
	public ArrayList<Image> up;
	int movementRight=0;
	int movementUp=0;
	int movementDown=0;
	int movementLeft=0;
	Image imgCorrente;
	private int index;
	
	
	public GraphicsPlayer(int col) {
		super();
		String colour;
		switch(col) {
		case 0:
			colour = "B";
			break;
		case 1: 
			colour = "Y";
			break;
		case 2:
			colour = "P";
			break;
		case 3:
			colour = "G";
			break;
			default:
				colour = "G";
		}
		right = new ArrayList<Image>();
		left = new ArrayList<Image>();
		down = new ArrayList<Image>();
		up = new ArrayList<Image>();
		index = 0; 

		for(int i = 0; i < 3; i++) {
			try {
				Image l = ImageIO.read(new File("Images"+File.separator+"Player"+File.separator+"L"+i+colour+".png"));
				Image r = ImageIO.read(new File("Images"+File.separator+"Player"+File.separator+"R"+i+colour+".png"));
				Image d = ImageIO.read(new File("Images"+File.separator+"Player"+File.separator+"D"+i+colour+".png"));
				Image u = ImageIO.read(new File("Images"+File.separator+"Player"+File.separator+"U"+i+colour+".png"));
				right.add(r);
				left.add(l);
				down.add(d);
				up.add(u);
				
			} catch (IOException e) {
				System.out.println("errore nel caricamento delle immagini del player");
			}
		}
		imgCorrente = right.get(index);
	}

	public Image getImgCorrente() {
		//return imgCorrente;
	
		if(direction == "right")
			return right.get(movementRight);
		if(direction == "left")
			return left.get(movementLeft);
		if(direction == "up")
			return up.get(movementUp);
			return down.get(movementDown);
		
	}
	
	public void resetMovement() {
		movementRight=0;
		movementLeft=0;
		movementUp=0;
		movementDown=0;
	}
	
	public void incrementMovement(String direction) {
		if(direction.equals("right")) movementRight=(movementRight+1)%right.size();
		else if(direction.equals("left")) movementLeft=(movementLeft+1)%left.size();
		else if(direction.equals("up")) movementUp=(movementUp+1)%up.size();
		else if(direction.equals("down")) movementDown=(movementDown+1)%down.size();
	}


	public void setImgCorrente(Image imgCorrente) {
		this.imgCorrente = imgCorrente;
	}

		
	
}
