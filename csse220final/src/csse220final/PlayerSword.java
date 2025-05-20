package csse220final;

public class PlayerSword extends Sword {


	private static final String FILEPATH = "src/PlayerSword.png";
	
	public PlayerSword(LivingEntity owner, int damage) {
		super(owner, damage);
		
		//geometry
		this.width = 25;
		this.height = 50;
		this.yOffset = this.height / 2.0f + owner.getRadius();
		
		//attacking properties
		this.arc = (float) (2*Math.PI/4);
		this.swingSpeed = 5;
		this.cooldownAfterHit = 0.2f;
		
		this.team = Damagable.PLAYER_TEAM;
		
		
		
		this.sprite = SpriteLoader.getInstance().getSprite(PlayerSword.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		
		
	}

}