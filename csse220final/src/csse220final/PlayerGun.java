package csse220final;

/**
 * A gun that specifically has the properties for the player
 */
public class PlayerGun extends Gun{
	public PlayerGun(LivingEntity owner, int damage, float cooldown) {
		super(owner, damage, cooldown);
		
		//geometry
		this.width = 25;
		this.height = 45;
		Vector2D offsetVector = new Vector2D(0, this.height / 2.5f + owner.getRadius()).rotate((float) (-Math.PI/6));
		this.xOffset = offsetVector.getX();
		this.yOffset = offsetVector.getY();
		this.bulletSpeed = 1000f;
		this.spread = 0.5f;
		

		
		//attacking properties
		this.cooldownAfterShot = cooldown;
		
		this.team = Damagable.PLAYER_TEAM;
		
	
	}

}
