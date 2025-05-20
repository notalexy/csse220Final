package csse220final;

public abstract class Enemy extends LivingEntity{
	protected static final String FILEPATH = "src/Enemy.png";
	protected int maxSpeed;
	protected float xvelreq, yvelreq;
	protected float accel;
	protected EnemyBehavior behavior;
	
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
		
		this.xvelreq = 0;
		this.yvelreq = 0;
		
		this.sprite = SpriteLoader.getInstance().getSprite(Enemy.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		this.behavior = null;
	}
	

	protected float dtlast;

	public void requestVelocity(Vector2D reqVector) {
		Vector2D newReqVelo = reqVector;
		if (reqVector.magnitude() > this.maxSpeed) {
			newReqVelo = reqVector.unit().scalarMultiply(maxSpeed);
		}
		this.xvelreq = newReqVelo.getX();
		this.yvelreq = newReqVelo.getY();

		float xveldiff = this.xvelreq - this.xvel;
		float yveldiff = this.yvelreq - this.yvel;

		xveldiff = Math.max(-this.accel * dtlast, Math.min(xveldiff, this.accel * dtlast));
		yveldiff = Math.max(-this.accel * dtlast, Math.min(yveldiff, this.accel * dtlast));

		this.xvel += xveldiff;
		this.yvel += yveldiff;

	}

	public void requestPointTo(Vector2D target) {
		Vector2D currentPos = new Vector2D(this.x, this.y);
		float direction = target.subtract(currentPos).angle() + (float) Math.PI / 2;
		this.theta = direction;

	}
	@Override 
	public void update(float dt){
		this.behavior.update(dt);
		super.update(dt);
	}
}
