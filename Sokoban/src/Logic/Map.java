package Logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class Map {
	
	public int rows, columns;
	public int matrix[][] = null;
	public ArrayList<Target> targets = null;
	public Player player = null;
	int steps;
	int actualSteps;
	
	public Map() {
		rows = 10;
		columns = rows;
		matrix = new int[rows][columns];
		targets = new ArrayList<Target>();
		player = new Player();
		steps = 1000;
		actualSteps = 0;
	}
	
	//carico mappa da file
	public Map(String file) {
		int i = 0;
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
					}
					else if(i==2) {
						String line=bIn.readLine();
						steps = Integer.parseInt(line);
					}
					else {
						String line=bIn.readLine();
						StringTokenizer tok = new StringTokenizer(line, " ");
						for(int j = 0; j< columns; j++) {
							String v = tok.nextToken();
							int value = Integer.parseInt(v);
							matrix[i-3][j] = value;
							if(value < 0) { //è un target
								targets.add(new Target(i-3 , j, value));
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
		
			
			player = new Player();
			
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
		if(actualSteps > steps) return false;
		
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

	
	
	
	
	
	
}
