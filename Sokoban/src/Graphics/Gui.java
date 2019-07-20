package Graphics;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import Sound.SoundEffects;




public class Gui extends JPanel implements MouseListener{
	private static final long serialVersionUID = -4680955221761793446L;
	
	public MyImage background = null;
	public ArrayList<MyButton> buttons= null;
	private Dimension d = null;
	SoundEffects click = null;
	
	public Gui(Dimension d) {
		super();
		this.addMouseListener(this);
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
						System.exit(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		this.setFocusable(true);
		setVisible(true);
		this.d = d;
		click = new SoundEffects("Sounds"+File.separator+"click1.wav");
		buttons = new ArrayList<MyButton>();
		background =new MyImage(d,"Images"+File.separator+"Backgrounds"+File.separator+"ui.png", 0, 0, d.width, d.height);
		
		AddButton(600, 430, 190, 45, "Images"+File.separator+"Buttons"+File.separator+"ClassicP.png", "Images"+File.separator+"Buttons"+File.separator+"ClassicU.png");
		AddButton(600, 510, 190, 45, "Images"+File.separator+"Buttons"+File.separator+"StepsP.png", "Images"+File.separator+"Buttons"+File.separator+"StepsU.png");
		AddButton(600, 590, 190, 45, "Images"+File.separator+"Buttons"+File.separator+"TimeP.png", "Images"+File.separator+"Buttons"+File.separator+"TimeU.png");
		AddButton(600, 670, 190, 45, "Images"+File.separator+"Buttons"+File.separator+"EditorP.png", "Images"+File.separator+"Buttons"+File.separator+"EditorU.png");
	}
	
	
	public void AddButton(MyButton b) {
		buttons.add(b);
	}
	
	public void AddButton(int x, int y, int width, int height, String pressed, String unpressed) {
		MyButton b= new MyButton(d, pressed, unpressed, x, y, width, height);
		buttons.add(b);
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.image, background.x, background.y, d.width, d.height, null);
		for(int i = 0; i < buttons.size(); i++)
			g.drawImage(buttons.get(i).getActual(), buttons.get(i).pressed.x, buttons.get(i).pressed.y, buttons.get(i).pressed.width, buttons.get(i).pressed.height, null);
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
		int action = -2;
		
		for (int i = 0; i < buttons.size(); i++) {
			if(x>=buttons.get(i).pressed.x && x<=(buttons.get(i).pressed.x+buttons.get(i).pressed.width)
					&& y>=buttons.get(i).pressed.y && y<=(buttons.get(i).pressed.y+buttons.get(i).pressed.height)) {
				buttons.get(i).setStatus(false);
				action=i;
				click.playSound();
			}
		}
		
		switch(action) {
		case 0:
			PrincipalFrame k = (PrincipalFrame) this.getTopLevelAncestor();
			GameSelection q = new GameSelection(d, 0);
			k.setAcutalPane(q);
			q.requestFocusInWindow();
			break;
		case 1:
			PrincipalFrame t = (PrincipalFrame) this.getTopLevelAncestor();
			GameSelection g = new GameSelection(d, 1);
			t.setAcutalPane(g);
			g.requestFocusInWindow();
			break;
		case 2:
			PrincipalFrame r = (PrincipalFrame) this.getTopLevelAncestor();
			GameSelection j = new GameSelection(d, 2);
			r.setAcutalPane(j);
			j.requestFocusInWindow();
			break;
		case 3:
			PrincipalFrame f = (PrincipalFrame) this.getTopLevelAncestor();
			Editor h = new Editor(d);
			f.setAcutalPane(h);
			h.requestFocusInWindow();
			break;
		default:
				
		}
	}

 
}
