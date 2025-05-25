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
	private JLabel hp;
	private JLabel scoreLabel;
	private JLabel deadLabel;
	private JLabel restartLabel;
	private JLabel helpLabel;
	
	//stores movements
	private boolean w, a, s, d, left;
	
	//game logic
	private int wave;
	private float currentTime = 0;
	private int score = 0;
	
	private int lasthealth = 100;
	
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
		
		this.hp = new JLabel();
		this.hp.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 26));
		this.hp.setBounds(70, 70, 200, 50);
		
		this.scoreLabel = new JLabel();
		this.scoreLabel.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 26));
		this.scoreLabel.setBounds(70, 120, 200, 50);

		
		this.deadLabel = new JLabel("Ded!");
		this.deadLabel.setBounds(GameViewer.SCREEN_WIDTH/2 - 150, GameViewer.SCREEN_HEIGHT/2 - 150, 300, 300);
		this.deadLabel.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 128));
		this.deadLabel.setVisible(false);
		
		this.restartLabel = new JLabel("Press R to Restart");
		this.restartLabel.setBounds(GameViewer.SCREEN_WIDTH/2 - 150, GameViewer.SCREEN_HEIGHT/2, 300, 300);
		this.restartLabel.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 26));
		this.restartLabel.setVisible(false);
		
		this.helpLabel = new JLabel(
				"<html>You have a very bad feeling about this...<br> You see a very sharp sword to your left, and a gun you do not know how to use to the right<br><br> WASD to move, Left Click to Attack. R to restart, PgDown to close<br>");
		this.helpLabel.setBounds(GameViewer.SCREEN_WIDTH/2 - 300, GameViewer.SCREEN_HEIGHT/2 - 400, 600, 300);
		this.helpLabel.setFont(new Font(hp.getFont().getName(), Font.PLAIN, 26));
		this.helpLabel.setVisible(true);
	}
	
	
	public void initDraw(GamePanel panel) {
		this.panel = panel;
		panel.add(this.hp);
		panel.add(this.scoreLabel);
		panel.add(this.deadLabel);
		panel.add(this.restartLabel);
		panel.add(this.helpLabel);
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
		
		if(EntityManager.getInstance().getPlayer().hasWeapon()) {
			helpLabel.setVisible(false);
		}
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
	
	public void cleanUp() {
		
		panel.remove(hp);
		panel.remove(scoreLabel);
		panel.remove(deadLabel);
		panel.remove(restartLabel);
		
	}

	//adds a way for the player to heal
	public void incrementHealingCondition() {
		// TODO Auto-generated method stub
		Player p = EntityManager.getInstance().getPlayer();
		if (p.getHealth() == this.lasthealth && EntityManager.getInstance().getNumberOfEnemies() == 0) {
			p.onDamage(-10, 2);
		}
		this.lasthealth = p.getHealth();
		
	}



	
}
