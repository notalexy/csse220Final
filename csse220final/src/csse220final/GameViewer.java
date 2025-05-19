package csse220final;

import java.util.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Handles everything dealing with the window
 */
public class GameViewer {
	// warning: system scaling affects this
	public static final int SCREEN_WIDTH = 1706;
	public static final int SCREEN_HEIGHT = 1066 - 32;
	public static final Dimension WINDOW_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

	public static void main(String[] args) {
		//set up frame
		JFrame frame = new JFrame();

		frame.setUndecorated(true);
		frame.setSize(WINDOW_SIZE);
		frame.setTitle("e");
		
		//offset to account for weird scaling issues
		frame.setLocation((int)
				((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-SCREEN_WIDTH)/2)
				,0); //centers the screen horiztonally if the screen width is unexpected, does not re-scale screen due to things depending on pixels
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		GamePanel gamePanel = new GamePanel();
		frame.add(gamePanel);
		
		frame.setVisible(true);
		
		//pass the keyevents to the manager
		frame.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
		        	case KeyEvent.VK_PAGE_DOWN:
		        		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));}
				
					//immediately feed it to the manager
					GameManager.getInstance().keyPressed(e);
				}
			
			public void keyReleased(KeyEvent e) {
				GameManager.getInstance().keyReleased(e);
			}
		});
		
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				switch(e.getButton()) {
				case MouseEvent.BUTTON1:
					GameManager.getInstance().mousePressed(e);
				}
				System.out.println("click");
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("e");
			}
		});
		



		
	}
}
