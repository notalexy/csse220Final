package csse220final;

public class EntityManager {
	
	private static EntityManager entityManager;
	
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
