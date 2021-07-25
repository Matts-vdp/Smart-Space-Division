package Data;

// stores all needed data to draw the circles
// in this example the acceleration is kept at 0 but this could be used to simulate gravity
public class Circle {
	private Vector pos;
	private Vector velocity;
	private Vector acceleration;
	private int size;
	
	public Circle(int x, int y, int size) {
		setPos(new Vector(x, y));
		setVelocity(new Vector(0,0));
		setAcceleration(new Vector(0,0));
		this.setSize(size);
	}
	
	// calculates the collision response against a Wall object when needed
	public void CollisionWall(Wall w) {
		if (velocity.getX()>0) {
			if (!w.isInsideX(new Vector(pos.getX()+getSize(), pos.getY()))){
				velocity.setX(-velocity.getX());
			}
		}
		else {
			if (!w.isInsideX(new Vector(pos.getX(), pos.getY()))){
				velocity.setX(-velocity.getX());
			}
		}
		if (velocity.getY()>0) {
			if (!w.isInsideY(new Vector(pos.getX(), pos.getY()+getSize()))){
				velocity.setY(-velocity.getY());
			}
		}
		else {
			if (!w.isInsideY(new Vector(pos.getX(), pos.getY()))){
				velocity.setY(-velocity.getY());
			}
		}
	}
	
	public boolean isCollision(Circle c) {
		double dist = getSize()/2 + c.getSize()/2;
		dist *= dist;
		return dist >= getMiddle().distanceToSquared(c.getMiddle());
	}
	
	// used to increment the position and velocity of the object
	public void update() {
		getVelocity().add(getAcceleration());
		updatePosition();
	}
		
	public void updatePosition() {
		getPos().add(getVelocity());
	}
	
	public Vector getMiddle() {
		double r = getSize() / 2;
		return new Vector(pos.getX()+r, pos.getY()+r);
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
	
	public Vector getAcceleration() {
		return acceleration;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public Vector getPos() {
		return pos;
	}

	public void setPos(Vector pos) {
		this.pos = pos;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public double getRadius() {
		return size/2;
	}
	
	// checks if the circle is to the right of the given position
	// or under when isX = false
	public boolean right(double pos, boolean isX){
		return getPos().get(isX) > pos;
	}
	
	// checks if the circle is to the left of the given position
	// or above when isX = false
	public boolean left(double pos, boolean isX){
		return getPos().get(isX)+getSize() < pos;
	}
}
