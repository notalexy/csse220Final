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
	public static final Dimension WINDOW_SIZE = new Dimension(1707, 1067 - 32);

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setSize(WINDOW_SIZE);
		frame.setTitle("e");
		
		frame.setLocation(0, 0);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		GamePanel gamePanel = new GamePanel();
		frame.add(gamePanel);
		
		frame.setVisible(true);
		
		
		//debug shortcut to rapidly close windows that eclipse opens up
		frame.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
		        	case KeyEvent.VK_PAGE_DOWN: frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        	}
				}
		   	});
		
		
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	}
}
