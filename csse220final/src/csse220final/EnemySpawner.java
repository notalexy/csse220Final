package csse220final;

/**
 * Class to decide when to spawn entities and then spawn them
 */
public class EnemySpawner {
	private final float maxSpawnTime = 15.0f;
	private float lastSpawnTime;

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
		//heal the player before spawning stuff
		GameManager.getInstance().incrementHealingCondition();
		
		//Increase enemy stats and numbers as waves progress
		if (wave > 5) {
			scaling += (wave - 5) * .05;
		}
		int numEnemies = (int) Math.floor(wave / 3) + 1;
		
		//spawn multiple enemies
		for (int i = 0; i < numEnemies; i++) {
			//random check to chose what enemy to spawn
			if (Math.random() < .5) {
				EntityManager.getInstance()
						.addEnemy(new GunEnemy(GameViewer.SCREEN_WIDTH / 2 + (float) (800 * (Math.random() - 0.5)),
								GameViewer.SCREEN_HEIGHT / 2 + (float) (500 * (Math.random() - 0.5)), 25, scaling));
			}
			else {
				EntityManager.getInstance()
				.addEnemy(new SwordEnemy(GameViewer.SCREEN_WIDTH / 2 + (float) (800 * (Math.random() - 0.5)),
						GameViewer.SCREEN_HEIGHT / 2 + (float) (500 * (Math.random() - 0.5)), 25, scaling));
			}
		}
		//spawn a powerup at every wave
		EntityManager.getInstance().addCollidable(
				new PowerUp(GameViewer.SCREEN_WIDTH / 2 + (float) (800 * (Math.random() - 0.5)),
						GameViewer.SCREEN_HEIGHT / 2 + (float) (500 * (Math.random() - 0.5))));
		this.lastSpawnTime = currentTime;

		
	}
}
