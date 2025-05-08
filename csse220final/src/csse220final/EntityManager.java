package csse220final;

import java.awt.*;
import java.util.*;

public class EntityManager {
	
	private static EntityManager entityManager; //singleton
	private java.util.List<Entity> entities;
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
		
		//Current test methods
		this.entities = new ArrayList<Entity>();
		entities.add(new Wall(500, 500, 50, 50));
		entities.add(new Wall(500, 600, 50, 50));
		entities.add(new Wall(500, 800, 50, 50));
	}
	
	
	/**
	 * Used for deleting the entityManager
	 */
	public void destroy() {
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
