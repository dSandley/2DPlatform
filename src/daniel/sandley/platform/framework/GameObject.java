package daniel.sandley.platform.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected float velX = 0;
	protected float velY = 0;
	protected ObjectId id;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean leftStandingFinal = false;
	protected boolean rightStandingFinal = true;
	
	
	
	public boolean isLeftStandingFinal() {
		return leftStandingFinal;
	}

	public void setLeftStandingFinal(boolean leftStandingFinal) {
		this.leftStandingFinal = leftStandingFinal;
	}

	public boolean isRightStandingFinal() {
		return rightStandingFinal;
	}

	public void setRightStandingFinal(boolean rightStandingFinal) {
		this.rightStandingFinal = rightStandingFinal;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render (Graphics graphic);
	public abstract Rectangle getBounds();
	
	public  float getX()
	{
		return x;
	}
	public  float getY(){
		return y;
	}
	public  void setX(float x){
		this.x = x;
	}
	public  void setY(float y){
		this.y = y;
	}
	
	public  float getVelX(){
		return velX;
	}
	public  float getVelY(){
		return velY;
	}
	public  void setVelX(float velX){
		this.velX = velX;
	}
	public  void setVelY(float velY){
		this.velY = velY;
	}
	
	public  ObjectId getId() {
		return id;
	}
}
