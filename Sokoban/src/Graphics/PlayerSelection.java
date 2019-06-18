package Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlayerSelection extends JPanel implements MouseListener {

	private static final long serialVersionUID = -5528240152591890920L;
	
	public Image background = null;
	public ArrayList<Image> players = null;
	public Image leftArrow = null;
	public Image rightArrow = null;
	public GraphicsButton play  = null;
	private int currentPlayer = 0;
	
	public PlayerSelection() {
		players = new ArrayList<Image>();
		this.addMouseListener(this);
		this.setFocusable(true);
		currentPlayer = 0;
		play = new GraphicsButton("Buttons"+File.separator+"playPr.png","Buttons"+File.separator+"playUn.png", 600, 600, 190, 50);
		try {
			
			background = ImageIO.read(new File("Images"+File.separator+"background.jpg"));
			for (int i = 0; i < 4; i++) {
				players.add(ImageIO.read(new File("Images"+File.separator+"Players"+File.separator+i+".png")));
			}
			leftArrow = ImageIO.read(new File("Images"+File.separator+"LeftArrow.png"));
			rightArrow = ImageIO.read(new File("Images"+File.separator+"RightArrow.png"));
		} catch (Exception e) {
			System.out.println("Errore nel caricamento delle immaggini della PlayerSelection");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(players.get(currentPlayer), 654, 100, null);
		g.drawImage(leftArrow, 550, 120, null);
		g.drawImage(rightArrow, 780, 120, null);
		g.drawImage(play.getActual(), play.getX(), play.getY(), null);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		if(x >= play.getX() && x<= play.getX()+play.getWidth() && 
		   y >= play.getY() && y<= play.getY()+play.getHeight()) {
			play.setPressed(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		if(x >= 550 && x<= 590 && y >= 120 && y<= 150) {
			if(currentPlayer > 0) currentPlayer--;
			else currentPlayer = players.size()-1;
		}
		
		if(x >= 780 && x<= 820 && y >= 120 && y<= 150) {
			if(currentPlayer == players.size()-1) currentPlayer=0;
			else currentPlayer ++;
		}
		
		if(x >= play.getX() && x<= play.getX()+play.getWidth() && 
		   y >= play.getY() && y<= play.getY()+play.getHeight()) {
			play.setPressed(false);
			PrincipalFrame f = (PrincipalFrame) this.getTopLevelAncestor();
			String playerColour = "G";
			switch(currentPlayer) {
			case 0:
				playerColour = "B";
				break;
			case 1:
				playerColour = "Y";
				break;
			case 2:
				playerColour = "P";
				break;
			case 3:
				playerColour = "G";
				break;
			default:
				break;
			}
			Level l = new Level(playerColour);
			f.setAcutalPane(l);
			l.requestFocusInWindow();
		}
		

	}

}
