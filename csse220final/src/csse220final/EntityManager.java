package csse220final;

import java.awt.*;
import java.util.*;

public class EntityManager {
	
	private static EntityManager entityManager; //singleton
	
	//there are multiple entity lists to improve performance for collision checking
	private java.util.List<Entity> entities;
	private java.util.List<CollisionInitiator> initators;
	private java.util.List<Collidable> collidables;
	private java.util.List<Enemy> enemies;
	
	private java.util.List<Entity> toRemove;
	private java.util.List<Entity> toAdd;
	
	private java.util.List<Collidable> toAddCollidable;
	
	private Player player; //store the player separately to ensure it gets called seprately
	
	private final int borderWallSize = 50;
	/**
	 * Singleton get instance command, should always be called when interacting with the entity manager
	 * @return The one and only entityManager
	 */
	public static EntityManager getInstance() {
		if (EntityManager.entityManager == null) {
			EntityManager.entityManager = new EntityManager();
			EntityManager.entityManager.startupFuction();
		}
		return EntityManager.entityManager;
	}
	
	/**
	 * Singleton private constructor class
	 */
	private EntityManager() {
		//Create a list of all entities
		//mulitple lists are used to make collisions cheap
		this.entities = new ArrayList<Entity>();
		this.collidables = new ArrayList<Collidable>();
		this.initators = new ArrayList<CollisionInitiator>();
		this.enemies = new ArrayList<Enemy>();
		//asynch add/remove to avoid concurent modificaitons
		this.toRemove = new ArrayList<Entity>();
		this.toAdd = new ArrayList<Entity>();
		this.toAddCollidable = new ArrayList<Collidable>();
				
	}
	
	//created after constructing
	
	private void startupFuction() {
		//construct walls around the border
		int horizontalWalls = GameViewer.SCREEN_WIDTH / borderWallSize;
		int verticalWalls = GameViewer.SCREEN_HEIGHT / borderWallSize;
		
		for(int i = 0; i < horizontalWalls; i++) {
			addCollidable(new Wall(i*borderWallSize + (GameViewer.SCREEN_WIDTH - horizontalWalls*borderWallSize)/2 + borderWallSize/2, 
					 (GameViewer.SCREEN_HEIGHT- verticalWalls*borderWallSize)/2 + borderWallSize/2,
					 borderWallSize, borderWallSize));
			addCollidable(new Wall(i*borderWallSize + (GameViewer.SCREEN_WIDTH - horizontalWalls*borderWallSize)/2 + borderWallSize/2, 
					 GameViewer.SCREEN_HEIGHT - (GameViewer.SCREEN_HEIGHT- verticalWalls*borderWallSize)/2 - borderWallSize/2,
					 borderWallSize, borderWallSize));
		}
		for (int i = 0; i < verticalWalls - 2; i++) {
			addCollidable(new Wall((GameViewer.SCREEN_WIDTH - horizontalWalls*borderWallSize)/2 + borderWallSize/2,
					i*borderWallSize + (GameViewer.SCREEN_HEIGHT - verticalWalls*borderWallSize)/2 + 3*borderWallSize/2,
					borderWallSize, borderWallSize));
			addCollidable(new Wall( GameViewer.SCREEN_WIDTH - (GameViewer.SCREEN_WIDTH - horizontalWalls*borderWallSize)/2 - borderWallSize/2,
					i*borderWallSize + (GameViewer.SCREEN_HEIGHT - verticalWalls*borderWallSize)/2 + 3*borderWallSize/2,
					borderWallSize, borderWallSize));
		}
		
		
		
		//spawns the player
		addPlayer(new Player(GameViewer.SCREEN_WIDTH/ 2, GameViewer.SCREEN_HEIGHT/ 2, 25));
		addCollidable(new PlayerSwordPickup(300, GameViewer.SCREEN_HEIGHT / 2));
		addCollidable(new PlayerGunPickup(GameViewer.SCREEN_WIDTH - 300, GameViewer.SCREEN_HEIGHT / 2));
	}
	
	
	/**
	 * Used for deleting the entityManager
	 */
	public static void destroy() {
		EntityManager.entityManager = null;
	}
	
	/**
	 * Calls update in every entity
	 * @param dt Time since last update
	 */
	public void updateAllEntities(float dt) {
		//purge dead entities
		updateEntities();
		//check collisions first, O(n^2) time complexity
		for(CollisionInitiator i : initators) {
			for(Collidable c : collidables) {
				if (c != i) { //prevent self-collsion
					i.initiateCollision(c);
				}
			}
		}
		//move entities after collisions are checked
		for(Entity e:entities) {
			e.update(dt);
		}
		
	}

	/**
	 * Draws every entity
	 * @param g2d
	 */
	
	public void drawAllEntities(Graphics2D g2d) {
		for(Entity e:entities) {
			e.draw(g2d);
		}
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	//adding and removing entities
	//specific adders are required to ensure the correct lists get updated to run game logic efficiencly
	public void addEntity(Entity e) {
		this.toAdd.add(e);
	}
	
	public void addCollidable(Collidable c) {
		this.addEntity(c);
		this.toAddCollidable.add(c);
	}
	
	public void addInitiator(CollisionInitiator c) {
		this.initators.add(c);
		addCollidable(c);
	}
	
	public void addPlayer(Player p) {
		this.player = p;
		this.addInitiator(p);
	}
	
	
	public void addEnemy(Enemy e) {
		this.enemies.add(e);
		this.addInitiator(e);
	}
	
	/**
	 * Returns the number of entities
	 * @return
	 */
	public int getNumberOfEnemies() {
		return enemies.size();
	}
	
	/**
	 * Schedules an entity to be deleted
	 * @param e
	 */
	public void scheduleDestroy(Entity e) {
		this.toRemove.add(e);
	}
	
	
	/**
	 * Adds and removes entities scheduled to be added and removed to avoid concurent modifications
	 */
	public void updateEntities() {
		for(Entity e: toRemove) {
			this.entities.remove(e);
			this.collidables.remove(e);
			this.initators.remove(e);
			this.enemies.remove(e);
		}

		for(Entity e: toAdd) {
			this.entities.add(e);
		}
		
		for(Collidable c: toAddCollidable) {
			this.collidables.add(c);
		}
		
		toAdd.clear();
		toAddCollidable.clear();
		toRemove.clear();
	}

	
}
