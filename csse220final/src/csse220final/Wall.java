package csse220final;

public class Wall extends Collidable {
	
	public Wall(float x, float y, int width, int height) {
		this.movable = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		//walls do not move or rotatae
		this.theta = 0;
		this.thetavel = 0;
		this.xvel = 0;
		this.yvel = 0;
		
	}


	@Override
	public void drawDetails() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void handleCollision() {
		// TODO Auto-generated method stub
		
	}

}
