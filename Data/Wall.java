package Data;

// Represents the screen border
// used for collision detection so the circles don't go of screen
public class Wall {
	private Vector pos;
	private Vector size;
	
	public Wall(int px, int py, int sx, int sy) {
		setPos(new Vector(px, py));
		setSize(new Vector(sx, sy));
	}
	
	public boolean isInsideX(Vector p) {
		if (p.getX() > pos.getX() && p.getX() < pos.getX() + size.getX()) {
			return true;
		}
		return false;
	}
	
	public boolean isInsideY(Vector p) {
		if (p.getY() > pos.getY() && p.getY() < pos.getY() + size.getY()) {
			return true;
		}
		return false;
	}

	public Vector getPos() {
		return pos;
	}

	public void setPos(Vector pos) {
		this.pos = pos;
	}

	public Vector getSize() {
		return size;
	}

	public void setSize(Vector size) {
		this.size = size;
	}
}
