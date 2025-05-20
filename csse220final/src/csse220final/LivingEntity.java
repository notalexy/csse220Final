package csse220final;

import java.util.*;

public abstract class LivingEntity extends CollisionInitiator implements Damagable {

	protected int health;
	protected int maxhealth;
	protected List<Weapon> weapons;
	protected int team;

	public LivingEntity(float x, float y, int radius) {
		super(radius);
		this.x = x;
		this.y = y;

		this.weapons = new ArrayList<Weapon>();
		
		this.solid = true;
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		if (this.health < 0)
			this.onDeath();
	}

	@Override
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		if (other.isSolid()) {
			Vector2D veloVector = new Vector2D(this.xvel, this.yvel);
			if (Vector2D.innerProduct(veloVector, collisionDirection) < 0) {
				Vector2D modifiedVelo = veloVector
						.subtract(Vector2D.project(veloVector, collisionDirection).scalarMultiply(1.5f))
						.add(collisionDirection.scalarMultiply(10.0f)); // scalar multiply to make things a bit bouncy
				this.xvel = modifiedVelo.getX();
				this.yvel = modifiedVelo.getY();
			}
		}
	}

	public void onDamage(int damage, int team) {
		if (this.team != team) { //prevent firendly fire
		this.health -= damage;
		}
	}

	public void addWeapon(Weapon w) {
		this.weapons.add(w);
		w.addToManager();
	}

	public void attack() {
		for (Weapon w : weapons) {
			w.attack();
		}
	}
	
	public void onDeath() {
		super.onDeath();
		for (Weapon w: weapons) {
			EntityManager.getInstance().scheduleDestroy((Entity) w);
		}
	}
}
