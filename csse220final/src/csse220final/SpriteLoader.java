package csse220final;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SpriteLoader {
	private static SpriteLoader spriteloader;
	private Map<String, java.awt.image.BufferedImage> sprites;
	
	/**
	 * Singleton constructor
	 */
	private SpriteLoader() {
		this.sprites = new HashMap<String, java.awt.image.BufferedImage>();
	}
	
	public static SpriteLoader getInstance() {
		if (SpriteLoader.spriteloader == null) {
			SpriteLoader.spriteloader = new SpriteLoader();
		}
		return SpriteLoader.spriteloader;
	}
	
	/**
	 * Returns the sprite corresponding to the filepath if loaded, loads the sprite if it is not yet loaded
	 * @param filepath the file path of the image
	 * @return the 
	 */
	public  java.awt.image.BufferedImage getSprite(String filepath) {
		if (sprites.containsKey(filepath)) return sprites.get(filepath);
		 try {
			 java.awt.image.BufferedImage sprite = ImageIO.read(new File(filepath));
			 sprites.put(filepath, sprite);
         } catch (IOException e) {
             System.err.println("Error loading image: " + e.getMessage());
             //e.printStackTrace();
             try {
    			 java.awt.image.BufferedImage sprite = ImageIO.read(new File("src/missingTexture.png"));
    			 sprites.put(filepath, sprite);
             }
             catch (IOException f){
            	 System.err.println("Error loading placeholder texutre, check filepath " + e.getMessage() );      
            	 
             }
         }
		 return sprites.get(filepath);
	}
	
}
