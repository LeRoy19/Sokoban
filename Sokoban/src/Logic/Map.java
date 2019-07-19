package Logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class Map {
	
	public int rows, columns;
	public int matrix[][] = null;
	private int resetMatrix[][] = null;
	public ArrayList<Target> targets = null;
	public Player player = null;
	public int steps, actualSteps, totalTime;
	public int mode;
	public Timer time = null;
	public int playerI, playerJ;
	
	public Map() {
		rows = 10;
		columns = rows;
		matrix = new int[rows][columns];
		resetMatrix = new int[rows][columns];
		targets = new ArrayList<Target>();
		player = new Player();
		steps = 1000;
		actualSteps = 0;
		totalTime = 100;
		mode = 0;
		playerI = 0;
		playerJ = 0;
	}
	
	//carico mappa da file
	public Map(String file, int mode) {
		int i = 0;
		this.mode = mode;
		targets = new ArrayList<Target>();
			try {
				BufferedReader bIn = new BufferedReader(new FileReader(file));
				while(bIn.ready()) {
					if(i==0) {
						String line=bIn.readLine();
						rows = Integer.parseInt(line);
					}
					else if(i==1) {
						String line=bIn.readLine();
						columns = Integer.parseInt(line);
						matrix = new int[rows][columns];
						resetMatrix = new int[rows][columns];
					}
					else if(i==2) {
						String line=bIn.readLine();
						steps = Integer.parseInt(line);
					}
					else if(i==3) {
						String line=bIn.readLine();
						totalTime = Integer.parseInt(line);
					}
					else if(i==14) {
						String line=bIn.readLine();
						playerI = Integer.parseInt(line);
					}
					else if (i==15) {
						String line=bIn.readLine();
						playerJ = Integer.parseInt(line);
					}
					else {
						String line=bIn.readLine();
						StringTokenizer tok = new StringTokenizer(line, " ");
						for(int j = 0; j< columns; j++) {
							String v = tok.nextToken();
							int value = Integer.parseInt(v);
							matrix[i-4][j] = value;
							resetMatrix[i-4][j] = value;
							if(value < 0) { //è un target
								Target e = new Target(i-4, j, value);
								targets.add(e);
							}
						}	
					}
					i++;
				}
				bIn.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		
			
			player = new Player(playerI,playerJ);
			if(mode == 2) {
				time = new Timer();
				new Thread(time).start();
			}
			
	}
		
	
	
	public Map(int rows ,int columns) {
		this.rows = rows;
		this.columns = columns;
		matrix = new int [rows][columns];
		targets = new ArrayList<Target>();
		player = new Player();
	}
	
	public int get(int i, int j) {
		return matrix[i][j];
	}
	
	public void set(int  i, int j, int x) {
		matrix[i][j] = x;
	}
	
	
	
	public boolean isComplete() {
		for(int i = 0; i < targets.size(); i++) {
			if(targets.get(i).getActualValue() < 0)
				return false;
		}
		if(mode == 1)
			if(actualSteps > steps) return false;
		if(mode == 2)
			if(time.getTime() > totalTime) return false;
		
		return true;
	}
	
	public void incrementActualSteps() {
		actualSteps++;
	}
	
	public void setActualSteps(int x) {
		actualSteps = x;
	}
	
	public int getActualSteps() {
		return actualSteps;
	}
	
	public void setSteps(int x) {
		steps = x;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public void reset() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j< columns; j++) {
				matrix[i][j]=resetMatrix[i][j];
			}
		}
		
		for(int i = 0; i<targets.size(); i++) {
			if(targets.get(i).actualValue>0)
				targets.get(i).actualValue= (targets.get(i).actualValue+9)*(-1);
		}
			

		player.setI(playerI);
		player.setJ(playerJ);
		actualSteps = 0;
		if(time!=null) {
			time.setTime(0);
		}
	}

	
	
	
	
	
	
}
