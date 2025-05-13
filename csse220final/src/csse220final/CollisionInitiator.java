package csse220final;

import java.awt.Graphics2D;
import java.util.*;

public abstract class CollisionInitiator extends Collidable {
	private int radius;
	
	
	//collision initators are ALL circles
	
	public CollisionInitiator(int radius) {
		super();
		this.radius = radius;
	}
	
	public void initiateCollision(Collidable other) {
		List<Vector2D> collisionValues  = other.generateCollisionVector(new Vector2D(this.x, this.y));
		Vector2D point = collisionValues.get(0);
		//check if point is within the circle
		if ((point.getX() - this.x)*(point.getX() - this.x) 
				+ (point.getY() - this.y)*(point.getY() - this.y) <= this.radius*this.radius) {
			this.respondToCollision(other);
			other.respondToCollision(this);
		}
	}

	@Override
	public void respondToCollision(Collidable other) {	
	}

	@Override
	public List<Vector2D> generateCollisionVector(Vector2D point) {
		//return a vector pointing in the direction between the direction of the initiator point with a radius of r
		List<Vector2D> outputs = new ArrayList<Vector2D>();
		outputs.add(new Vector2D(this.x, this.y).subtract(point).unit());
		outputs.add(new Vector2D(this.x, this.y).subtract(point).unit().scalarMultiply(radius));
		return outputs;
	}

}
