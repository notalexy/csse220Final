package csse220final;

public class EnemySpawner {
	private final float maxSpawnTime = 15.0f;
	private float lastSpawnTime;
	
	public EnemySpawner() {
		spawn(1, 0);

	}
	public void update(float currentTime) {
		if (currentTime > (lastSpawnTime + maxSpawnTime)) {
			GameManager.getInstance().incrementWave();
			spawn(GameManager.getInstance().getWave(), currentTime);
		}
	}

	private void spawn(int wave, float currentTime) {
		float scaling = 1;
		//test scaling that's super fast
		if (wave > 5) {
			scaling += wave*.1;
		}
		
		EntityManager.getInstance().addEnemy(new SwordEnemy(GameViewer.SCREEN_WIDTH / 2 + (float)(500*(Math.random() - 0.5)),
				GameViewer.SCREEN_HEIGHT/ 2 + (float)( 250 * (Math.random() - 0.5)), 25, scaling));
		this.lastSpawnTime = currentTime;
	}
}
