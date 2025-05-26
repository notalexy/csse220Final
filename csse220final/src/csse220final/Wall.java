package csse220final;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Walls block movement of all objects
 */
public class Wall extends Collidable {
	
	private static final String FILEPATH = "src/WallSprite.png";
	
	public Wall(float x, float y, int width, int height) {
		super(x, y);
		this.solid = true;
		
		this.width = width;
		this.height = height;

		//walls do not move or rotate
		
		
		this.sprite = SpriteLoader.getInstance().getSprite(Wall.FILEPATH);
		this.spriteLoaded = !(this.sprite == null);
	}
	
	public Wall(int x, int y, int width, int height) {
		this((float)x, (float)y, width, height);
	}
	
	@Override 
	public void update(float dt) {

	}


	@Override
	public void drawDetails(Graphics2D g2d) {
		// TODO Auto-generated method stub
		 if (spriteLoaded) g2d.drawImage(this.sprite, -width/2, -height/2, width, height, this);
	}

	@Override
	public void respondToCollision(Collidable other, Vector2D collisionDirection) {
		//doNothing
	}

	/**
	 * The wall collision vector is a combination of 4 lines.
	 * The wall will return the appropriate point based on where the entity is.
	 */
	@Override
	public List<Vector2D> generateCollisionVector(Vector2D point) {
		List<Vector2D> outputs = new ArrayList<Vector2D>();
		
		
		//find vector from wall center to initiator center
		Vector2D centersVector = point.subtract(new Vector2D(this.x, this.y));
		
		//object is (possibly) touching a flat face
		if (Math.abs(centersVector.getX()) <= this.width/2) {
			outputs.add(new Vector2D(
					point.getX(),
					this.y + Math.signum(centersVector.getY())*this.height/2
					));
			outputs.add(new Vector2D(
					0,
					Math.signum(centersVector.getY())
					));
		}
		else if (Math.abs(centersVector.getY()) <= this.height/2) {
			outputs.add(new Vector2D(
					this.x + Math.signum(centersVector.getX())*this.width/2,
					point.getY()
					));
			outputs.add(new Vector2D(
					Math.signum(centersVector.getX()),
					0					
					));
		}
		
		else {	
		//object is (possibly) touching a corner
		outputs.add(new Vector2D(
				Math.signum(centersVector.getX())*this.width/2,
				Math.signum(centersVector.getY())*this.height/2
				));
		outputs.add(centersVector.subtract(new Vector2D(this.width/2 * Math.signum(centersVector.getX()), 
				this.width/2 * Math.signum(centersVector.getY()))).unit());
		}
		
		return outputs;
	}






}
