package csse220final;

import java.util.*;

//TODO: Implement
public abstract class Collidable extends Entity {
	protected boolean movable;
	protected boolean solid; //Non-solid objects do not push other objects, but still have collision detections
	
	//collisions is ASYMMETRIC. There are collision initiators and collision responders. 
	//collision initiators are created by overriding handleCollision with contents. 
	//Collision responders are created by setting this
	//all collision responders must have generateCollisionvetor with contents
	
	//known collisoin initators
	//livingentitiy
	//bullets
	
	
	public abstract void respondToCollision(Collidable other, Vector2D collisionDirection);

	
	/*
	 * Collision vector process:
	 * Generate a vector from the responder depending on the responder's bounding box
	 * See if this vector intercepts the initator. 
	 * if so, call respond to collision on both objects
	 */
	
	/**
	 * 
	 * @param point the centerpoint of the collision initiator
	 * @return A list with the point nearest to the object and the surface normal of the surface collied with.
	 */
	public abstract List<Vector2D> generateCollisionVector(Vector2D point);
	
	public boolean isSolid() {
		return solid;
	}
}
