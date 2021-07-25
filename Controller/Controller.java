package Controller;
import java.util.ArrayList;
import java.util.Random;

import Data.*;
import UI.Main;

// this class controls the flow of the program
// it initializes the wall, circles and GUI
// then it runs in the background as a thread and calls for updates and collision checks
// spawns 10 000 circles
public class Controller extends Thread{
	private Main mainWindow;
	private ArrayList<Circle> circ;
	private Wall boundary;
	private boolean smart = false; // tells with solver to use
	private long timeBetween = 0;
	
	public Controller() {
		boundary = new Wall(0,0,600,600);
		circ = new ArrayList<Circle>();
		initCircles(10000);
		mainWindow = new Main(circ, this);
	}
	
	// makes all circles and spread them over the field
	public void initCircles(int amount) {
		double speed = 0.2;
		int amsq = (int) Math.max(Math.sqrt(amount), 1);
		int size = (int) (boundary.getSize().getX() / (amsq*1.5));
		size = Math.min(size, 100);
		for (int i=0; i<amount; i++) {
			int x = i % amsq;
			int y = i / amsq;
			x *= size + size/2;
			y *= size + size/2;
			x += size/2;
			y += size/2;
			Circle c = new Circle(x, y, size);
			c.setVelocity(new Vector((new Random().nextDouble()*2-1)*speed, (new Random().nextDouble()*2-1)*speed));
			circ.add(c);
		}
	}
	
	public void run() {
		while (true) {
			update();
		}
	}
	
	// updates the GUI and updates the circles
	public void update() {
		long startTime = System.nanoTime();
		mainWindow.update();
		for (Circle c: circ) {
			c.update();
		}
		solveWallCollision();
		if (smart) {
			solveCollisionSmart();
		}
		else {
			solveCollisionBasic(circ);
		}
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		timeBetween = timeElapsed / 1000000;
	}
	
	public void solveWallCollision() {
		for (Circle c: circ) {
			c.CollisionWall(boundary);
		}
	}
	
	// uses a basic double for loop to check for collisions
	public void solveCollisionBasic(ArrayList<Circle> circl) {
		for (Circle c: circl) {
			for (Circle a: circl) {
				if (c == a) 
					break;
				if (c.isCollision(a)) {
					collide(c, a);
				}
			}
		}
	}
	
	// uses a Smart Space Division algorithm to find possible collisions
	public void solveCollisionSmart() {
		Space s = new Space();
		s.divide(circ, true); // divide space start with x axis
		s.collide(this);
	}
	
	// Calculates the needed arguments for the collisionResponse
	public void collide(Circle c1, Circle c2) {
		double dx = c2.getMiddle().getX() - c1.getMiddle().getX();
		double dy = c2.getMiddle().getY() - c1.getMiddle().getY();
		double dist = Math.sqrt(dx*dx + dy*dy);
		Vector normal = new Vector(dx / dist, dy / dist);
		double v1 = c1.getVelocity().getMagnitude();
		double v2 = c2.getVelocity().getMagnitude();
		double dir1 = c1.getVelocity().getAngle();
		double dir2 = c2.getVelocity().getAngle();
		double dirContact = normal.getAngle();
		double radBetween = c1.getRadius() + c2.getRadius();
		double px = c2.getMiddle().getX() - normal.getX() * radBetween;
		double py = c2.getMiddle().getY() - normal.getY() * radBetween;
		c1.setPos(new Vector(px-c1.getRadius(), py-c1.getRadius()));
		collisionResponse(c1, c2, v1, v2, dir1, dir2, dirContact, c1.getSize(), c2.getSize());
	}
	
	
	// calculates the velocity after a collision
	// based on https://en.wikipedia.org/wiki/Elastic_collision
	public void collisionResponse(Circle c1, Circle c2, double v1, double v2, double d1, double d2, double cDir, double m1, double m2) {
		Vector newV1 = new Vector(0,0);
		Vector newV2 = new Vector(0,0);
		double mm = m1 - m2;
		double mmt = m1 + m2;
		double v1s = v1 * Math.sin(d1 - cDir);
		double cp = Math.cos(cDir);
		double sp = Math.sin(cDir);
		double cdp1 = v1 * Math.cos(d1 - cDir);
		double cdp2 = v2 * Math.cos(d2 - cDir);
		double cpp = Math.cos(cDir + Math.PI / 2);
		double spp = Math.sin(cDir + Math.PI / 2);
		
		double t = (cdp1 * mm + 2 * m2 * cdp2) / mmt;
		newV1.setX(t * cp + v1s * cpp);
		newV1.setY(t * sp + v1s * spp);
		cDir += Math.PI;
		
		double v2s = v2 * Math.sin(d2 - cDir);    
	    cdp1 = v1 * Math.cos(d1 - cDir);
	    cdp2 = v2 * Math.cos(d2 - cDir);    
	    t = (cdp2 * -mm + 2 * m1 * cdp1) / mmt;
	    newV2.setX(t * -cp + v2s * -cpp);
	    newV2.setY(t * -sp + v2s * -spp);
		
		c1.setVelocity(newV1);
		c2.setVelocity(newV2);
	}
	
	public boolean getSmart() {
		return smart;
	}
	public void setSmart(boolean s) {
		smart = s;
	}
	
	public long getTimeBetween() {
		return timeBetween;
	}
	
	public void switchSmart() {
		setSmart(!getSmart());
	}
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}
	
}
