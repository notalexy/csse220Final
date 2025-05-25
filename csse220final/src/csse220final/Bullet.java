package csse220final;

import java.awt.Graphics2D;

public class Bullet extends CollisionInitiator implements Damagable {
	private int damage;
	private int team;
	private static final String FILEPATH = "src/bullet.png";
	
	public Bullet(int radius, int damage, int team, float x, float y, float xvel, float yvel) {
		super(radius);
		this.xvel = xvel;
		this.yvel = yvel;
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.team = team;
	}

	@Override
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		if (other instanceof Damagable) {
			((Damagable) other).onDamage(damage, this.team);
		}
		if (other.isSolid()) {
			onDeath();
		}
		
	}

	@Override
	public void drawDetails(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	
	//allows for shooting bullets out of hte air
	@Override
	public void onDamage(int damage, int team) {
		this.onDeath();
		
	}

}
