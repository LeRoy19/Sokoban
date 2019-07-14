package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import Sound.SoundEffects;

public class ColourSelection extends JPanel implements MouseListener {

	private static final long serialVersionUID = -3858296845991617406L;
	

	
	public ArrayList<MyImage> players = null;
	public MyImage leftArrow = null;
	public MyImage rightArrow = null;
	private int currentPlayer = 0;
	@SuppressWarnings("unused")
	private Dimension d = null;
	SoundEffects click = null;
	public MyImage classicMode = null;
	public MyImage stepsMode = null;
	public GraphicsButton cModeCheckBox = null;
	public GraphicsButton sModeCheckBox = null;
	public boolean mode = true;
	
	
	public ColourSelection(Dimension d) {
		addMouseListener(this);
		setFocusable(true);
		this.d=d;
		this.setOpaque(false);
		click = new SoundEffects("Sounds"+File.separator+"click1.wav");
		players = new ArrayList<MyImage>();
		this.setFocusable(true);
		currentPlayer = 0;
		for (int i = 0; i < 4; i++) {
			players.add(new MyImage(d, "Images"+File.separator+"Players"+File.separator+i+".png", 240, 85, 90, 90));
		}
		leftArrow = new MyImage(d, "Images"+File.separator+"LeftArrow.png", 150, 120, 39, 31);
		rightArrow = new MyImage(d, "Images"+File.separator+"RightArrow.png", 380, 120, 39, 31);
		classicMode = new MyImage(d, "Images"+File.separator+"CMode.png", 1100, 50, 200, 50);
		stepsMode = new MyImage(d, "Images"+File.separator+"SMode.png", 1090, 100, 200, 50);
		cModeCheckBox = new GraphicsButton(d, "Images"+File.separator+"Buttons"+File.separator+"On.png", "Images"+File.separator+"Buttons"+File.separator+"Off.png", 1060, 57, 30, 30);
		cModeCheckBox.setStatus(true);
		sModeCheckBox = new GraphicsButton(d, "Images"+File.separator+"Buttons"+File.separator+"On.png", "Images"+File.separator+"Buttons"+File.separator+"Off.png", 1060, 107, 30, 30);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(players.get(currentPlayer).image, players.get(currentPlayer).x , players.get(currentPlayer).y, players.get(currentPlayer).width, players.get(currentPlayer).height, null);
		g.drawImage(leftArrow.image, leftArrow.x, leftArrow.y, leftArrow.width, leftArrow.height, null);
		g.drawImage(rightArrow.image, rightArrow.x, rightArrow.y, rightArrow.width, rightArrow.height, null);
		g.drawImage(classicMode.image, classicMode.x, classicMode.y, classicMode.width, classicMode.height, null);
		g.drawImage(stepsMode.image, stepsMode.x, stepsMode.y, stepsMode.width, stepsMode.height, null);
		g.drawImage(cModeCheckBox.getActual(), cModeCheckBox.getPressed().x, cModeCheckBox.getPressed().y, cModeCheckBox.getPressed().width, cModeCheckBox.getPressed().height, null);
		g.drawImage(sModeCheckBox.getActual(), sModeCheckBox.getPressed().x, sModeCheckBox.getPressed().y, sModeCheckBox.getPressed().width, sModeCheckBox.getPressed().height, null);
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
			SwitchPlayer(false);
			repaint();
			click.playSound();
		
		}
		
		if(x >= leftArrow.x && x <= leftArrow.x + leftArrow.width && y >= leftArrow.y && y <= leftArrow.y + leftArrow.height) {
			SwitchPlayer(true);
			repaint();
			click.playSound();
		}
		
		if(x >= cModeCheckBox.getPressed().x && x <= cModeCheckBox.getPressed().x + cModeCheckBox.getPressed().width &&
				y >= cModeCheckBox.getPressed().y && y <= cModeCheckBox.getPressed().y +  cModeCheckBox.getPressed().height) {
			click.playSound();
			if(!cModeCheckBox.isPressed) {
				cModeCheckBox.setStatus(!cModeCheckBox.isPressed);
				sModeCheckBox.setStatus(!sModeCheckBox.isPressed);
				mode = true;
			}
		}
		
		if(x >= sModeCheckBox.getPressed().x && x <= sModeCheckBox.getPressed().x + sModeCheckBox.getPressed().width &&
				y >= sModeCheckBox.getPressed().y && y <= sModeCheckBox.getPressed().y +  sModeCheckBox.getPressed().height) {
			click.playSound();
			if(!sModeCheckBox.isPressed) {
				cModeCheckBox.setStatus(!cModeCheckBox.isPressed);
				sModeCheckBox.setStatus(!sModeCheckBox.isPressed);
				mode = false;
			}
		}
	}
	
	public int getPlayerColour() {
		return currentPlayer;
	}
	
	
}
