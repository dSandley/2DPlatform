package daniel.sandley.platform.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import daniel.sandley.platform.window.Handler;

public class Controls extends KeyAdapter {

	Handler handler;

	public Controls(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(5);
					tempObject.setLeftStandingFinal(false);
					tempObject.setRightStandingFinal(false);
				}
				if (key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-5);
					tempObject.setLeftStandingFinal(false);
					tempObject.setRightStandingFinal(false);
				}
				if (key == KeyEvent.VK_UP && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-7);
				}
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(0);
					tempObject.setLeftStandingFinal(false);
					tempObject.setRightStandingFinal(true);
				}
				if (key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(0);
					tempObject.setLeftStandingFinal(true);
					tempObject.setRightStandingFinal(false);
				}
				// if(key == KeyEvent.VK_UP) tempObject.setFalling(true);
			}
		}

	}

}
