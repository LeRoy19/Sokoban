package Graphics;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;




public class Gui extends JPanel implements MouseListener{
	private static final long serialVersionUID = -4680955221761793446L;
	public MyImage background = null;
	public ArrayList<GraphicsButton> buttons= null;
	public MyImage esc = null;
	private Dimension d = null;
	
	public Gui(Dimension d) {
		super();
		this.addMouseListener(this);
		this.setFocusable(true);
		setVisible(true);
		this.d = d;
		esc = new MyImage(d, "Images"+File.separator+"Buttons"+File.separator+"esc.png", 0, 0, 50, 50);
		buttons = new ArrayList<GraphicsButton>();
		try {
			Image x = ImageIO.read(new File("Images"+File.separator+"ui.png"));
			background = new MyImage(d, x, 0, 0, d.width, d.height);
		}
		catch (Exception e) {
			System.out.println("Immagine della gui non trovata");
		}
		AddButton(600, 450, 190, 45, "Images"+File.separator+"pressed.png", "Images"+File.separator+"unpressed.png");
		AddButton(600, 530, 190, 45, "Images"+File.separator+"pressed.png", "Images"+File.separator+"unpressed.png");
		AddButton(600, 610, 190, 45, "Images"+File.separator+"pressed.png", "Images"+File.separator+"unpressed.png");
	}
	
	
	public void AddButton(GraphicsButton b) {
		buttons.add(b);
	}
	
	public void AddButton(int x, int y, int width, int height, String pressed, String unpressed) {
		GraphicsButton b= new GraphicsButton(d, pressed, unpressed, x, y, width, height);
		buttons.add(b);
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.image, background.x, background.y, d.width, d.height, null);
		for(int i = 0; i < buttons.size(); i++)
			g.drawImage(buttons.get(i).getActual(), buttons.get(i).pressed.x, buttons.get(i).pressed.y, buttons.get(i).pressed.width, buttons.get(i).pressed.height, null);
		g.drawImage(esc.image, esc.x ,esc.y, esc.width, esc.height, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		for (int i = 0; i < buttons.size(); i++) {
			if(x>=buttons.get(i).pressed.x && x<=(buttons.get(i).pressed.x+buttons.get(i).pressed.width)
			&& y>=buttons.get(i).pressed.y && y<=(buttons.get(i).pressed.y+buttons.get(i).pressed.height))
				buttons.get(i).setStatus(true);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x>=esc.x && x <= esc.x + esc.width && y>=esc.y && y<=esc.y + esc.height) {
			System.exit(0);
		}
		
		int action=-1;
		for (int i = 0; i < buttons.size(); i++) {
			if(x>=buttons.get(i).pressed.x && x<=(buttons.get(i).pressed.x+buttons.get(i).pressed.width)
					&& y>=buttons.get(i).pressed.y && y<=(buttons.get(i).pressed.y+buttons.get(i).pressed.height)) {
				buttons.get(i).setStatus(false);
				action=i;
			}
		}
		
		switch(action) {
		
		case 0:
			PrincipalFrame k = (PrincipalFrame) this.getTopLevelAncestor();
			GameSelection q = new GameSelection(d);
			k.setAcutalPane(q);
			q.requestFocusInWindow();
			break;
		case 1:
			PrincipalFrame t = (PrincipalFrame) this.getTopLevelAncestor();
			GameSelection g = new GameSelection(d);
			t.setAcutalPane(g);
			g.requestFocusInWindow();
			break;
		case 2:
			PrincipalFrame f = (PrincipalFrame) this.getTopLevelAncestor();
			Editor h = new Editor(d);
			f.setAcutalPane(h);
			h.requestFocusInWindow();
			break;
		default:
				
		}
	}

 /*cambiare il level*/
}
