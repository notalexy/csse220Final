package csse220final;

import java.awt.Graphics2D;

public class SwordEnemy extends Enemy {

	private float accel = 500f;
	private float dtlast;

	public void requestVelocity(Vector2D reqVector) {
		Vector2D newReqVelo = reqVector;
		if (reqVector.Magnitude() > this.maxSpeed) {
			newReqVelo = reqVector.unit().scalarMultiply(maxSpeed);
		}
		this.xvelreq = newReqVelo.getX();
		this.yvelreq = newReqVelo.getY();

		float xveldiff = this.xvelreq - this.xvel;
		float yveldiff = this.yvelreq - this.yvel;

		xveldiff = Math.max(-accel * dtlast, Math.min(xveldiff, accel * dtlast));
		yveldiff = Math.max(-accel * dtlast, Math.min(yveldiff, accel * dtlast));

		this.xvel += xveldiff;
		this.yvel += yveldiff;

	}

	public void requestPointTo(Vector2D target) {
		Vector2D currentPos = new Vector2D(this.x, this.y);
		float direction = target.subtract(currentPos).angle() + (float) Math.PI / 2;
		this.theta = direction;

	}

	public SwordEnemy(float x, float y, int r, float scale) {
		super(x, y, r, scale);
		this.maxhealth = (int) (scale * 25);
		this.health = maxhealth;
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
}
