
import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Gui extends JPanel implements MouseListener{
	Image background = null;
	ArrayList <Button> buttons = null;
	int action;
	
	public Gui(String background) {
		addMouseListener(this);
		action=0;
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
		if(arg0.getX() >= buttons.get(0).x && arg0.getY()>= buttons.get(0).y) {
			buttons.get(0).setCurrent(1);
			action=1;
			this.repaint();
		}
	}

			
}
