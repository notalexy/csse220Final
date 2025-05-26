package csse220final;


/**
 * Class common to all entities that provides the basic movement framework
 */
public abstract class Enemy extends LivingEntity{
	protected static final String FILEPATH = "src/Enemy.png";
	protected EnemyBehavior behavior;
	
	public Enemy(float x, float y, int r, float scaling) {
		super(x, y, r);	
			
		this.sprite = SpriteLoader.getInstance().getSprite(Enemy.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		this.behavior = null;
		
		this.team = Damagable.ENEMY_TEAM;
	}
	
	
	public void onDeath() {
		super.onDeath();
		GameManager.getInstance().addKill();
	}
	
	@Override 
	public void update(float dt){
		this.behavior.update(dt);
		super.update(dt);
	}
}
