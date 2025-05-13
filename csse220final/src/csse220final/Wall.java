package csse220final;

import java.awt.*;
import java.util.List;
import java.util.*;


public class Wall extends Collidable {
	
	private java.awt.image.BufferedImage sprite;
	private boolean spriteLoaded;
	private static final String FILEPATH = "src/WallSprite.png";
	
	public Wall(float x, float y, int width, int height) {
		this.movable = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		
		
		//walls do not move or rotate
		this.theta = 0;
		this.thetavel = 0;
		this.xvel = 0;
		this.yvel = 0;
		
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
	public void respondToCollision(Collidable other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vector2D> generateCollisionVector(Vector2D point) {
		List<Vector2D> outputs = new ArrayList<Vector2D>();
		
		Vector2D centersVector = new Vector2D(this.x, this.y).subtract(point);
		if (Math.abs(centersVector.getX()) < this.width) {
			outputs.add(new Vector2D(
					point.getX(),
					Math.signum(centersVector.getY())*this.height
					));
			outputs.add(new Vector2D(
					0,
					Math.signum(centersVector.getY())
					));
		}
		else if (Math.abs(centersVector.getY()) < this.height) {
			outputs.add(new Vector2D(
					Math.signum(centersVector.getX())*this.width,
					point.getY()
					));
			outputs.add(new Vector2D(
					0,
					Math.signum(centersVector.getX())
					));
		}
		
		return null;
	}
	





}
