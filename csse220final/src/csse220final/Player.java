package csse220final;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends LivingEntity{

	private java.awt.image.BufferedImage sprite;
	private boolean spriteLoaded;
	private static final String FILEPATH = "src/playerbig.png";
	
	private int maxSpeed;
	private float xvelreq, yvelreq;
	private final float accel = 1000f;
	
	private float dtlast;
	
	public Player(float x, float y, int r) {
		super(x, y, r);
		this.movable = true;
		this.width = r*2;
		this.height = r*2;
		
		
		
		//initialize to zero
		this.theta = 0;
		this.thetavel = 0;
		this.xvel = 0;
		this.yvel = 0;
		this.maxSpeed = 300;
		
		this.xvelreq = 0;
		this.yvelreq = 0;
		
		this.sprite = SpriteLoader.getInstance().getSprite(Player.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
	}
	
	public Player(int x, int y, int radius) {
		this((float)x, (float)y, radius);
	}
	
	@Override 
	public void update(float dt) {
		this.dtlast = dt;
		//update player
	
		
				
		
		
		
		
		super.update(dt);
		//randomly increments velocities for testing

		
		
	}
	
	public void requestVelocity(Vector2D reqVector) {
			Vector2D newReqVelo = reqVector;
		if (reqVector.Magnitude() > this.maxSpeed) {
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


	@Override
	public void drawDetails(Graphics2D g2d) {
		 if (spriteLoaded) g2d.drawImage(this.sprite, -width/2, -height/2, width, height, this);
	}

}
