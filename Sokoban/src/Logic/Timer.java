package Logic;

public class Timer implements Runnable {

	private int seconds;
	private boolean run; 
	
	public Timer() {
		seconds=0;
		run = true;
	}
	
	
	@Override
	public void run() {
		while(true) {
			if(run) {
				seconds++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int getTime() {
		return seconds;
	}
	
	public void setTime(int x) {
		seconds = x;
	}
	
	public void pause() {
		run = false;
	}
	
	public void start() {
		run = true;
	}
	
	public boolean getStatus() {
		return run;
	}

}
