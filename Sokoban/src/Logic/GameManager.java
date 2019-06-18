package Logic;

import Graphics.Level;

public class GameManager {
	private Level level = null;
	
	public GameManager(String playerColour) {
		level = new Level(playerColour);
	}
	
	public GameManager(Level level) {
		this.level=level;
	}
	
	public void goRight() {
		if(level.map.player.getJ()+1 < level.map.columns) {
			if(level.map.matrix[level.map.player.getI()][level.map.player.getJ()+1]==0) {
				level.map.player.MoveRight();
			}
		}
	}
	
	public void goLeft() {
		if(level.map.player.getJ()-1 >= 0) {
			if(level.map.matrix[level.map.player.getI()][level.map.player.getJ()-1]==0) {
				level.map.player.MoveLeft();
			}
		}
	}
	
	public void goUp() {
		if(level.map.player.getI()-1>=0) {
			if(level.map.matrix[level.map.player.getI()-1][level.map.player.getJ()]==0) {
				level.map.player.MoveUp();
			}
		}
	}
	
	public void goDown() {
		if(level.map.player.getI()+1<level.map.rows) {
			if(level.map.matrix[level.map.player.getI()+1][level.map.player.getJ()]==0) {
				level.map.player.MoveDown();
			}
		}
	}
	
	
	
	

}
