package csse220final;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

/**
 * Singleton class for handling IO and keeping track of the relevant variables
 * 
 */
public class GameManager{
	private static GameManager gameManager; //singleton
	private EnemySpawner spawner;
	//labels are static to work on game restart
	private static JLabel hp;
	private static JLabel scoreLabel;
	private static JLabel deadLabel;
	private static JLabel restartLabel;
	
	//stores movements
	private boolean w, a, s, d, left;
	
	//game logic
	private int wave;
	private float currentTime = 0;
	private int score = 0;
	
	private GamePanel panel;
	
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
		
		GameManager.hp = new JLabel();
		GameManager.hp.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 26));
		GameManager.hp.setBounds(70, 70, 200, 50);
		
		GameManager.scoreLabel = new JLabel();
		GameManager.scoreLabel.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 26));
		GameManager.scoreLabel.setBounds(70, 120, 200, 50);

		
		GameManager.deadLabel = new JLabel("Ded!");
		GameManager.deadLabel.setBounds(GameViewer.SCREEN_WIDTH/2 - 150, GameViewer.SCREEN_HEIGHT/2 - 150, 300, 300);
		GameManager.deadLabel.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 128));
		GameManager.deadLabel.setVisible(false);
		
		GameManager.restartLabel = new JLabel("Press R to Restart");
		GameManager.restartLabel.setBounds(GameViewer.SCREEN_WIDTH/2 - 150, GameViewer.SCREEN_HEIGHT/2 - 150, 300, 300);
		GameManager.restartLabel.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 26));
		GameManager.restartLabel.setVisible(false);
	}
	
	
	public void initDraw(GamePanel panel) {
		this.panel = panel;
		panel.add(this.hp);
		panel.add(this.scoreLabel);
		panel.add(this.deadLabel);
		panel.add(this.restartLabel);
	}
	
	/**
	 * Used for deleting the GameManager state such that a new one can be created
	 */
	public static void destroy() {
		EntityManager.destroy();
		GameManager.gameManager = null;
	}
	
	/**
	 * Called regularly to update all game logic
	 * 
	 * @param dt: Time since last update
	 */
	public void update(float dt, int xpos, int ypos) {
		
		this.currentTime += dt;
		//send game actions to player
		requestStuffToPlayer(xpos, ypos);
		
		if (this.spawner != null) this.spawner.update(currentTime);
		
		EntityManager.getInstance().updateAllEntities(dt);
		
		hp.setText("HP: "+ Integer.toString(EntityManager.getInstance().getPlayer().getHealth()));
		scoreLabel.setText("Score: "+ Integer.toString(this.score));
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
	
	//sends information to player
	public void requestStuffToPlayer(int xpos, int ypos) {
		int yvel = (s ? 1 : 0) - (w ? 1 : 0);
		int xvel = (d ? 1 : 0) - (a ? 1 : 0);
		
		Vector2D requestedVelo = new Vector2D(xvel, yvel).unit().scalarMultiply(999);
		EntityManager.getInstance().getPlayer().requestVelocity(requestedVelo);
		EntityManager.getInstance().getPlayer().requestPointTo(new Vector2D(xpos, ypos));
		if (this.left) {
			EntityManager.getInstance().getPlayer().attack();
		}
	}
	


	
	//input logic
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
	
	public void addKill() {
		this.score++;
	}
	
	public void playerDie() {
		this.spawner = null; //kill the spawner to prevent lag
		this.deadLabel.setVisible(true);
		this.restartLabel.setVisible(true);
	}

	
}
