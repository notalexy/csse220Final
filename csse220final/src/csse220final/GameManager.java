package csse220final;

/**
 * Singleton class for managing the game states
 * 
 */
public class GameManager {
	private static GameManager gameManager; //singleton
	private EntityManager entities;
	
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
		EntityManager.getInstance().destroy();
		this.entities = EntityManager.getInstance();
	}
	
	
	/**
	 * Used for deleting the GameManager state such that a new one can be created
	 */
	public void destroy() {
		GameManager.gameManager = null;
	}
}
