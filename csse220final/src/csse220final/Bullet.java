package csse220final;

import java.awt.Graphics2D;

public class Bullet extends CollisionInitiator implements Damagable {
	private int damage;
	private int team;
	private static final String FILEPATH = "src/bullet.png";
	
	public Bullet(int radius, int damage, int team, float x, float y, float xvel, float yvel) {
		super(x, y, radius);
		this.damage = damage;
		this.team = team;
		this.xvel = xvel;
		this.yvel = yvel;
		
		this.sprite = SpriteLoader.getInstance().getSprite(Bullet.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
	}

	@Override
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		if (other instanceof Damagable && this.damage != 0) { //only damage entities that can be damaged
			//avoiding this instanceof is unreasonably awkward
			((Damagable) other).onDamage(damage, this.team);
			this.damage = 0; //prevent double damage due to bullet getting destoryed later in the loop
		}
		if (other.isSolid()) {
			onDeath();
		}
		
	}
	
	//allows for shooting bullets out of the air
	@Override
	public void onDamage(int damage, int team) {
		this.onDeath();
		
	}

	public void addToManager() {
		EntityManager.getInstance().addInitiator(this);
	}
	
	@Override
	public void onDeath() {
		super.onDeath();
	}
	

}
