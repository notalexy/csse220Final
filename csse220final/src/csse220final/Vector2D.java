package csse220final;
/**
 * Vector helper class for performing linear algebra
 */
public class Vector2D {
	private float x;
	private float y;
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() { return this.x;}
	public float getY() { return this.y;}
	
	//arithmatic operations
	public Vector2D add(Vector2D other) {
		return new Vector2D(this.x + other.getX(), this.y + other.getY());
	}
	
	public Vector2D subtract(Vector2D other) {
		return new Vector2D(this.x - other.getX(), this.y - other.getY());
	}
	
	public Vector2D scalarMultiply(float scalar) {
		return new Vector2D(this.x * scalar, this.y * scalar);
	}
	
	//vector operations
	
	public static float innerProduct(Vector2D one, Vector2D two) {
		return one.getX() * two.getX() + two.getY()*two.getY();
	}
	
	public static float crossProduct(Vector2D one, Vector2D two) {
		return one.getX()*two.getY() - one.getY()*two.getX();
	}
	
	public static Vector2D project(Vector2D one, Vector2D two) {
		return two.unit().scalarMultiply(Vector2D.innerProduct(one, two.unit()));
	}
	
	//other properties
	
	public Vector2D unit() {
		return new Vector2D((float)(this.x / Math.sqrt(this.x*this.x + this.y*this.y)), (float)(this.y / Math.sqrt(this.x*this.x + this.y*this.y)));
	}
	
	public float Magnitude() {
		return (float)Math.sqrt((this.x*this.x + this.y*this.y));
	}
	
	
}
