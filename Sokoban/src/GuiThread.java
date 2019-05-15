
public class GuiThread implements Runnable {

	private Gui gui = null;
	int slee=10;
	public GuiThread(Gui g) {
		gui=g;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				gui.repaint();
				Thread.sleep(slee);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
