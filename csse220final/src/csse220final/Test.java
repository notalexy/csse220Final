package csse220final;
/**
 * Class for testing stuff
 */
public class Test {
	public static void main(String[] args) {
		Vector2D a = new Vector2D(0, 1);
		Vector2D b = new Vector2D(0, -50);
		System.out.println(Vector2D.project(b, a));
		System.out.println(Vector2D.innerProduct(b, a.unit()));
		Vector2D c = new Vector2D(1, 0);
		Vector2D d = new Vector2D(-50, 0);
		System.out.println(Vector2D.project(d, c));
	}
}
