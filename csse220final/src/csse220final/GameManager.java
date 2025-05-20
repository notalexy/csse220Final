package csse220final;

import javax.swing.*;
import java.awt.event.*;

/**
 * Singleton class for managing the game states
 * 
 */
public class GameManager{
	private static GameManager gameManager; //singleton
	private EnemySpawner spawner;
	private boolean w, a, s, d, left;
	private int wave;
	private float currentTime = 0;
	
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
		
		this.spawner = new EnemySpawner();
		
	}
	
	
	/**
	 * Used for deleting the GameManager state such that a new one can be created
	 */
	public static void destroy() {
		EntityManager.destroy();
		GameManager.gameManager = null;
		System.out.println("yeet");
	}
	
	/**
	 * Called regularly to update everything inside
	 * 
	 * @param dt: Time since last update
	 */
	public void update(float dt, int xpos, int ypos) {
		this.currentTime += dt;
		requestStuffToPlayer(xpos, ypos);
		this.spawner.update(currentTime);
		
		EntityManager.getInstance().updateAllEntities(dt);
	}
	
	/**
	 * Draws all game details
	 * @param g2d
	 */
	public void draw(java.awt.Graphics2D g2d) {
		EntityManager.getInstance().drawAllEntities(g2d);
	}

	
	//gameplay logic
	
	public void incrementWave() {
		this.wave++;
	}
	
	public int getWave() {
		return this.wave;
	}
	
	//mouse stuff: CONSIDER SPLITTING CLASS
	
	public void requestStuffToPlayer(int xpos, int ypos) {
		int yvel = (s ? 1 : 0) - (w ? 1 : 0);
		int xvel = (d ? 1 : 0) - (a ? 1 : 0);
		
		Vector2D requestedVelo = new Vector2D(xvel, yvel).unit().scalarMultiply(999);
		EntityManager.getInstance().getPlayer().requestVelocity(requestedVelo);
		EntityManager.getInstance().getPlayer().requestPointTo(new Vector2D(xpos, ypos));
		if (this.left) {
			EntityManager.getInstance().getPlayer().attack();
		}
		//System.out.println(requestedVelo);
	}
	


	public void mousePressed(MouseEvent e) {
		this.left = true;
	}


	public void mouseReleased(MouseEvent e) {
		this.left = false;
		
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
