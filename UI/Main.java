package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Controller;
import Data.Circle;

// represents the main window
public class Main extends JFrame {
	private CircleUI cUI;
	private Controller control;
	private JLabel time;
	private JButton b;
	
	public Main(ArrayList<Circle> circ, Controller controller) {
		cUI = new CircleUI(circ);
		cUI.setPreferredSize(new Dimension(600,600));
		control = controller;
		add(cUI, BorderLayout.CENTER);
		b = new JButton("Switch to Smart");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.switchSmart();
			}
		});
		add(b, BorderLayout.NORTH);
		time = new JLabel("0");
		add(time, BorderLayout.SOUTH);
		
		setSize(250, 250);
		setTitle("Smart Space Division Collision detection");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	// used by the controller to update the screen
	public void update() {
		String t = "Switch to ";
		t += control.getSmart()? "basic": "smart";
		b.setText(t);
		time.setText(control.getTimeBetween()+" ms/frame");
		cUI.update();
	}
}
