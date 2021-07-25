package Data;


// represents a vector with a x and y coordinate
public class Vector {
	private double x;
	private double y;
	
	public Vector(double x, double y) {
		this.setX(x);
		this.setY(y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void add(Vector v) {
		x += v.getX();
		y += v.getY();
	}
	
	// used by the collision check for 2 circles
	public double distanceToSquared(Vector v) {
		double distx = getX()-v.getX();
		distx *= distx;
		double disty = getY()-v.getY();
		disty *= disty;
		return distx + disty;
	}
	
	public double getAngle() {
		return Math.atan2(getY(), getX());
	}
	// used for debugging
	public String toString() {
		return "x: "+ getX()+ " y: "+getY();
	}
	
	public double getMagnitude() {
		double sqr = getX()*getX() + getY()*getY();
		return Math.sqrt(sqr);
	}
	
	// used by the Space class to get the x or y coordinate based on a boolean
	public double get(boolean isX){
		return isX? getX(): getY();
	}
	  
	
	
	
}
