package csse220final;

import java.awt.*;
import java.util.*;

public class EntityManager {
	
	private static EntityManager entityManager; //singleton
	private java.util.List<Entity> entities;
	private final int borderWallSize = 50;
	/**
	 * Singleton get instance command, should always be called when interacting with the entity manager
	 * @return The one and only entityManager
	 */
	public static EntityManager getInstance() {
		if (EntityManager.entityManager == null) {
			EntityManager.entityManager = new EntityManager();
		}
		return EntityManager.entityManager;
	}
	
	/**
	 * Singleton private constructor class
	 */
	private EntityManager() {
		
		//Create a list of all entities
		this.entities = new ArrayList<Entity>();
		
		//construct walls around the border
		int horizontalWalls = GameViewer.SCREEN_WIDTH / borderWallSize;
		int verticalWalls = GameViewer.SCREEN_HEIGHT / borderWallSize;
		System.out.println(horizontalWalls);
		for(int i = 0; i < horizontalWalls; i++) {
			entities.add(new Wall(i*borderWallSize + (GameViewer.SCREEN_WIDTH - horizontalWalls*borderWallSize)/2 + borderWallSize/2, 
					 (GameViewer.SCREEN_HEIGHT- verticalWalls*borderWallSize)/2 + borderWallSize/2,
					 borderWallSize, borderWallSize));
			entities.add(new Wall(i*borderWallSize + (GameViewer.SCREEN_WIDTH - horizontalWalls*borderWallSize)/2 + borderWallSize/2, 
					 GameViewer.SCREEN_HEIGHT - (GameViewer.SCREEN_HEIGHT- verticalWalls*borderWallSize)/2 - borderWallSize/2,
					 borderWallSize, borderWallSize));
		}
		for (int i = 0; i < verticalWalls - 2; i++) {
			entities.add(new Wall((GameViewer.SCREEN_WIDTH - horizontalWalls*borderWallSize)/2 + borderWallSize/2,
					i*borderWallSize + (GameViewer.SCREEN_HEIGHT - verticalWalls*borderWallSize)/2 + 3*borderWallSize/2,
					borderWallSize, borderWallSize));
			entities.add(new Wall( GameViewer.SCREEN_WIDTH - (GameViewer.SCREEN_WIDTH - horizontalWalls*borderWallSize)/2 - borderWallSize/2,
					i*borderWallSize + (GameViewer.SCREEN_HEIGHT - verticalWalls*borderWallSize)/2 + 3*borderWallSize/2,
					borderWallSize, borderWallSize));
		}
		
		//spawns the player
		entities.add(new Player(300, 300, 50, 50));
		
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
}
