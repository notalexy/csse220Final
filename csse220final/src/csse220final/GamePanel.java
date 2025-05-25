package csse220final;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.*;
/**
 * 
 * Responsible for displaying all objects
 */
public class GamePanel extends JComponent{
	private int fps = 60;
	private JFrame parentFrame;
	
	public GamePanel(JFrame parentFrame) {
		//create the one and only game manager and init drawing
		GameManager.getInstance().initDraw(this);
		
		this.parentFrame = parentFrame;

		
		//send things to game manager
		parentFrame.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyPressed(KeyEvent e) {
					//immediately feed it to the manager
				switch (e.getKeyCode()) { 
		        case KeyEvent.VK_R: GameManager.destroy();  
		        break;
		      }	
					GameManager.getInstance().keyPressed(e);
				}
				
			
			public void keyReleased(KeyEvent e) {
				GameManager.getInstance().keyReleased(e);
			}
		});
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				switch(e.getButton()) {
				case MouseEvent.BUTTON1:
					GameManager.getInstance().mousePressed(e);
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				switch(e.getButton()) {
				case MouseEvent.BUTTON1:
					GameManager.getInstance().mouseReleased(e);
				}
			}
		});
		
		//start animati0on timer
		Timer animationTimer = new Timer(1/fps, e -> update());
		animationTimer.start();
		
	}
	
	private void update() {
		int xpos = 0;
		int ypos = 0;
		try {
		xpos = (int)this.getMousePosition().getX();
		ypos = (int)this.getMousePosition().getY();
		}	catch (NullPointerException e) { //catch if mouse is off screen
			
		}
		
		GameManager.getInstance().update(1.0f/fps, xpos, ypos);
		repaint();
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
		GameManager.getInstance().draw(g2d);
	}
}
