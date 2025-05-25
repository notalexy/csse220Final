package csse220final;

public class EnemyGun extends Gun{
	public EnemyGun(LivingEntity owner, int damage, float cooldown) {
		super(owner, damage, cooldown);
		
		//geometry
		this.width = 25;
		this.height = 45;
		Vector2D offsetVector = new Vector2D(0, this.height / 2.5f + owner.getRadius()).rotate((float) (-Math.PI/6));
		this.xOffset = offsetVector.getX();
		this.yOffset = offsetVector.getY();
		this.bulletSpeed = 500f;
		this.spread = 0.3f;
		//randomize cooldown to avoid multiple enemies shooting at once and blocking each other
		this.cooldown = (float)Math.random(); 
		

		
		//attacking properties
		this.cooldownAfterShot = 1.5f;
		
		this.team = Damagable.ENEMY_TEAM;
		
	
	}

}
