
/**
 * contains coordinates for mapping visially along an x,y graph
 * @author info
 *
 */
public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(){
		
	}
	
	public void print() {
		System.out.println("the position of the x and y is  " + x + "," + y);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	

}
