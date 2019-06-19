package Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrincipalFrame extends JFrame {
	
	private static final long serialVersionUID = -3318135592335853499L;
	JPanel actualPane = null;
	Dimension dim = null;
	JPanel x = null;
	public PrincipalFrame() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dim.width, dim.height);
		x = new JPanel();
		x.setLayout(new BorderLayout(0,0));
		this.add(x);
		x.setLayout(new BorderLayout(0,0));
		
		this.setUndecorated(true);
		this.setResizable(false);
		//this.setSize(600, 600);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setAcutalPane(JPanel p) {
		if(actualPane!=null) {
			this.remove(actualPane);
		}
		actualPane = p;
		this.x.add(actualPane,BorderLayout.CENTER);
		revalidate();
	}
	
	public JPanel getActualPane() {
		return actualPane;
	}
	

}
