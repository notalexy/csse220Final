package csse220final;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
/**
 * Class for adding a text particle to the screen
 */
public class TextParticle extends Entity {
	private String text;
	private float time = 0.5f; //how long the particle lasts
	private Color color;
	
	/**
	 * 
	 * @param text The text of the particle
	 * @param x X position
	 * @param y Y position
	 * @param color Color of text
	 */
	public TextParticle(String text, float x, float y, Color color) {
		super(x, y);
		this.text = text;
		this.color = color;
		
	}
	
	/**
	 * 
	 * @param text The text of the particle
	 * @param x X position
	 * @param y Y position
	 * @param color Color of text
	 * @param time How long the text lasts
	 */
	public TextParticle(String text, float x, float y, Color color, float time) {
		super(x, y);
		this.text = text;
		this.color = color;
		this.time = time;
		
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		time -= dt;
		if (time <= 0) this.onDeath();
	}

	@Override
	public void drawDetails(Graphics2D g2d) {
		Font old = g2d.getFont();
		Font f = new Font(old.getName(), Font.BOLD, 30);
		g2d.setColor(this.color);
		
		g2d.setFont(f);
		g2d.drawString(this.text, 0, 0);
		g2d.setColor(color.BLACK);
		g2d.setFont(old);
	}
}
