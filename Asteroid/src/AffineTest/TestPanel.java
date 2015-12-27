package AffineTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;

import javax.imageio.ImageIO;
import javax.net.ssl.KeyManager;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objects.Asteroid;
import Objects.AsteroidManager;
import Objects.Bullet;
import Objects.Player;

public class TestPanel extends JPanel implements KeyListener, Runnable {

	private Dimension dimensions;
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

		updateShape();
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
		// Detect Keyboard values
		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public Dimension getPreferredSize() {
		return dimensions;
	}

	public TestPanel() {
		initializeValues();

	}

	int x = 300, y = 250;
	int x2 = 350, y2 = 250;
	Rectangle rect = new Rectangle(x, y, 100, 40);
	Rectangle rect2 = new Rectangle(x2, y2, 100, 40);
	int angle = 90;

	Rectangle2D rect2d;

	private void updateShape() {

		// angle++;

		AffineTransform rect1Af = new AffineTransform();

		rect1Af.rotate(angle, x, y);

		AffineTransform rect2Af = new AffineTransform();

		rect2Af.rotate(0, x2, y2);
		

		// if (rect1Af.int) {
		// System.out.println("Intersected");
		// }

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform af = g2d.getTransform();

		g2d.rotate(Math.toRadians(angle), x, y);

		g2d.draw(rect);

		g2d.setTransform(af);

		g2d.draw(rect2);

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

		if (key == KeyEvent.VK_RIGHT) {
			x++;
		}

		if (key == KeyEvent.VK_LEFT) {
			x--;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
