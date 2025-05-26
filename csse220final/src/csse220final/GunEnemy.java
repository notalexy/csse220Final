package csse220final;

import java.awt.Graphics2D;

/**
 * A gun enemy has a gun and shoots at the player
 */
public class GunEnemy extends Enemy {
	
	//gun enemy tries to circle the player
	private float angVelTarget;

	public GunEnemy(float x, float y, int r, float scale) {
		super(x, y, r, scale);
		this.maxhealth = (int) (scale * 25);
		this.health = maxhealth;
		this.accel = 500;
		this.maxSpeed = (int) (scale * 100);
		this.behavior = new GunEnemyBehavior(this);
		this.angVelTarget = scale * .3f;
		
		this.addWeapon(new EnemyGun(this, (int)(4.0 * scale), 1.5f / scale));
	}
	

	@Override
	public void drawDetails(Graphics2D g2d) {
		if (spriteLoaded)
			g2d.drawImage(this.sprite, -width / 2, -height / 2, width, height, this);
	}

	@Override
	public void update(float dt) {
		this.dtlast = dt;
				
		
		
		
		
		super.update(dt);
	}
	
	float getAngVelTarget() {return this.angVelTarget;}
	
	
}
