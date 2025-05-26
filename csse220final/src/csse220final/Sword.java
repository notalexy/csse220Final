package csse220final;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Sword extends Collidable implements Weapon {
	// attack stuff
	protected int damage;
	protected int team;

	// visuals
	protected float yOffset;
	protected int length;
	protected float thetaOffset;
	protected LivingEntity owner;

	// attack movement
	protected float arc;
	protected float swingSpeed = 2;
	protected float cooldown = 0; // cooldown to prevent sword from repeatedly hitting
	protected float cooldownAfterHit;

	protected boolean swinging;

	/**
	 * Construct a new sword
	 * @param owner The entity that owns the weapon
	 * @param damage How much damage the sword does
	 */
	public Sword(LivingEntity owner, int damage) {
		super(0, 0);
		this.owner = owner;
		this.damage = damage;
	}

	@Override
	public void attack() {
		if (this.swinging == false) {
			this.swinging = true;
		}

	}

	/**
	 * Adds the sword to the entitymanager as a collidable
	 */
	@Override
	public void addToManager() {
		EntityManager.getInstance().addCollidable(this);
	}
	
	@Override
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		// prevent self-collision
		if (other != this.owner) {
			// check if it collides with a damagable object
			//do not damage if sword is not swinging or is on cooldown
			if (this.cooldown <= 0 && thetaOffset != -arc / 2) {
				if (other instanceof Damagable) { //only damage entities that can be damaged
					//avoiding this instanceof is unreasliably awkward
					((Damagable) other).onDamage(damage, this.team);
					this.cooldown = this.cooldownAfterHit;
				}
			}
		}

	}

	@Override
	public List<Vector2D> generateCollisionVector(Vector2D point) {
		Vector2D ownerPos = new Vector2D(owner.getX(), owner.getY());
		Vector2D ownerVector = point.subtract(ownerPos);

		// restrict length of sword vector if its far from stuff
		if (ownerVector.magnitude() > owner.getRadius() + this.length) {
			ownerVector = ownerVector.unit().scalarMultiply(owner.getRadius() + this.length + this.yOffset);
		}

		List<Vector2D> outputs = new ArrayList<Vector2D>();
		outputs.add(new Vector2D(0, 1).scalarMultiply(ownerVector.magnitude()).rotate(thetaOffset + owner.getTheta() + (float)Math.PI).add(ownerPos));
		outputs.add(new Vector2D(0, 1).rotate(thetaOffset + owner.getTheta() + (float)Math.PI));
		return outputs;
	}

	@Override
	public void drawDetails(Graphics2D g2d) {
		// second transformation to draw relative to Player
		AffineTransform origin = g2d.getTransform();
		// transforms coordinate system to the object's local frame
		g2d.rotate(this.thetaOffset);
		g2d.translate(0, -this.yOffset);

		if (spriteLoaded)
			g2d.drawImage(this.sprite, -width / 2, -height / 2, width, height, this);
		g2d.setTransform(origin);
	}

	@Override
	public void update(float dt) {
		// cling sword to owner
		this.x = owner.getX();
		this.y = owner.getY();
		this.xvel = owner.getXvel();
		this.yvel = owner.getYvel();
		this.theta = owner.getTheta();

		super.update(dt);
		//make sword swing
		if (this.swinging) {
			this.thetaOffset += dt * this.swingSpeed;
			if (thetaOffset > arc/2) {
				this.swinging = false;
				thetaOffset = -arc / 2;
			}
		}
		//increment cooldown
		this.cooldown -= dt;
	}

	public void setDamage(int newDamage) {
		this.damage = newDamage;
	}


	@Override
	public int getDamage() {
		return this.damage;		
	}
}
