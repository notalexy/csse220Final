package csse220final;

import javax.swing.*;

/**
 * Singleton class for managing the game states
 * 
 */
public class GameManager{
	private static GameManager gameManager; //singleton
	private EntityManager entityManager;
	
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
		
		//TODO: Add game stuff
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
		this.entityManager.updateAllEntities(dt);
	}
	
	/**
	 * Draws all game details
	 * @param g2d
	 */
	public void draw(java.awt.Graphics2D g2d) {
		//TODO: Draw non-entity stuff
		
		
		this.entityManager.drawAllEntities(g2d);
	}
}
