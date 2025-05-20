package csse220final;

/**
 * Interface to force some entities to handle getting attacked
 */
public interface Damagable {
	//teams prevent friendly fire
	public static final int PLAYER_TEAM = 0;
	public static final int ENEMY_TEAM = 1;
	
	public void onDamage(int damage, int team);
}
