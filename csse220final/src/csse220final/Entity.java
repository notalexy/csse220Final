package csse220final;

import java.awt.*;
import java.awt.geom.*;

public abstract class Entity {
	protected float x;
	protected float y;
	protected float theta;
	protected float xvel;
	protected float yvel;
	protected float thetavel;
	protected float width;
	protected float height;
	
	public void update(int dt) {
		this.x += xvel*dt;
		this.y += yvel*dt;
		this.x += theta += thetavel*dt;
		
	}
	
	/**
	 * Draw the object on the G2D object
	 * @param g2d Graphics to draw stuff on
	 */
	public void draw(Graphics2D g2d) {
		AffineTransform origin = g2d.getTransform();
		//transforms coordinate system to the object's local frame
		g2d.translate(this.x, this.y);
		g2d.rotate(theta);
		
		this.drawDetails();
		
		g2d.setTransform(origin);
	}
	
	
	/**
	 * This method gets called once when the object is destroyed
	 */
	public void onDeath() {
		
	}
	
	/**
	 * Anything specific to how the object is drawn
	 */
	public abstract void drawDetails();
}
