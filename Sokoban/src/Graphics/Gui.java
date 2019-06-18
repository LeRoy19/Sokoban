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




public class Gui extends JPanel implements MouseListener{
	private static final long serialVersionUID = -4680955221761793446L;
	public Image background = null;
	public ArrayList<GraphicsButton> buttons= null;
	public GraphicsButton esc = null;
	
	public Gui(String background) {
		this.addMouseListener(this);
		
		this.setFocusable(true);
		esc = new GraphicsButton("Buttons"+File.separator+"esc.png","Buttons"+File.separator+"esc.png", 0, 0, 50, 50);
		buttons = new ArrayList<GraphicsButton>();
		try {
			this.background = ImageIO.read(new File("Images"+File.separator+background));
		}
		catch (Exception e) {
			System.out.println("Immagine della gui non trovata");
		}
		AddButton(600, 450, 190, 45, "pressed.png", "unpressed.png");
		AddButton(600, 530, 190, 45, "pressed.png", "unpressed.png");
		AddButton(600, 610, 190, 45, "pressed.png", "unpressed.png");
	}
	
	public void AddButton(GraphicsButton b) {
		buttons.add(b);
	}
	
	public void AddButton(int x, int y, int width, int height, String pressed, String unpressed) {
		GraphicsButton b= new GraphicsButton(pressed, unpressed, x, y, width, height);
		buttons.add(b);
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		for(int i = 0; i < buttons.size(); i++)
			g.drawImage(buttons.get(i).getActual(),buttons.get(i).getX(),buttons.get(i).getY(), null);
		g.drawImage(esc.getActual(),esc.getX(),esc.getY(), 50, 50, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < buttons.size(); i++) {
			if(e.getX()>=buttons.get(i).getX() && e.getX()<=(buttons.get(i).getX()+buttons.get(i).getWidth())
			&& 	e.getY()>=buttons.get(i).getY() && e.getY()<=(buttons.get(i).getY()+buttons.get(i).getHeight()))
				buttons.get(i).setPressed(true);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x>=0 && x<=50 && y>=0 && y<=50) {
			System.exit(0);
		}
		
		int action=-1;
		for (int i = 0; i < buttons.size(); i++) {
			if(x>=buttons.get(i).getX() && x<=(buttons.get(i).getX()+buttons.get(i).getWidth())
			&& y>=buttons.get(i).getY() && y<=(buttons.get(i).getY()+buttons.get(i).getHeight())) {
				buttons.get(i).setPressed(false);
				action=i;
			}
		}
		
		switch(action) {
		
		case 0:
			PrincipalFrame k = (PrincipalFrame) this.getTopLevelAncestor();
			PlayerSelection q = new PlayerSelection();
			k.setAcutalPane(q);
			q.requestFocusInWindow();
			break;
		case 1:
			break;
		case 2:
			PrincipalFrame f = (PrincipalFrame) this.getTopLevelAncestor();
			Editor h = new Editor();
			f.setAcutalPane(h);
			h.requestFocusInWindow();
			break;
		default:
				
		}
	}


}
