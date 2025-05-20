package csse220final;

public abstract class Enemy extends LivingEntity{
	protected static final String FILEPATH = "src/Enemy";
	protected int maxSpeed;
	protected float xvelreq, yvelreq;
	
	public Enemy(float x, float y, int r, float scaling) {
		super(x, y, r);
		this.movable = true;
		this.width = r*2;
		this.height = r*2;
		
		
		
		//initialize to zero
		this.theta = 0;
		this.thetavel = 0;
		this.xvel = 0;
		this.yvel = 0;
		this.maxSpeed = 300;
		
		this.xvelreq = 0;
		this.yvelreq = 0;
		
		this.sprite = SpriteLoader.getInstance().getSprite(Enemy.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
	}
}
