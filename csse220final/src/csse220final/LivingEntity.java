package csse220final;

public abstract class LivingEntity extends CollisionInitiator implements Damagable{

	private int health;
	private int maxhealth;
	
	public LivingEntity(float x, float y, int radius) {
		super(radius);
		this.x = x;
		this.y = y;
	}


	
	@Override 
	public void update(float dt) {
		super.update(dt);
		if(this.health < 0) this.onDeath();
	}
	
	@Override
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		Vector2D veloVector = new Vector2D(this.xvel, this.yvel);
		if(Vector2D.innerProduct(veloVector, collisionDirection) < 0) {
			Vector2D modifiedVelo = veloVector.subtract(Vector2D.project(veloVector, collisionDirection));
			this.xvel = modifiedVelo.getX();
			this.yvel = modifiedVelo.getY();
		}
	}
	
	public void onDamage(int damage) {
		this.health -= damage;
	}

}
