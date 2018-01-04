package daniel.sandley.platform.framework;

import java.awt.image.BufferedImage;

import daniel.sandley.platform.window.BufferedImageLoader;

public class Texture {

	private SpriteSheet ps, bs;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	public BufferedImage[] player = new BufferedImage[21];
	public BufferedImage[] block = new BufferedImage[2];
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {

			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet3.png");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32);
		block[1] = bs.grabImage(2, 1, 32, 32);
		//change to array lists?
		//walking left
		player[0] = ps.grabImage(5, 3, 43, 68);
		player[1] = ps.grabImage(6, 3, 43, 68);
		player[2] = ps.grabImage(7, 3, 43, 68);
		player[3] = ps.grabImage(8, 3, 43, 68);
		//walking right
		player[4] = ps.grabImage(1, 3, 43, 68);
		player[5] = ps.grabImage(2, 3, 43, 68);
		player[6] = ps.grabImage(3, 3, 43, 68);
		player[7] = ps.grabImage(4, 3, 43, 68);
		//standing left
		player[8] = ps.grabImage(4, 2, 43, 68);
		player[9] = ps.grabImage(5, 2, 43, 68);
		player[10] = ps.grabImage(6, 2, 43, 68);
		//standing right
		player[11] = ps.grabImage(1, 2, 43, 68);
		player[12] = ps.grabImage(2, 2, 43, 68);
		player[13] = ps.grabImage(3, 2, 43, 68);
		//jumping left
		player[14] = ps.grabImage(2, 4, 43, 68);
		//jumping right
		player[15] = ps.grabImage(1, 4, 43, 68);
	}
	
	
	
	
}