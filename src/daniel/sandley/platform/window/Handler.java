package daniel.sandley.platform.window;

import java.awt.Graphics;
import java.util.LinkedList;

import daniel.sandley.platform.framework.GameObject;
import daniel.sandley.platform.framework.ObjectId;
import daniel.sandley.platform.objects.Blocks;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);

		}
	}

	public void render(Graphics graphic) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(graphic);

		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}


}
