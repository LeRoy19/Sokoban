package Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LevelSelection extends JPanel implements MouseListener {

	private static final long serialVersionUID = -4453938973046062048L;
	public Image background = null;
	public ArrayList<GraphicsButton> buttons = null;
	
    public LevelSelection() {
    	buttons = new ArrayList<GraphicsButton>();
		try {
			this.background = ImageIO.read(new File("Images"+File.separator+"background.jpg"));
		} catch (Exception e) {
			System.out.println("Immaggine levelSelection non trovata");
		}
		/* aggiungere tanti bottoni quante sono le mappe nel file txt */
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		for (int i = 0; i < buttons.size(); i++) {
			g.drawImage(buttons.get(i).getActual(), buttons.get(i).getX(), buttons.get(i).getX(), null);
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

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
