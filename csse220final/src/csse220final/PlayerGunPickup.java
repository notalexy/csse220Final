package csse220final;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
/**
 * Pickup class to run the chose weapon sequence
 */

public class PlayerGunPickup extends Collidable {
	private int radius = 10;
	
	
	public PlayerGunPickup(int x, int y) {
		
		super(x, y);
		//geometry
		this.width = 25;
		this.height = 45;		
		
		this.sprite = SpriteLoader.getInstance().getSprite(Gun.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
		
		
	}
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		EntityManager.getInstance().getPlayer().addGun();
	}

	@Override
	public List<Vector2D> generateCollisionVector(Vector2D point) {
		Vector2D centersVector = point.subtract(new Vector2D(this.x, this.y));
		//return a vector pointing in the direction between the direction of the initiator point with a radius of r
		List<Vector2D> outputs = new ArrayList<Vector2D>();
		outputs.add(centersVector.unit().scalarMultiply((float) this.radius).add(new Vector2D(this.x, this.y)));
		outputs.add(centersVector.unit());
		return outputs;
	}

	@Override
	public void update(float dt) {
		if(EntityManager.getInstance().getPlayer().hasWeapon()) this.onDeath();
		
	}
}
