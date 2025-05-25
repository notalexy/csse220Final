package csse220final;

public abstract class Enemy extends LivingEntity{
	protected static final String FILEPATH = "src/Enemy.png";
	protected EnemyBehavior behavior;
	
	public Enemy(float x, float y, int r, float scaling) {
		super(x, y, r);	
		
		
		//initialize to zero
		this.theta = 0;
		this.thetavel = 0;
		this.xvel = 0;
		this.yvel = 0;
		
		this.sprite = SpriteLoader.getInstance().getSprite(Enemy.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		this.behavior = null;
		
		this.team = Damagable.ENEMY_TEAM;
	}
	

	protected float dtlast;



	public void requestPointTo(Vector2D target) {
		Vector2D currentPos = new Vector2D(this.x, this.y);
		float direction = target.subtract(currentPos).angle() + (float) Math.PI / 2;
		this.theta = direction;

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
