package daniel.sandley.platform.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import daniel.sandley.platform.framework.GameObject;
import daniel.sandley.platform.framework.ObjectId;
import daniel.sandley.platform.framework.Texture;
import daniel.sandley.platform.window.Game;

public class Blocks extends GameObject {
	Texture tex = Game.getInstance();
	private int type;

	public Blocks(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub

	}

	public void render(Graphics graphic) {
		// TODO Auto-generated method stub

		if (type == 0)
		 graphic.drawImage(tex.block[0],(int) x,(int) y, null);
		// dirt block
		if (type == 1)
			 graphic.drawImage(tex.block[1],(int) x,(int) y, null);
			// dirt block
		// the number in the array relates to the block type
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
