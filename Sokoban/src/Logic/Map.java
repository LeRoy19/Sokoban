package Logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Graphics.MobileCrate;
import Graphics.StaticCrate;
import Graphics.Target;

public class Map {
	
	public int rows, columns;
	public int matrix[][] = null;
	public ArrayList<StaticCrate> staticCrates = null;
	public ArrayList<MobileCrate> mobileCrates = null;
	public ArrayList<Target> targets = null;
	public Player player = null;
	
	public Map() {
		rows = 10;
		columns = rows;
		matrix = new int[rows][columns];
		staticCrates = new ArrayList<StaticCrate>();
		mobileCrates = new ArrayList<MobileCrate>();
		targets = new ArrayList<Target>();
		player = new Player();
	}
	
	//carico mappa da file
	public Map(String file) {
		int i = 0;
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
					else {
						String line=bIn.readLine();
						for(int j = 0; j< columns; j++) {
							int value = Character.getNumericValue(line.charAt(2*j));
							matrix[i-2][j] = value;
						}	
					}
					i++;
				}
				bIn.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			staticCrates = new ArrayList<StaticCrate>();
			mobileCrates = new ArrayList<MobileCrate>();
			targets = new ArrayList<Target>();
			player = new Player();
			
	}
		
	
	
	public Map(int rows ,int columns) {
		this.rows = rows;
		this.columns = columns;
		matrix = new int [rows][columns];
		staticCrates = new ArrayList<StaticCrate>();
		mobileCrates = new ArrayList<MobileCrate>();
		targets = new ArrayList<Target>();
		player = new Player();
	}
	
	public int get(int i, int j) {
		return matrix[i][j];
	}
	
	public void set(int  i, int j, int x) {
		matrix[i][j] = x;
	}
	
	void addMCrate(MobileCrate x, Target y) {
		matrix[x.coordinate.i][x.coordinate.j]+=x.getValue();
		mobileCrates.add(x);
		matrix[y.coordinate.i][y.coordinate.j]+=y.getValue();
		targets.add(y);
	}
	
	void addSCrate(StaticCrate x) {
		matrix[x.coordinate.i][x.coordinate.j]+=x.getValue();
		staticCrates.add(x);
	}
	

	
	
	
	
}
