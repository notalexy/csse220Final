package csse220final;

public class SwordEnemyBehavior extends EnemyBehavior {
	float kP = 2; //essentially a phase plane kP gain
	public SwordEnemyBehavior(SwordEnemy owner) {
		super(owner);
	}
	@Override
	public void update(float dt) {
		float playerX = EntityManager.getInstance().getPlayer().getX();
		float playerY = EntityManager.getInstance().getPlayer().getY();
		
		Vector2D direction = new Vector2D(playerX - owner.getX(), playerY - owner.getY()).scalarMultiply(kP);
		
		owner.requestVelocity(direction);
		owner.requestPointTo(new Vector2D(playerX, playerY));
		owner.attack();
		
		}
}
