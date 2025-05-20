package csse220final;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public abstract class Entity implements ImageObserver {
	protected float x;
	protected float y;
	protected float theta;
	protected float xvel;
	protected float yvel;
	protected float thetavel;
	protected int width;
	protected int height;
	
	protected java.awt.image.BufferedImage sprite;
	protected boolean spriteLoaded;
	

	
	public void update(float dt) {
		this.x += this.xvel*dt;
		this.y += this.yvel*dt;
		this.theta += this.thetavel*dt;
		
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
		
		this.drawDetails(g2d);
		
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
	public abstract void drawDetails(Graphics2D g2d);
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		//required to draw images
		return false;
	}
}
