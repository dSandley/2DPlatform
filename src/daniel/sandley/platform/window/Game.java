package daniel.sandley.platform.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import daniel.sandley.platform.framework.Controls;
import daniel.sandley.platform.framework.ObjectId;
import daniel.sandley.platform.framework.Texture;
import daniel.sandley.platform.objects.Blocks;
import daniel.sandley.platform.objects.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -109931239170637286L;

	private boolean running = false;
	private Thread thread;
	private BufferedImage level = null;

	public static int WIDTH, HEIGHT;

	// Object
	Handler handler;
	Camera camera;
	static Texture tex;

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/Level.png"); // loads level

		camera = new Camera(0, 0);

		handler = new Handler();
		// handler.addObject(new Player(100, 100, handler, ObjectId.Player));
		// handler.createLevel();
		loadImageLevel(level);
		this.addKeyListener(new Controls(handler));
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public synchronized void stop() {

	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + "Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player)
				camera.tick(handler.object.get(i));
		}

	}

	private void render() {
		BufferStrategy bufferStrat = this.getBufferStrategy();
		if (bufferStrat == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics graphics = bufferStrat.getDrawGraphics();
		Graphics2D graphics2D = (Graphics2D) graphics;
		// start drawing
		 graphics.setColor(Color.red);
		 graphics.fillRect(0, 0, getWidth(), getHeight());
		// camera following
		graphics2D.translate(camera.getX(), camera.getY());
		handler.render(graphics);
		graphics2D.translate(-camera.getX(), camera.getY());
		// end of following

		// end drawing
		graphics.dispose();
		bufferStrat.show();

	}

	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		System.out.println("Width:" + w);

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff; 
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue ==255)handler.addObject(new Blocks(xx * 32, yy*32, 1 ,ObjectId.Block));
				if(red == 255 && green == 255 && blue ==0)handler.addObject(new Blocks(xx * 32, yy*32, 0 ,ObjectId.Block));
				if(red == 0 && green == 0 && blue ==255)handler.addObject(new Player(xx * 32, yy*32, handler ,ObjectId.Player));
			}
		}

	}
	
	public static Texture getInstance() {
		return tex;
	}

	public static void main(String args[]) {
		new Window(800, 600, "Neon Platform", new Game());
	}

}
