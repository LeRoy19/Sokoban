package Graphics;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


public class LevelSelection extends JPanel implements MouseListener{

	private static final long serialVersionUID = -4453938973046062048L;
	public ArrayList<JLabel> maps = null;
	@SuppressWarnings("unused")
	private Dimension d = null;
	int mapNumbers = 0;
	int map = 0;
    public LevelSelection(Dimension d) {
    	super();
    	this.addMouseListener(this);
    	this.setVisible(true);
    	this.setFocusable(true);
    	this.d = d;
    	this.setOpaque(false);
    	this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
    	maps = new ArrayList<JLabel>();
		try {
			BufferedReader bIn = new BufferedReader(new FileReader("NumMaps.txt"));
			mapNumbers = Integer.parseInt(bIn.readLine());
			bIn.close();
		} catch (Exception e) {
			System.out.println("Errore nella lettura del numero di mappe");
		}
		
		for(int i = 0; i < mapNumbers; i++) {
			Image image = null;
			try {
				image = ImageIO.read(new File("Images"+File.separator+"Buttons"+File.separator+"Map"+(i+1)+"U.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ImageIcon img = new ImageIcon(image);
			JLabel label = new JLabel(img);
			
			maps.add(label);
			this.add(label);
		}
		
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
		
		for (int i = 0; i<maps.size(); i++) {
			if(x >= maps.get(i).getX() && x <= maps.get(i).getWidth() + maps.get(i).getX() &&  y >= maps.get(i).getY() && y <= maps.get(i).getY() + maps.get(i).getHeight()) {
				Image image = null;
				try {
					image = ImageIO.read(new File("Images"+File.separator+"Buttons"+File.separator+"Map"+(i+1)+"P.png"));
				} catch (IOException r) {
					r.printStackTrace();
				}
				
				ImageIcon img = new ImageIcon(image);
				maps.get(i).setIcon(img);
			}	
		}
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		for (int i = 0; i<maps.size(); i++) {
			if(x >= maps.get(i).getX() && x <= maps.get(i).getWidth() + maps.get(i).getX() &&  y >= maps.get(i).getY() && y <= maps.get(i).getY() + maps.get(i).getHeight()) {
				
				map = i;
				JSplitPane split = (JSplitPane) (this.getParent());
				ColourSelection col = (ColourSelection) split.getTopComponent();
				int colour = col.getPlayerColour();
				
				PrincipalFrame f = (PrincipalFrame) this.getTopLevelAncestor();
				Level l = new Level(d, colour, map+1);
				f.setAcutalPane(l);
				l.requestFocusInWindow();
			}
		}
	
		
	}
	
	public int getMap() {
		return map;
	}
	
	
	
	
}
