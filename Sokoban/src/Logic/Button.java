package Logic;

public class Button {
	protected Coordinate coordinate = null;
	protected int width, height;
	protected boolean isPressed;
	
	public Button() {
		coordinate = new Coordinate();
		width = 0;
		height = 0;
		isPressed = false;
	}
	
	public Button(int x, int y, int width, int height) {
		coordinate = new Coordinate(x, y);
		this.width = width;
		this.height = height;
		isPressed = false;
	}

	public int getX() {
		return coordinate.x;
	}

	public void setX(int x) {
		this.coordinate.x = x;
	}

	public int getY() {
		return coordinate.y;
	}

	public void setY(int y) {
		this.coordinate.y = y;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	

	
	
}
