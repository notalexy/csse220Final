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
