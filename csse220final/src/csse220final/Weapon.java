package csse220final;

public interface Weapon {
	/**
	 * Command to make the weapon attack
	 */
	public void attack();
	/**
	 * Add the weapon to the entity manager. This must be called after the constructor
	 */
	public void addToManager();
}
