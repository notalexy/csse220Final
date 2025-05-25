package csse220final;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends LivingEntity{

	
	private static final String FILEPATH = "src/playerbig.png";
	
	private Weapon Sword, Gun;
	
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
		this.accel = 1000.0f;
				
		this.maxhealth = 100;
		this.health = this.maxhealth;
		
		this.sprite = SpriteLoader.getInstance().getSprite(Player.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		
		//add a player sword for testing
		this.Sword = (new PlayerSword(this, 25));
		this.Gun = (new PlayerGun(this, 6, .5f));
		
	}
	
	public Player(int x, int y, int radius) {
		this((float)x, (float)y, radius);
	}
	
	@Override 
	public void update(float dt) {
		super.update(dt);
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
	
	/**
	 * Sets the player's weapon to a gun
	 */
	public void addGun() {
		this.addWeapon(Gun);
	}
	/**
	 * Sets the player's weapon to a sword
	 */
	public void addSword() {
		this.addWeapon(Sword);
	}
}
