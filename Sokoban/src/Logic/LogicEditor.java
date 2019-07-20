package Logic;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*Editor logico, si occupa di effettuare le operazoni logiche dell'editor*/

public class LogicEditor {

	public int matrix [][] = null;
	public int availableTargets = 0;
	public int steps = 0;
	public int time = 0;
	int playerI=0, playerJ=0;
	public int lastEventI=0, lastEventJ=0;

	
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
				//aggiorno il numero di mappe
				BufferedWriter bOut2 = new BufferedWriter(new FileWriter("NumMaps.txt"));
				bOut2.append(Integer.toString(map));
				bOut2.close();
				
				//righe e colonne
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
	
	public int undo() {
		int targetControl = -10;
		
			if(matrix[lastEventI][lastEventJ]>=10 && matrix[lastEventI][lastEventJ]<20) {
				availableTargets--;
				targetControl = -1;
			}
			else if(matrix[lastEventI][lastEventJ]==-100) {
				targetControl = -2;
			}
			else if(matrix[lastEventI][lastEventJ]<0) {
				availableTargets++;
				targetControl = ((matrix[lastEventI][lastEventJ])+10)*-1;
			}
			
			matrix[lastEventI][lastEventJ]=0;
		return targetControl;
	}
	
}
