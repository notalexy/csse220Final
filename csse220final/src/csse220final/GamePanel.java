package csse220final;

import java.awt.*;
import javax.swing.*;
/**
 * 
 * Responsible for displaying all objects
 */
public class GamePanel extends JComponent{
	private int fps = 60;
	private GameManager manager;
	
	public GamePanel() {
		//create the one and only game manager
		this.manager = GameManager.getInstance();
		
		//start animation timer
		Timer animationTimer = new Timer(1/fps, e -> update());
		animationTimer.start();
	}
	
	private void update() {
		manager.update(1.0f/fps);
		repaint();
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
		this.manager.draw(g2d);
	}
}
