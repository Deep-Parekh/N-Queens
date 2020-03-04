/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class Queen {

	int x;
	int y;
	
	public Queen() {
		this.x = 0;
		this.y = 0;
	}
	
	public Queen(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void moveX(int distance) {
		this.x += distance;
	}
	
	public void moveY(int distance) {
		this.y += distance;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!obj.getClass().equals(this.getClass()))
			return false;
		Queen queen = (Queen) obj;
		return ((this.x == queen.getX()) && (this.y == queen.getY()));
	}
}
