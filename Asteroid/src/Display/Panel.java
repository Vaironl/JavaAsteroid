package Display;

import java.awt.Dimension;
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
import Objects.Player;

public class Panel extends JPanel implements ActionListener, KeyListener {

	private Timer timer;
	private Player player;
	private Asteroid asteroid;
	private Dimension dimensions;
	public static final int WIDTH = 640, HEIGHT = 480;

	public void initializeValues() {

		dimensions = new Dimension(WIDTH, HEIGHT);

		setPreferredSize(dimensions);
		// Pass dimensions rather than widht and height
		player = new Player();

		asteroid = new Asteroid();

		timer = new Timer(5, this);
		timer.start();

		// Detect Keyboard values
		setFocusable(true);
		addKeyListener(this);

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

		player.draw(g2d);
		asteroid.draw(g2d);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		player.update();
		asteroid.update();

		// Repaint at the end
		repaint();
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
			player.moveForward(true);

		}
		if (key == KeyEvent.VK_A) {
			player.moveRight(true);

		}
		if (key == KeyEvent.VK_D) {
			player.moveLeft(true);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			// Forward
			player.moveForward(false);

		}
		if (key == KeyEvent.VK_A) {
			player.moveRight(false);

		}
		if (key == KeyEvent.VK_D) {
			player.moveLeft(false);
		}

	}

}
