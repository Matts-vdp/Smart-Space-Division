package Data;

import java.util.ArrayList;

import Controller.Controller;

// a subclass of space for storing the arrayList of objects
public class SpaceLeaf extends Space {
	public ArrayList<Circle> ar;
	
	public SpaceLeaf(ArrayList<Circle> ar){
		this.ar = ar;
	}
	
	public SpaceLeaf() {
		this.ar = new ArrayList<Circle>();
	}
	
	// solve the collisions with the basic method 
	public void collide(Controller c) {
		if (ar.size() > 0) {
			c.solveCollisionBasic(ar);
		}
	}
	
	// used for debugging
	public String toString(){
		return "l:" + ar.size();
	}
}