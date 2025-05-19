package csse220final;

import javax.swing.*;
import java.awt.event.*;

/**
 * Singleton class for managing the game states
 * 
 */
public class GameManager{
	private static GameManager gameManager; //singleton
	private EntityManager entityManager;
	private boolean w, a, s, d, left;
	
	 /**
	 * Singleton get instance command, should always be called when interacting with the game manager
	 * @return The one and only GameManager
	 */
	public static GameManager getInstance() {
		if (GameManager.gameManager == null) {
			GameManager.gameManager = new GameManager();
		}
		return GameManager.gameManager;
	}
	
	/**
	 * Singleton private constructor class
	 */
	private GameManager() {
		//destroys the entity manager if it exits
		EntityManager.destroy();
		//makes a new entityManager
		this.entityManager = EntityManager.getInstance();
	}
	
	
	/**
	 * Used for deleting the GameManager state such that a new one can be created
	 */
	public static void destroy() {
		GameManager.gameManager = null;
	}
	
	/**
	 * Called regularly to update everything inside
	 * 
	 * @param dt: Time since last update
	 */
	public void update(float dt) {
		requestStuffToPlayer();
		this.entityManager.updateAllEntities(dt);
	}
	
	/**
	 * Draws all game details
	 * @param g2d
	 */
	public void draw(java.awt.Graphics2D g2d) {
		this.entityManager.drawAllEntities(g2d);
	}

	//mouse stuff
	
	public void requestStuffToPlayer() {
		int yvel = (s ? 1 : 0) - (w ? 1 : 0);
		int xvel = (d ? 1 : 0) - (a ? 1 : 0);
		
		Vector2D requestedVelo = new Vector2D(xvel, yvel).unit().scalarMultiply(999);
		entityManager.getPlayer().requestVelocity(requestedVelo);
		//System.out.println(requestedVelo);
	}
	


	public void mousePressed(MouseEvent e) {
	
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
        case KeyEvent.VK_W: w = true; break;
        case KeyEvent.VK_A: a = true; break;
        case KeyEvent.VK_S: s = true; break;
        case KeyEvent.VK_D: d = true; break;       
      }	
	}


	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
        case KeyEvent.VK_W: w = false; break;
        case KeyEvent.VK_A: a = false; break;
        case KeyEvent.VK_S: s = false; break;
        case KeyEvent.VK_D: d = false; break;
      }	
	}

}
