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
			return right.get(0);
		if(direction == "left")
			return left.get(0);
		if(direction == "up")
			return up.get(0);
			return down.get(0);
		
	}


	public void setImgCorrente(Image imgCorrente) {
		this.imgCorrente = imgCorrente;
	}

		
	
}
