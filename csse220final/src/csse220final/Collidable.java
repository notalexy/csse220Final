package csse220final;


//TODO: Implement
public abstract class Collidable extends Entity {
	protected boolean movable;
	protected boolean solid; //Non-solid objects do not push other objects, but still have collision detections
	
	public abstract void handleCollision();
}
