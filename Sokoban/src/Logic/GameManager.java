package Logic;

import Graphics.Level;

/*classe che controlla gli spostamenti del giocatore nella mappa*/

public class GameManager {
	private Level level = null;
	
	public GameManager(Level level) {
		this.level=level;
	}
	
	public void goRight() {
		int i = level.map.player.getI();
		int j = level.map.player.getJ();
		level.player.MoveRight();
		if(j+1 < level.map.columns) {
			if(level.map.matrix[i][j+1]>=0 && level.map.matrix[i][j+1] < 10) { //c'è prato oppure un buco pieno
				level.map.player.MoveRight();
				if(level.getMode()==1) {
					level.map.incrementActualSteps();
				}
			}
			
			if(level.map.matrix[i][j+1]>=10 && level.map.matrix[i][j+1]<20 && j+2<level.map.columns) { //se sta spingendo un blocco mobile
				int value = level.map.matrix[i][j+1]; //colore del blocco
				
				if(level.map.matrix[i][j+2] == 0) { //se c'è spazio a destra
					level.map.player.MoveRight();
					level.map.matrix[i][j+1]-=value;
					level.map.matrix[i][j+2]+=value;
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
				}
				
				else if(level.map.matrix[i][j+2] > 0 && level.map.matrix[i][j+2] < 10) {
					level.map.player.MoveRight();
					level.map.matrix[i][j+1]-=value;
					level.map.matrix[i][j+2]+=value - level.map.matrix[i][j+2];
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
				}
				
				else if(level.map.matrix[i][j+2]==(value*(-1))){//c'è un buco dello stesso colore 
					level.map.player.MoveRight();
					level.map.matrix[i][j+1]-=value;
					
					for(int k = 0; k<level.map.targets.size(); k++) {
						if(level.map.targets.get(k).i == i && level.map.targets.get(k).j == j+2)
							level.map.targets.get(k).actualValue = level.map.matrix[i][j+2]+=value + value-9;
					}
					
					level.map.matrix[i][j+2]=0;
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
				}
			}
		}
	}
	
	public void goLeft() {
		int i = level.map.player.getI();
		int j = level.map.player.getJ();
		level.player.MoveLeft();
		if(j-1 >= 0) {
			if(level.map.matrix[i][j-1]>=0 && level.map.matrix[i][j-1] < 10) { //c'è prato oppure un buco pieno
				level.map.player.MoveLeft();
				if(level.getMode()==1) {
					level.map.incrementActualSteps();
				}
			}
			if(level.map.matrix[i][j-1]>=10 && level.map.matrix[i][j-1]<20 && j-2 >= 0) { //se sta spingendo un blocco mobile
				int value = level.map.matrix[i][j-1]; //colore del blocco
				if(level.map.matrix[i][j-2] == 0) { //se c'è spazio a sinistra
					level.map.player.MoveLeft();
					level.map.matrix[i][j-1]-=value;
					level.map.matrix[i][j-2]+=value;
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
				}
				else if(level.map.matrix[i][j-2] > 0 && level.map.matrix[i][j-2] < 10) { //c'è un buco pieno
					level.map.player.MoveLeft();
					level.map.matrix[i][j-1]-=value;
					level.map.matrix[i][j-2]+=value - level.map.matrix[i][j-2];
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
				}
				else if(level.map.matrix[i][j-2]==(value*(-1))){//c'è un buco dello stesso colore 
						level.map.player.MoveLeft();
						level.map.matrix[i][j-1]-=value;
						for(int k = 0; k<level.map.targets.size(); k++) {
							if(level.map.targets.get(k).i == i && level.map.targets.get(k).j == j-2)
								level.map.targets.get(k).actualValue = level.map.matrix[i][j-2]+=value + value-9;
						}
						level.map.matrix[i][j-2]=0;
						if(level.getMode()==1) {
							level.map.incrementActualSteps();
						}
					}
				}
		}	
	}
	
	public void goUp() {
		level.player.MoveUp();
		int i = level.map.player.getI();
		int j = level.map.player.getJ();
		if(i-1>=0) {
			if(level.map.matrix[i-1][j]>=0 && level.map.matrix[i-1][j] < 10) { //c'è prato oppure un buco pieno
				level.map.player.MoveUp();
				if(level.getMode()==1) {
					level.map.incrementActualSteps();
				}
			}
			if(level.map.matrix[i-1][j]>=10 && level.map.matrix[i-1][j]<20 && i-2 >= 0) { //se sta spingendo un blocco mobile
				int value = level.map.matrix[i-1][j]; //colore del blocco
				if(level.map.matrix[i-2][j] == 0) { //se c'è pato sopra
					level.map.player.MoveUp();
					level.map.matrix[i-1][j]-=value;
					level.map.matrix[i-2][j]+=value;
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
				}
				else if(level.map.matrix[i-2][j] > 0 && level.map.matrix[i-2][j] < 10) { //c'è un buco pieno
					level.map.player.MoveDown();
					level.map.matrix[i-1][j]-=value;
					level.map.matrix[i-2][j]+=value - level.map.matrix[i-2][j];
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
				}
				else if(level.map.matrix[i-2][j]==(value*(-1))){//c'è un buco dello stesso colore 
						level.map.player.MoveUp();
						level.map.matrix[i-1][j]-=value;
						for(int k = 0; k<level.map.targets.size(); k++) {
							if(level.map.targets.get(k).i == i-2 && level.map.targets.get(k).j == j)
								level.map.targets.get(k).actualValue = level.map.matrix[i-2][j]+=value + value-9;
						}
						level.map.matrix[i-2][j]=0;
						if(level.getMode()==1) {
							level.map.incrementActualSteps();
						}
					}
				}
		}
	}
	
	public void goDown() {
		level.player.MoveDown();
		int i = level.map.player.getI();
		int j = level.map.player.getJ();
		if(i+1<level.map.rows) {
			if(level.map.matrix[i+1][j]>=0 && level.map.matrix[i+1][j] < 10) { //c'è prato oppure un buco pieno
				level.map.player.MoveDown();
				if(level.getMode()==1) {
					level.map.incrementActualSteps();
				}
			}
			
			if(level.map.matrix[i+1][j]>=10 && level.map.matrix[i+1][j]<20 && i+2 < level.map.rows) { //se sta spingendo un blocco mobile
				int value = level.map.matrix[i+1][j]; //colore del blocco
				if(level.map.matrix[i+2][j] == 0) { //se c'è prato sotto
					level.map.player.MoveDown();
					level.map.matrix[i+1][j]-=value;
					level.map.matrix[i+2][j]+=value;
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
				}
				
				else if(level.map.matrix[i+2][j] > 0 && level.map.matrix[i+2][j] < 10) { //c'è un buco pieno
					level.map.player.MoveDown();
					level.map.matrix[i+1][j]-=value;
					level.map.matrix[i+2][j]+=value - level.map.matrix[i+2][j];
					if(level.getMode()==1) {
						level.map.incrementActualSteps();
					}
					
				}
				else if(level.map.matrix[i+2][j]==(value*(-1))){//c'è un buco dello stesso colore 
						level.map.player.MoveDown();
						level.map.matrix[i+1][j]-=value;
						for(int k = 0; k<level.map.targets.size(); k++) {
							if(level.map.targets.get(k).i == i+2 && level.map.targets.get(k).j == j)
								level.map.targets.get(k).actualValue = level.map.matrix[i+2][j]+=value + value-9;
						}
						level.map.matrix[i+2][j]=0;
						if(level.getMode()==1) {
							level.map.incrementActualSteps();
						}
						
					}
				}
		}
	}
	
	
	
	
	

}
