package csse220final;

public class EnemySword extends Sword {


	private static final String FILEPATH = "src/enemySword.png";
	
	public EnemySword(LivingEntity owner, int damage) {
		super(owner, damage);
		
		//geometry
		this.width = 25;
		this.height = 45;
		this.yOffset = this.height / 2.0f + owner.getRadius();
		
		//attacking properties
		this.arc = (float) (2*Math.PI);
		this.swingSpeed = 5;
		this.cooldownAfterHit = 0.2f;
		
		this.team = Damagable.ENEMY_TEAM;
		
		
		
		this.sprite = SpriteLoader.getInstance().getSprite(EnemySword.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		
		
	}

}
