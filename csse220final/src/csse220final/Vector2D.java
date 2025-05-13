package csse220final;
/**
 * Vector helper class for performing linear algebra
 * @author Alex Y
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
	/**
	 * Adds one vector to another
	 * @param The vector to add to this vector
	 * @return The vector sum
	 */
	public Vector2D add(Vector2D other) {
		return new Vector2D(this.x + other.getX(), this.y + other.getY());
	}
	/**
	 * Subtracts one vector to another
	 * @param The vector to add to this vector
	 * @return The vector sum
	 */
	
	public Vector2D subtract(Vector2D other) {
		return new Vector2D(this.x - other.getX(), this.y - other.getY());
	}
	/**
	 * Scalar Multiplies one vector by another
	 * @param The scalar to multiply by
	 * @return The scalar product
	 */
	
	public Vector2D scalarMultiply(float scalar) {
		return new Vector2D(this.x * scalar, this.y * scalar);
	}
	
	//vector operations
	/**
	 * Performs an inner product between two vectors
	 * @param one
	 * @param two
	 * @return The inner product
	 */
	public static float innerProduct(Vector2D one, Vector2D two) {
		return one.getX() * two.getX() + one.getY()*two.getY();
	}
	/**
	 * Performs the cross product between vector 1 and 2
	 * @param one
	 * @param two
	 * @return The cross product
	 */
	public static float crossProduct(Vector2D one, Vector2D two) {
		return one.getX()*two.getY() - one.getY()*two.getX();
	}
	/**
	 * Project one onto two
	 */
	public static Vector2D project(Vector2D one, Vector2D two) {
		return two.unit().scalarMultiply(Vector2D.innerProduct(one, two.unit()));
	}
	
	//other properties
	
	public Vector2D unit() {
		//avoid divide by zero
		if (this.x == 0 && this.y == 0) {
			return new Vector2D(0, 0);
		}
		return new Vector2D((float)(this.x / Math.sqrt(this.x*this.x + this.y*this.y)), (float)(this.y / Math.sqrt(this.x*this.x + this.y*this.y)));
	}
	
	public float Magnitude() {
		return (float)Math.sqrt((this.x*this.x + this.y*this.y));
	}
	
	public String toString() {
		return ("X: " + this.x + " Y: " + this.y);
	}
	
}
