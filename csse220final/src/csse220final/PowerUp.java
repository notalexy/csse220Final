package csse220final;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Power up class
 */
public class PowerUp extends Collidable implements Damagable{
	private final int radius = 32;
	private static final int DAMAGE_TO_ADD = 1;
	private static final int HP_TO_HEAL = 2;
	protected static final String FILEPATH = "src/PowerUp.png";
	
	
	public PowerUp(float x, float y) {
		super(x, y);
		
		this.width = this.radius;
		this.height = this.radius;
		
		this.sprite = SpriteLoader.getInstance().getSprite(PowerUp.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
	}
	@Override
	public void onDamage(int damage, int team) {
		this.onDeath();
		
	}

	@Override
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		
		if(other instanceof LivingEntity) { //only increment damage if its a living entity
			//avoiding this instanceof is unreasliably awkward
			((LivingEntity) other).addDamage(DAMAGE_TO_ADD);
			((LivingEntity) other).onDamage(-PowerUp.HP_TO_HEAL, 2);
			EntityManager.getInstance().addEntity(
					new TextParticle("+" + DAMAGE_TO_ADD + " Strength", this.x - this.width, this.y - this.radius*3.0f,
							((LivingEntity)other).getTeam() == Damagable.ENEMY_TEAM ? Color.RED : Color.GREEN ));
			if (((LivingEntity) other).getTeam() == Damagable.PLAYER_TEAM) {
				GameManager.getInstance().addScore(5);
			}
		}
		
		onDeath(); //kill it if it collides with anythign else
		
	}

	/**
	 * Generates a collision vector pointing towards the other entity
	 */
	@Override
	public List<Vector2D> generateCollisionVector(Vector2D point) {
		Vector2D centersVector = point.subtract(new Vector2D(this.x, this.y));
		//return a vector pointing in the direction between the direction of the initiator point with a radius of r
		List<Vector2D> outputs = new ArrayList<Vector2D>();
		outputs.add(centersVector.unit().scalarMultiply((float) radius).add(new Vector2D(this.x, this.y)));
		outputs.add(centersVector.unit());
		return outputs;
	}
}
