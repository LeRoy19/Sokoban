package Logic;

import java.awt.Dimension;

import Graphics.Level;

public class GameManager {
	private Level level = null;
	
	public GameManager(int playerColour, int map) {
		Dimension d = new Dimension (0,0);
		level = new Level(d, playerColour, map);
	}
	
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
			}
			if(level.map.matrix[i][j+1]>=10 && level.map.matrix[i][j+1]<20 && j+2<level.map.columns) { //se sta spingendo un blocco mobile
				int value = level.map.matrix[i][j+1]; //colore del blocco
				if(level.map.matrix[i][j+2] == 0) { //se c'è spazio a destra
					level.map.player.MoveRight();
					level.map.matrix[i][j+1]-=value;
					level.map.matrix[i][j+2]+=value;
				}
				if(level.map.matrix[i][j+2]==(value*(-1))){//c'è un buco dello stesso colore 
					level.map.player.MoveRight();
					level.map.matrix[i][j+1]-=value;
					level.map.matrix[i][j+2]+=value + value-9;
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
			}
			if(level.map.matrix[i][j-1]>=10 && level.map.matrix[i][j-1]<20 && j-2 >= 0) { //se sta spingendo un blocco mobile
				int value = level.map.matrix[i][j-1]; //colore del blocco
				if(level.map.matrix[i][j-2] == 0) { //se c'è spazio a sinistra
					level.map.player.MoveLeft();
					level.map.matrix[i][j-1]-=value;
					level.map.matrix[i][j-2]+=value;
				}
				if(level.map.matrix[i][j-2]==(value*(-1))){//c'è un buco dello stesso colore 
						level.map.player.MoveLeft();
						level.map.matrix[i][j-1]-=value;
						level.map.matrix[i][j-2]+=value + value-9;
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
			}
			if(level.map.matrix[i-1][j]>=10 && level.map.matrix[i-1][j]<20 && i-2 >= 0) { //se sta spingendo un blocco mobile
				int value = level.map.matrix[i-1][j]; //colore del blocco
				if(level.map.matrix[i-2][j] == 0) { //se c'è spazio sopra
					level.map.player.MoveUp();
					level.map.matrix[i-1][j]-=value;
					level.map.matrix[i-2][j]+=value;
				}
				if(level.map.matrix[i-2][j]==(value*(-1))){//c'è un buco dello stesso colore 
						level.map.player.MoveUp();
						level.map.matrix[i-1][j]-=value;
						level.map.matrix[i-2][j]+=value + value-9;
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
			}
			if(level.map.matrix[i+1][j]>=10 && level.map.matrix[i+1][j]<20 && i+2 >= 0) { //se sta spingendo un blocco mobile
				int value = level.map.matrix[i+1][j]; //colore del blocco
				if(level.map.matrix[i+2][j] == 0) { //se c'è spazio sotto
					level.map.player.MoveDown();
					level.map.matrix[i+1][j]-=value;
					level.map.matrix[i+2][j]+=value;
				}
				if(level.map.matrix[i+2][j]==(value*(-1))){//c'è un buco dello stesso colore 
						level.map.player.MoveDown();
						level.map.matrix[i+1][j]-=value;
						level.map.matrix[i+2][j]+=value + value-9;
					}
				}
		}
	}
	
	
	
	
	

}
