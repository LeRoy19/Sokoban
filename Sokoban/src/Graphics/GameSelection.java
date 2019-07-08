package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class GameSelection extends JPanel {

	
	
	private static final long serialVersionUID = 8346368215230200184L;
	public MyImage background = null;
	private Dimension d = null;
	JSplitPane split = null;
	ColourSelection playerColourPane = null;
	LevelSelection mapSelection = null;
	
	
	public GameSelection(Dimension d) {
		super();
		this.setFocusable(true);
		this.d = d;
		this.setLayout(new BorderLayout());
		background = new MyImage(d, "Images"+File.separator+"background.jpg", 0, 0, d.width, d.height);
		playerColourPane = new ColourSelection(d);
		
		mapSelection = new LevelSelection(d);
		mapSelection.setLayout(new FlowLayout());
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, playerColourPane, mapSelection);
		split.setDividerLocation(300);
		split.setDividerSize(0);
		split.setOpaque(false);
		mapSelection.setOpaque(false);
		this.add(split, BorderLayout.CENTER);
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.image, 0, 0, getWidth(), getHeight(), null);
	}
	
	
}
