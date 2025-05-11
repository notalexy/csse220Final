package csse220final;

import java.awt.Graphics2D;

public class Player extends LivingEntity{

	private java.awt.image.BufferedImage sprite;
	private boolean spriteLoaded;
	private static final String FILEPATH = "src/playerbig.png";
	
	public Player(float x, float y, int width, int height) {
		this.movable = true;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		
		
		//initialize to zero
		this.theta = 0;
		this.thetavel = 0;
		this.xvel = 0;
		this.yvel = 0;
		
		this.sprite = SpriteLoader.getInstance().getSprite(Player.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
	}
	
	public Player(int x, int y, int width, int height) {
		this((float)x, (float)y, width, height);
	}
	
	@Override 
	public void update(float dt) {
		
		this.xvel += 5.0f * (1.0f - 2.0f*Math.random());
		this.yvel += 5.0f * (1.0f - 2.0f*Math.random());
		this.thetavel += 0.5f * (1.0f - 2.0f*Math.random());
		
		super.update(dt);
	}


	@Override
	public void drawDetails(Graphics2D g2d) {
		 if (spriteLoaded) g2d.drawImage(this.sprite, -width/2, -height/2, width, height, this);
	}
	
	@Override
	public void handleCollision() {
		// TODO Auto-generated method stub
		
	}
}
