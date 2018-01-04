package daniel.sandley.platform.window;

import daniel.sandley.platform.framework.GameObject;

public class Camera {

	private int x,y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void tick(GameObject player) {
		x = (int) (-player.getX() + Game.WIDTH/2);
		y = (int) (-1 * player.getY() + (Game.HEIGHT/2));
	}
	
	
}
