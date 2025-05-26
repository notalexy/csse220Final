package csse220final;
/**
 * Gun enemy behavior. The gun enemy tries to keep a fixed distance around the player and will then circle them constantly
 */
public class GunEnemyBehavior extends EnemyBehavior {
	float kP = 40; //essentially a phase plane kP gain
	
	float thetaTarget = 0;
	float targetDistance = 300;
		
	public GunEnemyBehavior(GunEnemy owner) {
		super(owner);
		
		//seed the target angle
		float playerX = EntityManager.getInstance().getPlayer().getX();
		float playerY = EntityManager.getInstance().getPlayer().getY();
		thetaTarget = (float) Math.atan2(owner.getY() - playerY, owner.getX() - playerX);
	}
	
	/**
	 * Actually try to stay away from the player and go there
	 */
	@Override
	public void update(float dt) {
		float playerX = EntityManager.getInstance().getPlayer().getX();
		float playerY = EntityManager.getInstance().getPlayer().getY();
		
		Vector2D playerVector = new Vector2D(playerX, playerY);
		thetaTarget +=  ((GunEnemy) this.owner).getAngVelTarget()*dt;
		
		Vector2D targetPosition = new Vector2D(1, 0).scalarMultiply(this.targetDistance).rotate(thetaTarget).add(playerVector);
		
		
		//try to keep a certain distance from the player
		Vector2D direction = new Vector2D(targetPosition.getX() - owner.getX(), targetPosition.getY() - owner.getY());
		
		owner.requestVelocity(direction);
		owner.requestPointTo(new Vector2D(playerX, playerY));
		owner.attack();
		
		}
}
