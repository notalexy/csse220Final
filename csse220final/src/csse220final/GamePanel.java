package csse220final;

import java.awt.*;
import javax.swing.*;
/**
 * 
 * Responsible for displaying all objects
 */
public class GamePanel extends JComponent{
	private GameManager manager;
	public GamePanel() {
		this.manager = GameManager.getInstance();
	}
}
