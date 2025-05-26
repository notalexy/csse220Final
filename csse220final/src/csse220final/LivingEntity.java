package csse220final;

import java.awt.Color;
import java.util.*;

/**
 * Living entities are entities which have health, can die after losing health, and can wield weapons.
 * Living entities also has methods to request changes in velocity
 */
public abstract class LivingEntity extends CollisionInitiator implements Damagable {

	protected int health;
	protected int maxhealth;
	protected int maxSpeed;
	protected float xvelreq, yvelreq;
	protected float accel;
	protected float dtlast;
	
	
	//can have no weapons
	protected List<Weapon> weapons;
	
	//used to prevent friendly fire
	protected int team;
	
	
	/**
	 * Creates the living entity variables for an entity
	 * @param x X position of the entity
	 * @param y Y position of enemy
	 * @param radius The radius of the entity
	 */
	public LivingEntity(float x, float y, int radius) {
		super(x, y, radius);
		
		this.xvelreq = 0;
		this.yvelreq = 0;

		this.weapons = new ArrayList<Weapon>();
		
		this.solid = true;
	}
	
	

	@Override
	public void update(float dt) {
		this.dtlast = dt;
		super.update(dt);
		if (this.health <= 0)
			this.onDeath();
	}

	/**
	 * Prevents movement in the direction of a solid entity
	 * @param other collidable object
	 * @param direction of collision
	 */
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
	
	
	/**
	 * @param damage The amount of damage to deal. A negative value will heal the target
	 * @param The team of the damage sourse
	 */
	public void onDamage(int damage, int team) {
		if (this.team != team) { //prevent friendly fire
			if (this.team == Damagable.PLAYER_TEAM) {
				if (damage < 0) {
					EntityManager.getInstance().addEntity(
							new TextParticle("+" + Math.abs(damage), this.x, this.y - this.getRadius()*1.5f, Color.GREEN));
				} else {
					EntityManager.getInstance().addEntity(
					new TextParticle("-" + damage, this.x, this.y - this.getRadius()*1.5f, Color.RED));
				}
					
			}
			
			if (this.team == Damagable.ENEMY_TEAM) {
				if (damage < 0) {
					EntityManager.getInstance().addEntity(
							new TextParticle("+" + Math.abs(damage), this.x, this.y - this.getRadius()*1.5f, Color.ORANGE));
				} else {
					EntityManager.getInstance().addEntity(
					new TextParticle("-" + damage, this.x, this.y - this.getRadius()*1.5f, Color.MAGENTA));
				}
					
			}
			this.health = Math.clamp(this.health - damage, 0, this.maxhealth);
			
		}
	}

	/**
	 * Adds a weapon to the player
	 * @param w
	 */
	public void addWeapon(Weapon w) {
		this.weapons.add(w);
		w.addToManager();
	}

	/**
	 * Makes the entity attack using each weapon it has
	 */
	public void attack() {
		for (Weapon w : weapons) {
			w.attack();
		}
	}
	/**
	 * Requests the entity to move at a targeted velocity. 
	 * Velocities are limited by both maximum velocity and acceleration
	 * @param reqVector A velocity vector to reach
	 */
	public void requestVelocity(Vector2D reqVector) {
		Vector2D newReqVelo = reqVector;
		
		//cap to max speed
		if (reqVector.magnitude() > this.maxSpeed) {
			newReqVelo = reqVector.unit().scalarMultiply(maxSpeed);
		}
		
		this.xvelreq = newReqVelo.getX();
		this.yvelreq = newReqVelo.getY();

		float xveldiff = this.xvelreq - this.xvel;
		float yveldiff = this.yvelreq - this.yvel;
		
		//limit acceleration
		xveldiff = Math.max(-this.accel * dtlast, Math.min(xveldiff, this.accel * dtlast));
		yveldiff = Math.max(-this.accel * dtlast, Math.min(yveldiff, this.accel * dtlast));

		this.xvel += xveldiff;
		this.yvel += yveldiff;

	}
	
	/**
	 * Requests the entity to point to a certain point
	 * @param target The point to point at
	 */
	public void requestPointTo(Vector2D target) {
		Vector2D currentPos = new Vector2D(this.x, this.y);
		float direction = target.subtract(currentPos).angle() + (float)Math.PI/2;
		this.theta = direction;
	}
	
	@Override
	public void onDeath() {
		super.onDeath();
		//weapons must be purged from the manager to avoid lag
		for (Weapon w: weapons) {
			EntityManager.getInstance().scheduleDestroy((Entity) w);
		}
	}
	
	/**
	 * Returns the entitiy's health
	 * @return
	 */
	public int getHealth(){ 
		return this.health;
	}
	
	/**
	 * Checks if the entity has a weapon
	 * @return
	 */
	public boolean hasWeapon() {
		return this.weapons.size() != 0;
	}
	
	public void addDamage(int damage) {
		for(Weapon w:weapons) {
			w.setDamage(w.getDamage() + damage);
		}
	}
}
