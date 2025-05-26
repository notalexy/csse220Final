package csse220final;

/**
 * Class to handle enemy AI behavior and decision making
 */
public abstract class EnemyBehavior {
	protected Enemy owner;
	
	
	/**
	 * Creates the enemy behavior and attaches it to its woner
	 * @param owner
	 */
	public EnemyBehavior(Enemy owner) {
		this.owner = owner;
	}
	
	public abstract void update(float dt);
}
