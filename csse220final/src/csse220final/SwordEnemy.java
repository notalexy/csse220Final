package csse220final;

import java.awt.Graphics2D;

public class SwordEnemy extends Enemy {

	public SwordEnemy(float x, float y, int r, float scale) {
		super(x, y, r, scale);
		this.maxhealth = (int) (scale * 25);
		this.health = maxhealth;
		this.accel = 500;
		this.maxSpeed = (int) (scale * 150);
		this.behavior = new SwordEnemyBehavior(this);
		
		this.addWeapon(new EnemySword(this, (int)(5.0 * scale)));
	}
	

	@Override
	public void drawDetails(Graphics2D g2d) {
		if (spriteLoaded)
			g2d.drawImage(this.sprite, -width / 2, -height / 2, width, height, this);
	}

	@Override
	public void update(float dt) {
		this.dtlast = dt;
				
		//System.out.println(this.health);
		
		
		
		
		super.update(dt);
	}
	
	
}
