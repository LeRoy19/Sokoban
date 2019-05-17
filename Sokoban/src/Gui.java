
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Gui extends JPanel implements MouseListener, Runnable{
	Image background = null;
	ArrayList <Button> buttons = null;
	int action;
	GuiPlayer p1 = null;
	
	public Gui(String background) {
		p1= new GuiPlayer();
		Thread t1 = new Thread(this);
		t1.start();
		Thread t2= new Thread(p1);
		t2.start();
		p1.setMuovi(true);
		this.setFocusable(true);
		addMouseListener(this);
		action=-1;
		buttons = new ArrayList<Button>();
		try {
			this.background = ImageIO.read(new File(background));
		}
		catch(Exception e) {
			System.out.println("Errore nel caricamento dell'immagine di Background");
		}

	}
	
	public Gui() {
		buttons = new ArrayList<Button>();
		background = null;
	}
	
	public void AddButton(Button b) {
		buttons.add(b);
	}
	
	public void RemoveButton(Button b) {
		buttons.remove(b);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g) ;		
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(p1.imgCorrente, p1.x, p1.y, 70, 70, null);
		for(int i=0; i<buttons.size(); i++) {
			g.drawImage(buttons.get(i).current, buttons.get(i).x, buttons.get(i).y, null);
			action=i;
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		if(arg0.getX() >= buttons.get(0).x && arg0.getY()>= buttons.get(0).y) {
			buttons.get(0).setCurrent(0);
			this.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for (int i = 0; i < buttons.size(); i++) {
			if(arg0.getX() >= buttons.get(i).x && arg0.getY()>= buttons.get(i).y) {
				buttons.get(i).setCurrent(1);
				action=i;
				this.repaint();
				
				
			}
		}
	}

	public int getAction() {
		return action;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while( true ) {
			repaint();
			try {
				Thread.sleep(60);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
			
}
