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
import java.security.Key;

import javax.net.ssl.KeyManager;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objects.Asteroid;
import Objects.AsteroidManager;
import Objects.Bullet;
import Objects.Player;

public class Panel extends JPanel implements KeyListener, Runnable {

	private Player player;
	private AsteroidManager asteroidManager;
	private Dimension dimensions;
	private boolean dead = false;
	public static final int WIDTH = 640, HEIGHT = 480;
	private final int DELAY = 6;
	private Thread animator;

	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();

		animator = new Thread(this);
		animator.start();
	}

	private void cycle() {
		if (!dead) {
			player.update();
			asteroidManager.update();

			Bullet[] bullets = player.getBullets();
			Asteroid[] asteroids = asteroidManager.getAsteroids();

			// Collision check... HORRIBLE CODE
			for (int bulletIndex = 0; bulletIndex < bullets.length; bulletIndex++) {

				for (int asteroidIndex = 0; asteroidIndex < asteroids.length; asteroidIndex++) {

					// Check for collision with asteroids
					if (asteroids[asteroidIndex].checkPlayerCollission(player)) {
						// dead = true;
						animator.stop();
					}

					if (bullets[bulletIndex].getVisibility()
							&& asteroids[asteroidIndex].getVisibility()) {

						if (bullets[bulletIndex].getX() >= asteroids[asteroidIndex]
								.getX()
								&& bullets[bulletIndex].getX() <= asteroids[asteroidIndex]
										.getX()
										+ asteroids[asteroidIndex]
												.getAsteroidRadius()) {
							if (bullets[bulletIndex].getY() >= asteroids[asteroidIndex]
									.getY()
									&& bullets[bulletIndex].getY() <= asteroids[asteroidIndex]
											.getY()
											+ asteroids[asteroidIndex]
													.getAsteroidRadius()) {

								bullets[bulletIndex].setVisibility(false);
								asteroids[asteroidIndex].setVisibility(false);
							}

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

		asteroidManager = new AsteroidManager();

		// Detect Keyboard values
		setFocusable(true);
		addKeyListener(this);

		this.setBackground(Color.black);

	}

	@Override
	public Dimension getPreferredSize() {
		return dimensions;
	}

	public Panel() {

		initializeValues();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		if (dead) {
			g2d.setColor(Color.red);
			g2d.setFont(new Font("Serif", Font.BOLD, 56));
			g2d.drawString("You died!", WIDTH / 2 - 100, HEIGHT / 2);
		}

		if (!dead) {
			player.draw(g2d);
			asteroidManager.render(g2d);

			// g2d.setColor(Color.yellow);
			// g2d.drawLine(player.getX() + player.getWidth() + 10,
			// player.getY() + 12, player.getX() + player.getWidth() + 10,
			// player.getY() + player.getHeight() - 12);
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
			player.fireBullet();
		}

		if (key == KeyEvent.VK_W) {
			// Forward
			player.moveUp(true);

		}
		if (key == KeyEvent.VK_S) {
			player.moveDown(true);

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			// Forward
			player.moveUp(false);

		}
		if (key == KeyEvent.VK_S) {
			player.moveDown(false);

		}
	}

}
