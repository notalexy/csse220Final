package csse220final;

import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public class Wall extends Collidable {
	
	private static java.awt.image.BufferedImage sprite;
	private static boolean spriteLoaded;
	
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
		
		if (Wall.sprite == null) {
		 try {
             Wall.sprite = ImageIO.read(new File("src/WallSprite.png"));
             Wall.spriteLoaded = true;
         } catch (IOException e) {
             System.err.println("Error loading image: " + e.getMessage());
             //e.printStackTrace();
             Wall.spriteLoaded = false;
             Wall.spriteLoaded = true;
         }
		}
		
	}
	
	@Override 
	public void update(float dt) {
		this.x += (int)(10.0*(Math.random() - 1.0/2.0));
		this.theta += (0.5*(Math.random() - 1.0/2.0));
	}


	@Override
	public void drawDetails(Graphics2D g2d) {
		// TODO Auto-generated method stub
		 if (spriteLoaded) g2d.drawImage(Wall.sprite, -width/2, -height/2, width, height, this);
	}
	
	@Override
	public void handleCollision() {
		// TODO Auto-generated method stub
		
	}




}
