package daniel.sandley.platform.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import daniel.sandley.platform.framework.Animation;
import daniel.sandley.platform.framework.GameObject;
import daniel.sandley.platform.framework.ObjectId;
import daniel.sandley.platform.framework.Texture;
import daniel.sandley.platform.window.Game;
import daniel.sandley.platform.window.Handler;

public class Player extends GameObject {

	private float width = 55, height = 96;
	private float gravity = 0.14f;
	private final float MAX_SPEED = 11;
	private Handler handler;
	Texture tex = Game.getInstance();

	private Animation playerWalkLeft, playerWalkRight, playerStandLeft, playerStandRight, playerJumpRight,
			playerJumpLeft;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		playerWalkLeft = new Animation(10, tex.player[0], tex.player[1], tex.player[2], tex.player[3]);
		playerWalkRight = new Animation(10, tex.player[4], tex.player[5], tex.player[6], tex.player[7]);
		playerStandLeft = new Animation(10, tex.player[8], tex.player[9], tex.player[10]);
		playerStandRight = new Animation(15, tex.player[11], tex.player[12], tex.player[13]);
		playerJumpRight = new Animation(10, tex.player[15]);
		playerJumpLeft = new Animation(10, tex.player[14]);
		// TODO Auto-generated constructor stub
	}

	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;

		if (falling || jumping) {
			velY += gravity;

			if (velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		PlayerCollision(object);
		playerWalkLeft.runAnimation();
		playerWalkRight.runAnimation();
		playerStandLeft.runAnimation();
		playerStandRight.runAnimation();
		playerJumpLeft.runAnimation();
		playerJumpRight.runAnimation();
	}

	private void PlayerCollision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block) {

				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;

				}

				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}

				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 33;

				}

				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 60;

				}

				else {
					falling = true;
				}
			}
		}
	}

	public void render(Graphics graphic) {
		// TODO Auto-generated method stub
		graphic.setColor(Color.cyan);
		if (jumping ) {
			if ( velX > 0 || rightStandingFinal) {
				playerJumpRight.drawAnimation(graphic, (int) x, (int) y, 55, 96);
			}
			if ( velX < 0 || leftStandingFinal) {
				playerJumpLeft.drawAnimation(graphic, (int) x, (int) y, 55, 96);
			}
		}

		else {
			if (velX < 0) {
				playerWalkLeft.drawAnimation(graphic, (int) x, (int) y, 55, 96);
			}
			if (velX > 0) {
				playerWalkRight.drawAnimation(graphic, (int) x, (int) y, 55, 96);
			}

			if (rightStandingFinal) {
				playerStandRight.drawAnimation(graphic, (int) x, (int) y, 55, 96);
			}

			if (leftStandingFinal) {
				playerStandLeft.drawAnimation(graphic, (int) x, (int) y, 55, 96);
			}
		}

		Graphics2D collison = (Graphics2D) graphic;
		graphic.setColor(Color.black);

		collison.draw(getBounds());
		collison.draw(getBoundsLeft());
		collison.draw(getBoundsRight());
		collison.draw(getBoundsTop());

	}

	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + (width / 2) - (width / 2 / 2)), (int) y, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + (width / 2) - (width / 2 / 2)), (int) ((int) y + (height / 2)),
				(int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + width), (int) y + 5, (int) 5, (int) height - 10);
	}

}
