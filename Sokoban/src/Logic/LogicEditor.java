package Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogicEditor {

	public int matrix [][] = null;
	public int availableTargets = 0;
	public int steps = 0;
	public int time = 0;
	int playerI=0, playerJ=0;
	
	public LogicEditor() {
		matrix = new int[10][14];
	}
	
	public boolean SaveMap() {
		int map=0;
		
		if(availableTargets==0 && steps>0 && time>0) {
			try {
				BufferedReader bIn = new BufferedReader(new FileReader("NumMaps.txt"));
				while(bIn.ready()) {
					String line = bIn.readLine();
					map = Integer.parseInt(line);
					map++;
				}
				bIn.close();
			
				BufferedWriter bOut = new BufferedWriter(new FileWriter("map"+map+".txt"));
				BufferedWriter bOut2 = new BufferedWriter(new FileWriter("NumMaps.txt"));
				bOut2.append(Integer.toString(map));
				bOut2.close();
				bOut.append("10");
				bOut.newLine();
				bOut.append("14");
				bOut.newLine();
				bOut.append(Integer.toString(steps));
				bOut.newLine();
				bOut.append(Integer.toString(time));
				bOut.newLine();
				for (int i = 0; i < 10; i++) {
					StringBuilder riga = new StringBuilder();
					for (int j = 0; j < 14; j++) {
						if(matrix[i][j]==-100) {
							playerI=i;
							playerJ=j;
							matrix[i][j]=0;
						}
							riga.append(Integer.toString(matrix[i][j])+" ");
					}
					bOut.append(riga.toString());
					bOut.newLine();
				}
				bOut.append(Integer.toString(playerI));
				bOut.newLine();
				bOut.append(Integer.toString(playerJ));
				bOut.newLine();
				bOut.close();
				return true;
				}
				catch(IOException k) {
					k.printStackTrace();
					return false;
			}
		}
		return false;
	}
	
	public void clearMap() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 14; j++) {
				matrix[i][j] = 0;
			}
		}
		availableTargets = 0;
		time = 0;
		steps = 0;
	}
	
}
