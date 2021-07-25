package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Data.Circle;

// represents the JPanel in which the circles are drawn 
public class CircleUI extends JPanel{
	private ArrayList<Circle> circ;
	
	public CircleUI(ArrayList<Circle> circ) {
		this.circ = circ;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Circle c: circ) {
			drawCircle(c, g);
		}
	}
	
	public void drawCircle(Circle c, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillOval((int) c.getPos().getX(), (int) c.getPos().getY(), c.getSize(), c.getSize());
	}
	
	// redraws the panel
	public void update() {
		repaint();
	}
}
