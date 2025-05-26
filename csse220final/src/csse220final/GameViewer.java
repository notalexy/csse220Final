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
	//dimensions of my screen
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
	
		GamePanel gamePanel = new GamePanel(frame);
		frame.add(gamePanel);
		
		frame.setVisible(true);
		
		//close window shortcut
		frame.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
		        	case KeyEvent.VK_PAGE_DOWN:
		        		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));}
				
			}
		});
		

		



		
	}
}
