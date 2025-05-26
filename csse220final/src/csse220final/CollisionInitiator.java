package csse220final;

import java.awt.Graphics2D;
import java.util.*;

public abstract class CollisionInitiator extends Collidable {
	private int radius;
	
	
	
	//collision initators are ALL circles
	
	/**
	 * Creates a collision initiator. They must all be circles.
	 * @param x X position
	 * @param y Y position
	 * @param radius 
	 */
	public CollisionInitiator(float x, float y, int radius) {
		super(x, y);
		this.radius = radius;
		this.width = radius*2;
		this.height = radius*2;
	}
	
	/**
	 * Requests the other entity to create a collision vector and see if that vector intersects this enitty
	 * @param other The other entity to check
	 */
	public void initiateCollision(Collidable other) {
		List<Vector2D> collisionValues  = other.generateCollisionVector(new Vector2D(this.x, this.y));
		Vector2D point = collisionValues.get(0);
		//check if point is within the circle
		if ((point.getX() - this.x)*(point.getX() - this.x) 
				+ (point.getY() - this.y)*(point.getY() - this.y) <= this.radius*this.radius) {
			this.respondToCollision(other, collisionValues.get(1));
			other.respondToCollision(this, collisionValues.get(1).scalarMultiply(-1));
		}
	}

	/**
	 * Generates a collision vector pointing towards the other entity
	 * @param the centerpoint of the collision initiator
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

	public int getRadius() {return this.radius;}
}
