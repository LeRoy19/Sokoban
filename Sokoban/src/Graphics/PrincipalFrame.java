package Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrincipalFrame extends JFrame {
	
	private static final long serialVersionUID = -3318135592335853499L;
	JPanel actualPane = null;
	
	public PrincipalFrame() {
		this.setUndecorated(true);
		this.setResizable(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setAcutalPane(JPanel p) {
		if(actualPane!=null) {
			this.remove(actualPane);
		}
		actualPane = p;
		this.add(actualPane);
		revalidate();
	}
	
	public JPanel getActualPane() {
		return actualPane;
	}
	

}
