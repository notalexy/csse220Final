package csse220final;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A
 */
public class PowerUp extends Collidable implements Damagable{
	private int radius;
	
	
	
	public PowerUp(float x, float y) {
		super(x, y);
		
		this.sprite = SpriteLoader.getInstance().getSprite(Enemy.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
	}
	@Override
	public void onDamage(int damage, int team) {
		this.onDeath();
		
	}

	@Override
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		// TODO Auto-generated method stub
		
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
	@Override
	public void drawDetails(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

}
