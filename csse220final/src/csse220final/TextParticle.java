package csse220final;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextParticle extends Entity {
	private String text;
	private float time = 0.5f;
	private Color color;
	public TextParticle(String text, float x, float y, Color color) {
		super(x, y);
		this.text = text;
		this.color = color;
		
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
