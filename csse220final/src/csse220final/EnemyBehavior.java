package csse220final;

public abstract class EnemyBehavior {
	protected Enemy owner;
	
	public EnemyBehavior(Enemy owner) {
		this.owner = owner;
	}
	
	public abstract void update(float dt);
}
