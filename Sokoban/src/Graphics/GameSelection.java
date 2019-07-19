package Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class GameSelection extends JPanel implements KeyListener{

	private static final long serialVersionUID = 8346368215230200184L;
	public MyImage background = null;
	private Dimension d = null;
	JSplitPane split = null;
	ColourSelection playerColourPane = null;
	LevelSelection mapSelection = null;
	int mode;
	
	
	public GameSelection(Dimension d, int mode) {
		super();
		this.setFocusable(true);
		this.d = d;
		this.mode=mode;
		this.setLayout(new BorderLayout());
		background = new MyImage(d, "Images"+File.separator+"background.jpg", 0, 0, d.width, d.height);
		playerColourPane = new ColourSelection(d);
		
		mapSelection = new LevelSelection(d);
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, playerColourPane, mapSelection);
		split.setDividerLocation((int)(300*background.scaley));
		split.setDividerSize(0);
		split.setOpaque(false);
		mapSelection.setOpaque(false);
		this.add(split, BorderLayout.CENTER);
		this.setVisible(true);
		this.addKeyListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.image, 0, 0, getWidth(), getHeight(), null);
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
			PrincipalFrame k = (PrincipalFrame) this.getTopLevelAncestor();
			if(k.getActualPane() == this) {
			Gui q = new Gui(d);
			k.setAcutalPane(q);
			q.requestFocusInWindow();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
}
