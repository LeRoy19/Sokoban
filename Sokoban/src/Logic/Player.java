package Logic;


public class Player {
	protected Coordinate coordinate; 
	protected String direction;
	
	
	
	public Player(int i, int j, String direction) {
		coordinate = new Coordinate(i,j);
		this.direction = direction;
	}
	
	public Player() {
		coordinate = new Coordinate();
		direction="right";
	}	
	
	public Player(int i, int j) {
		coordinate = new Coordinate(i,j);
		direction="right";
		
	}
	
	
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void MoveRight() {
		direction = "right";
		coordinate.j = coordinate.j+1;
		coordinate.x = coordinate.j*64;
	}
	
	public void MoveLeft() {
		direction = "left";
		coordinate.j = coordinate.j-1;
		coordinate.x = coordinate.j*64;
	}
	
	public void MoveUp() {
		direction = "up";
		coordinate.i = coordinate.i-1;
		coordinate.y = coordinate.i*64;
	}
	
	public void MoveDown() {
		direction = "down";
		coordinate.i = coordinate.i+1;
		coordinate.y = coordinate.i*64;
	}
	

	public int getI() {
		return coordinate.i;
	}
	
	public void setI(int i) {
		coordinate.i = i;
		coordinate.y = i*64;
	}
	
	public int getJ() {
		return coordinate.j;
	}
	
	public void setJ(int j) {
		coordinate.j = j;
		coordinate.x = j*64;
	}
	
	public int getX() {
		return coordinate.x;
	}
	
	public int getY() {
		return coordinate.y;
	}
	
	

}

	
	

