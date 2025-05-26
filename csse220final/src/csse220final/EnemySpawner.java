package csse220final;

/**
 * Class to decide when to spawn entities and then spawn them
 */
public class EnemySpawner {
	private final float maxSpawnTime = 15.0f;
	private float lastSpawnTime;
	private static final int SCALING_START = 5; //wave to start scalling
	private static final float SCALING_RATE = 0.05f;
	private static final int WAVES_TO_ADD_ENEMY = 3;
	private static final int X_ENEMY_SPREAD = 800;
	private static final int Y_ENEMY_SPREAD = 500;
	
	// empty constructor
	public EnemySpawner() {
	}

	/**
	 * call this regularly to update spawning logic
	 * 
	 * @param currentTime
	 */
	public void update(float currentTime) {
		if (currentTime >= (lastSpawnTime + maxSpawnTime
				- ((EntityManager.getInstance().getNumberOfEnemies() < 1) ? 10.0f : 0))) {
			GameManager.getInstance().incrementWave();
			spawn(GameManager.getInstance().getWave(), currentTime);
		}
	}
	
	/**
	 * Spawn the enemies cased on the current match states
	 * @param wave The current wave
	 * @param currentTime Current time
	 */

	private void spawn(int wave, float currentTime) {
		float scaling = 1;
		//check for a perfect clear
		GameManager.getInstance().incrementHealingCondition();
		
		//Increase enemy stats and numbers as waves progress
		if (wave > SCALING_START) {
			scaling += (wave - SCALING_START) * SCALING_RATE;
		}
		int numEnemies = (int) Math.floor(wave / WAVES_TO_ADD_ENEMY) + 1;
		
		//spawn multiple enemies
		for (int i = 0; i < numEnemies; i++) {
			//random check to chose what enemy to spawn
			if (Math.random() < .5) {
				EntityManager.getInstance()
						.addEnemy(new GunEnemy(GameViewer.SCREEN_WIDTH / 2 + (float) (X_ENEMY_SPREAD * (Math.random() - 0.5)),
								GameViewer.SCREEN_HEIGHT / 2 + (float) (Y_ENEMY_SPREAD * (Math.random() - 0.5)), Enemy.DEFAULT_RADIUS, scaling));
			}
			else {
				EntityManager.getInstance()
				.addEnemy(new SwordEnemy(GameViewer.SCREEN_WIDTH / 2 + (float) (X_ENEMY_SPREAD * (Math.random() - 0.5)),
						GameViewer.SCREEN_HEIGHT / 2 + (float) (Y_ENEMY_SPREAD * (Math.random() - 0.5)), Enemy.DEFAULT_RADIUS, scaling));
			}
		}
		//spawn a powerup at every wave
		EntityManager.getInstance().addCollidable(
				new PowerUp(GameViewer.SCREEN_WIDTH / 2 + (float) (X_ENEMY_SPREAD * (Math.random() - 0.5)),
						GameViewer.SCREEN_HEIGHT / 2 + (float) (Y_ENEMY_SPREAD * (Math.random() - 0.5))));
		this.lastSpawnTime = currentTime;

		
	}
}
