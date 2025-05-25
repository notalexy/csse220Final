package csse220final;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Gun extends Entity implements Weapon{
	public static final String FILEPATH = "src/Gun.png";
	
	// attack stuff
	protected int damage;
	protected int team;

	// visuals
	protected float yOffset;
	protected float xOffset;
	protected int length;
	protected float thetaOffset;
	protected LivingEntity owner;
	
	protected float cooldown = 0; // cooldown to prevent sword from repeatedly hitting
	protected float cooldownAfterShot;
	protected float bulletSpeed;
	protected float spread;
	
	public Gun(LivingEntity owner, int damage, float cooldown) {
		this.sprite = SpriteLoader.getInstance().getSprite(Gun.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		this.owner = owner;
		this.damage = damage;
		this.cooldown = cooldownAfterShot;
		
		// initialize to zero
		this.theta = 0;
		this.thetavel = 0;
		this.xvel = 0;
		this.yvel = 0;
	}

	@Override
	public void attack() {
		if(this.cooldown <= 0) {
			//add player velocity to shot
			Vector2D ownerVel = new Vector2D(owner.getXvel(), owner.getYvel());
			float randomOffset = (float) (Math.random() - 0.5f)*spread;
			Vector2D bulletVel = ownerVel.add((new Vector2D(0, -this.bulletSpeed).rotate(theta + randomOffset)));
			
			//spawn bullet at gun
			Vector2D offsetVector = new Vector2D(this.x, this.y).add(
					new Vector2D(this.xOffset, -this.yOffset).rotate(theta));
			Bullet b = new Bullet(10, this.damage, this.team, offsetVector.getX(), offsetVector.getY(), bulletVel.getX(), bulletVel.getY());
			
			b.addToManager();
			this.cooldown = cooldownAfterShot;
		}
		
		

	}
	
	@Override
	public void drawDetails(Graphics2D g2d) {
		// second transformation to draw relative to Player
		AffineTransform origin = g2d.getTransform();
		// transforms coordinate system to the object's local frame
		g2d.rotate(this.thetaOffset);
		g2d.translate(this.xOffset, -this.yOffset);

		if (spriteLoaded)
			g2d.drawImage(this.sprite, -width / 2, -height / 2, width, height, this);
		g2d.setTransform(origin);
	}

	@Override
	public void addToManager() {
		EntityManager.getInstance().addEntity(this);
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


		this.cooldown -= dt;
	}

	public void setDamage(int newDamage) {
		this.damage = newDamage;
	}


}
