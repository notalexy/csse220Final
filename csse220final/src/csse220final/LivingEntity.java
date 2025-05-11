package csse220final;

public abstract class LivingEntity extends Collidable{
	int health;
	
	@Override 
	public void update(float dt) {
		super.update(dt);
		if(this.health < 0) this.onDeath();
	}

}
