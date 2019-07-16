package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import Sound.SoundEffects;

public class MultiPlayerColourSelection extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = -3929762455221186208L;
	public ArrayList<MyImage> players = null;
	public MyImage player1leftArrow = null;
	public MyImage player1rightArrow = null;
	public MyImage player2leftArrow = null;
	public MyImage player2rightArrow = null;
	int player1 = 0;
	int player2 = 1;
	@SuppressWarnings("unused")
	private Dimension d = null;
	SoundEffects click = null;
	
	
	public MultiPlayerColourSelection(Dimension d) {
		this.addMouseListener(this);
		setFocusable(true);
		this.d=d;
		this.setOpaque(false);
		click = new SoundEffects("Sounds"+File.separator+"click1.wav");
		players = new ArrayList<MyImage>();
		this.setFocusable(true);
		for (int i = 0; i < 4; i++) {
			players.add(new MyImage(d, "Images"+File.separator+"Players"+File.separator+i+".png", 390, 85, 90, 90));
		}
		player1leftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 300, 120, 39, 31);
		player1rightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 520, 120, 39, 31);
		player2leftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 300+500, 120, 39, 31);
		player2rightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 520+500, 120, 39, 31);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(players.get(player1).image, players.get(player1).x , players.get(player1).y, players.get(player1).width, players.get(player1).height, null);
		g.drawImage(players.get(player2).image, players.get(player1).x +(int) (500*players.get(player1).scalex), players.get(player1).y, players.get(player1).width, players.get(player1).height, null);
		g.drawImage(player1leftArrow.image, player1leftArrow.x, player1leftArrow.y, player1leftArrow.width, player1leftArrow.height, null);
		g.drawImage(player1rightArrow.image, player1rightArrow.x, player1rightArrow.y, player1rightArrow.width, player1rightArrow.height, null);
		g.drawImage(player2leftArrow.image, player2leftArrow.x, player2leftArrow.y, player2leftArrow.width, player2leftArrow.height, null);
		g.drawImage(player2rightArrow.image, player2rightArrow.x, player2rightArrow.y, player2rightArrow.width, player2rightArrow.height, null);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		
		if(x>=player1leftArrow.x && x<=player1leftArrow.x + player1leftArrow.width && y>=player1leftArrow.y && y<=player1leftArrow.y + player1leftArrow.height ) {
			click.playSound();
			if(player1==0) player1=players.size()-1;
			else player1--;
			if(player1==player2) {
				if(player1 == 0) player1=players.size()-1;
				else player1 --;
			}
		}
		
		if(x>=player1rightArrow.x && x<=player1rightArrow.x + player1rightArrow.width && y>=player1rightArrow.y && y<=player1rightArrow.y + player1rightArrow.height ) {
			click.playSound();
			if(player1 == players.size()-1) player1=0;
			else player1++;
			if(player1==player2) {
				if(player1 == players.size()-1) player1=0;
				else player1++;
			}
		}
		
		if(x>=player2leftArrow.x && x<=player2leftArrow.x + player2leftArrow.width && y>=player2leftArrow.y && y<=player2leftArrow.y + player2leftArrow.height ) {
			click.playSound();
			if(player2==0) player2=players.size()-1;
			else player2--;
			if(player2==player1) {
				if(player2 == 0) player2=players.size()-1;
				else player2 --;
			}
		}
		
		if(x>=player2rightArrow.x && x<=player2rightArrow.x + player2rightArrow.width && y>=player2rightArrow.y && y<=player2rightArrow.y + player2rightArrow.height ) {
			click.playSound();
			if(player2 == players.size()-1) player2=0;
			else player2++;
			if(player1==player2) {
				if(player2 == players.size()-1) player2=0;
				else player2++;
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
