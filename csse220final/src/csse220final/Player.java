package csse220final;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends LivingEntity{

	
	private static final String FILEPATH = "src/playerbig.png";
	
	private int maxSpeed;
	private float xvelreq, yvelreq;
	private final float accel = 1000f;
	
	private float dtlast;
	
	public Player(float x, float y, int r) {
		super(x, y, r);
		
		//prevent friendly fire
		this.team = Damagable.PLAYER_TEAM;
		
		
		//initialize to zero
		this.theta = 0;
		this.thetavel = 0;
		this.xvel = 0;
		this.yvel = 0;
		this.maxSpeed = 300;
		
		this.xvelreq = 0;
		this.yvelreq = 0;
		
		this.maxhealth = 100;
		this.health = this.maxhealth;
		
		this.sprite = SpriteLoader.getInstance().getSprite(Player.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		
		//add a player sword for testing
		this.addWeapon(new PlayerSword(this, 25));
		
	}
	
	public Player(int x, int y, int radius) {
		this((float)x, (float)y, radius);
	}
	
	@Override 
	public void update(float dt) {
		this.dtlast = dt;
		//update player
	
		
				
		
		
		
		
		super.update(dt);
		
		
		
	}
	
	public void requestVelocity(Vector2D reqVector) {
			Vector2D newReqVelo = reqVector;
		if (reqVector.magnitude() > this.maxSpeed) {
			newReqVelo = reqVector.unit().scalarMultiply(maxSpeed);
		}
		this.xvelreq = newReqVelo.getX();
		this.yvelreq = newReqVelo.getY();
		
		float xveldiff = this.xvelreq - this.xvel;
		float yveldiff = this.yvelreq - this.yvel;
		
		
		
		xveldiff = Math.max(-accel*dtlast, Math.min(xveldiff, accel*dtlast));
		yveldiff = Math.max(-accel*dtlast, Math.min(yveldiff, accel*dtlast));
		
		this.xvel += xveldiff;
		this.yvel += yveldiff;
		
	}
	
	public void requestPointTo(Vector2D target) {
		Vector2D currentPos = new Vector2D(this.x, this.y);
		float direction = target.subtract(currentPos).angle() + (float)Math.PI/2;
		this.theta = direction;
		
		
		
		
	}


	@Override
	public void drawDetails(Graphics2D g2d) {
		 if (spriteLoaded) g2d.drawImage(this.sprite, -width/2, -height/2, width, height, this);
	}

	@Override
	public void onDeath() {
		super.onDeath();
		GameManager.getInstance().playerDie();
	}
}
