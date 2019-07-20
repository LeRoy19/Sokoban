package Logic; 

public class Coordinate {

	
		public int x, y, i, j;
		
		public Coordinate() {
			x = 0;
			y = 0;
			i = 0;
			j = 0;
		}
		
		public Coordinate(int i, int j) {
			this.x = j*64;
			this.y = i*64;
			this.i = i;
			this.j = j;
			
		}
		
		public void incrementI() {
			i++;
			y = (64*i);
		}
		
		public void decrementI() {
			i--;
			y = (64*i);
		}
		
		public void incrementJ() {
			j++;
			x = (64*i);
		}
		
		public void decrementJ() {
			j--;
			x = (64*j);
		}
		
}
