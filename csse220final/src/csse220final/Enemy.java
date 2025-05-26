package csse220final;


/**
 * Class common to all entities that provides the basic movement framework
 */
public abstract class Enemy extends LivingEntity{
	public static final int DEFAULT_RADIUS = 25;
	protected static final String FILEPATH = "src/Enemy.png";
	protected EnemyBehavior behavior;
	
	/**
	 * 
	 * @param x X position
	 * @param y Y position
	 * @param r radius
	 * @param scaling Scaling term to scale enemy stats
	 */
	public Enemy(float x, float y, int r, float scaling) {
		super(x, y, r);	
			
		this.sprite = SpriteLoader.getInstance().getSprite(Enemy.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		this.behavior = null;
		
		this.team = Damagable.ENEMY_TEAM;
	}
	
	
	public void onDeath() {
		super.onDeath();
		GameManager.getInstance().addScore(10);
	}
	
	@Override 
	public void update(float dt){
		this.behavior.update(dt);
		super.update(dt);
	}
}
