package Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MultiplayerSelection extends JPanel {

	private static final long serialVersionUID = -894298700714597982L;
	public MyImage background = null;
	private Dimension d = null;
	JSplitPane split = null;
	
	LevelSelection mapSelection = null;
	MultiPlayerColourSelection colourSelection = null;

	public MultiplayerSelection(Dimension d){
		super();
		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		this.d = d;
		background = new MyImage(d, "Images"+File.separator+"background.jpg", 0, 0, d.width, d.height);
		mapSelection = new LevelSelection(d);
		colourSelection = new MultiPlayerColourSelection(d);
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,colourSelection,mapSelection); 
		split.setDividerLocation((int)(400*background.scaley));
		split.setDividerSize(0);
		split.setOpaque(false);
		this.add(split,BorderLayout.CENTER);
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.image, 0, 0, background.width, background.height , null);
	}
}
