package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import Sound.SoundEffects;


/*pannello per la selezione del colore del giocatore*/

public class ColourSelection extends JPanel implements MouseListener {

	private static final long serialVersionUID = -3858296845991617406L;
	
	public ArrayList<MyImage> players = null;
	public MyImage leftArrow = null;
	public MyImage rightArrow = null;
	private int currentPlayer = 0;
	Dimension d = null;
	SoundEffects click = null;
	
	public ColourSelection(Dimension d) {
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setOpaque(false);
		this.d = d;
		click = new SoundEffects("Sounds"+File.separator+"click1.wav");
		players = new ArrayList<MyImage>();
		this.setFocusable(true);
		currentPlayer = 0;
		for (int i = 0; i < 4; i++) {
			players.add(new MyImage(d, "Images"+File.separator+"Players"+File.separator+i+".png", 640, 85, 90, 90));
		}
		leftArrow = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"LeftArrow.png", 550, 120, 39, 31);
		rightArrow = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"RightArrow.png", 780, 120, 39, 31);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(players.get(currentPlayer).image, players.get(currentPlayer).x , players.get(currentPlayer).y, players.get(currentPlayer).width, players.get(currentPlayer).height, null);
		g.drawImage(leftArrow.image, leftArrow.x, leftArrow.y, leftArrow.width, leftArrow.height, null);
		g.drawImage(rightArrow.image, rightArrow.x, rightArrow.y, rightArrow.width, rightArrow.height, null);
	}
	
	public void SwitchPlayer(boolean left) {
		if(left) {
			if(currentPlayer > 0) currentPlayer--;
			else currentPlayer = players.size()-1;
		}
		else {
			if(currentPlayer == players.size()-1) currentPlayer=0;
			else currentPlayer ++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x >= rightArrow.x && x <= rightArrow.x + rightArrow.width && y >= rightArrow.y && y <= rightArrow.y + rightArrow.height) {
			click.playSound();
			SwitchPlayer(false);
			repaint();
		}
		
		if(x >= leftArrow.x && x <= leftArrow.x + leftArrow.width && y >= leftArrow.y && y <= leftArrow.y + leftArrow.height) {
			click.playSound();
			SwitchPlayer(true);
			repaint();
		}
		
	}
	
	public int getPlayerColour() {
		return currentPlayer;
	}
	
	
}
