package Graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrincipalFrame extends JFrame {
	
	private static final long serialVersionUID = -3318135592335853499L;
	JPanel actualPane = null;
	Dimension dim = null;

	public PrincipalFrame() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dim.width, dim.height);
		
		
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.actualPane = new Gui(this.getSize());
		this.add(actualPane);
		this.setVisible(true);
	}
	
	public void setAcutalPane(JPanel p) {
		if(actualPane!=null) {
			this.remove(actualPane);
		}
		actualPane = p;
		this.add(actualPane);
		actualPane.requestFocus();
		revalidate();
	}
	
	public JPanel getActualPane() {
		return actualPane;
	}
	

}
