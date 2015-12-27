package Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.net.ssl.KeyManager;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objects.Asteroid;
import Objects.AsteroidBoss;
import Objects.AsteroidManager;
import Objects.Bullet;
import Objects.Player;
import Objects.SimpleBullet;

public class Panel extends JPanel implements KeyListener, Runnable {

	private Player player;
	private BufferedImage backgroundImage;
	private AsteroidManager asteroidManager;
	private Dimension dimensions;
	private boolean dead = false;
	public static final int WIDTH = 640, HEIGHT = 480;
	private final int DELAY = 6;
	private Thread animator;
	private Random random;

	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();

		animator = new Thread(this);
		animator.start();
	}

	private void cycle() {

		// updateBackground();

		// Spawn boss
		if (player.getScore() >= 20) {

			asteroidManager.setBossMode(true);

		}

		if (dead) {
			asteroidManager.update();
		}

		if (!dead) {
			player.update();
			asteroidManager.update();

			Bullet[] bullets = player.getBullets();
			Asteroid[] asteroids = asteroidManager.getAsteroids();
			AsteroidBoss boss = asteroidManager.getAsteroidBoss();

			for (int bulletIndex = 0; bulletIndex < bullets.length; bulletIndex++) {

				if (asteroidManager.getBossMode()) {

					boss.checkBulletCollision(bullets[bulletIndex], player);
					if (boss.checkPlayerCollission(player)) {
						dead = true;
					}

				} else {

					for (int asteroidIndex = 0; asteroidIndex < asteroids.length; asteroidIndex++) {

						asteroids[asteroidIndex].checkBulletCollision(
								bullets[bulletIndex], player);

						// Also check for collision with the player
						if (asteroids[asteroidIndex]
								.checkPlayerCollission(player)) {
							dead = true;
						}

					}
				}
			}
		}

		// Repaint at the end
		repaint();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {

			cycle();
			repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0) {
				sleep = 2;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("Interrupted: " + e.getMessage());
			}

			beforeTime = System.currentTimeMillis();
		}
	}

	public void initializeValues() {

		dimensions = new Dimension(WIDTH, HEIGHT);

		setPreferredSize(dimensions);
		// Pass dimensions rather than width and height
		player = new Player();

		random = new Random();

		asteroidManager = new AsteroidManager();

		// Detect Keyboard values
		setFocusable(true);
		addKeyListener(this);

		this.setBackground(Color.black);

		backgroundImage = LoadImage
				.loadImage(
						"E:\\Program Files\\Computer Science\\Asteroid\\Asteroid\\src\\background.png",
						"Background Image");

		bulletRack = LoadImage
				.loadImage(
						"E:\\Program Files\\Computer Science\\Asteroid\\Asteroid\\src\\GunRack.png",
						"Bullet Rack");

	}

	@Override
	public Dimension getPreferredSize() {
		return dimensions;
	}

	public Panel() {

		initializeValues();

	}

	// Background scrolling
	private int scrollingY = -HEIGHT;

	private void drawBackground(Graphics2D g) {

		// g.drawImage(backgroundImage, 0, scrollingY, null);

	}

	private void updateBackground() {
		if (scrollingY >= 0) {
			scrollingY = -HEIGHT;
		}
		scrollingY++;
	}

	// Bullet rack

	BufferedImage bulletRack;

	private void bulletSelection(Graphics2D g) {

		g.setColor(Color.white);

		BufferedImage bullet1 = LoadImage
				.loadImage(
						"E:\\Program Files\\Computer Science\\Asteroid\\Asteroid\\src\\bullet1.png",
						"Bullet Image");

		// 3 Rectangles
		for (int i = 0; i < 3; i++) {
			g.drawRect(2, 2 + (i * 30), 40, 30);
		}
		g.drawImage(bullet1, 8, 2 + (0 * 30) + 4, null);
		
		g.setColor(Color.red);
		g.drawRect(2, 2 + (0 * 30), 40, 30);

		// g.drawImage(bulletRack, 0, 0, null);

	}

	private void optionScreen(Graphics2D g) {

		switch (random.nextInt(2)) {
		case 0:
			g.setColor(Color.white);
			break;
		case 1:
			g.setColor(Color.gray);
			break;
		default:
			break;

		}

		g.drawString("Press SPACE to restart!!", WIDTH / 2 - 300, 350);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		drawBackground(g2d);

		bulletSelection(g2d);

		asteroidManager.render(g2d);

		if (dead) {
			g2d.setColor(Color.red);
			g2d.setFont(new Font("Serif", Font.BOLD, 56));
			g2d.drawString("You died!", WIDTH / 2 - 100, HEIGHT / 2);

			// Options
			optionScreen(g2d);
		}

		if (!dead) {
			player.draw(g2d);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

		if (key == KeyEvent.VK_SPACE) {
			if (dead) {
				dead = false;
			}
			player.fireBullet();
		}

		if (key == KeyEvent.VK_A) {
			// Forward
			player.moveLeft(true);

		}
		if (key == KeyEvent.VK_D) {
			player.moveRight(true);

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			// Forward
			player.moveLeft(false);

		}
		if (key == KeyEvent.VK_D) {
			player.moveRight(false);
		}
	}

}
