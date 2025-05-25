package csse220final;

public class GunEnemyBehavior extends EnemyBehavior {
	float kP = 40; //essentially a phase plane kP gain
	
	float thetaTarget = 0;
	float targetDistance = 300;
	
	private GunEnemy owner;
	
	public GunEnemyBehavior(GunEnemy owner) {
		super(owner);
		this.owner = owner;
		float playerX = EntityManager.getInstance().getPlayer().getX();
		float playerY = EntityManager.getInstance().getPlayer().getY();
		thetaTarget = (float) Math.atan2(owner.getY() - playerY, owner.getX() - playerX);
	}
	
	@Override
	public void update(float dt) {
		float playerX = EntityManager.getInstance().getPlayer().getX();
		float playerY = EntityManager.getInstance().getPlayer().getY();
		
		Vector2D playerVector = new Vector2D(playerX, playerY);
		thetaTarget += this.owner.getAngVelTarget()*dt;
		
		Vector2D targetPosition = new Vector2D(1, 0).scalarMultiply(this.targetDistance).rotate(thetaTarget).add(playerVector);
		
		
		//try to keep a certain distance from the player
		Vector2D direction = new Vector2D(targetPosition.getX() - owner.getX(), targetPosition.getY() - owner.getY());
		
		owner.requestVelocity(direction);
		owner.requestPointTo(new Vector2D(playerX, playerY));
		owner.attack();
		
		}
}
