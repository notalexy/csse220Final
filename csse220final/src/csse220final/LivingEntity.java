package csse220final;

public abstract class LivingEntity extends CollisionInitiator {

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
	

}
