package csse220final;

import java.util.*;

public class EntityManager {
	
	private static EntityManager entityManager; //singleton
	
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
		
	}
	
	
	/**
	 * Used for deleting the entityManager
	 */
	public void destroy() {
		EntityManager.entityManager = null;
	}
	
	public void drawAllEntities() {
		
	}
}
