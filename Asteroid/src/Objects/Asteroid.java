package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Display.Panel;

public class Asteroid {

	private int x, y, radius;
	private final int SPEED = 1;
	private Random random;
	private int dx, dy;
	private boolean visible;

	public Asteroid() {

		random = new Random();
		visible = true;
		radius = random.nextInt(61) + 30;

		// if true, asteroid starts at the top, otherwise start at the corners
		// if (random.nextBoolean()) {

		y = random.nextInt(Panel.HEIGHT + 1);
		// Towards playerY
		int directionY = (Panel.HEIGHT / 2) - y;
		dy = (directionY / Math.abs(directionY)) * SPEED;

		// if true x is 0
		if (random.nextBoolean()) {
			x = 0;
			dx = SPEED;
		} else {
			x = Panel.WIDTH - radius;
			dx = -SPEED;
		}

		// }

		/*
		 * else { x = random.nextInt(Panel.WIDTH + 1);
		 * 
		 * int directionX = (Panel.HEIGHT / 2) - x; dx = (directionX /
		 * Math.abs(directionX)) * SPEED;
		 * 
		 * // if true y is 0 if (random.nextBoolean()) { y = 0; dy = SPEED; }
		 * else { y = Panel.HEIGHT - radius; dy = -SPEED; } }
		 */

	}

	public void draw(Graphics2D g) {
		if (visible) {
			g.setColor(Color.red);
			g.fillOval(x, y, radius, radius);
		}
	}

	public void update() {

		if (x >= (Panel.WIDTH / 2) - radius && x <= (Panel.WIDTH / 2) + radius
				&& y >= (Panel.HEIGHT / 2) - radius
				&& y <= (Panel.HEIGHT / 2) - radius)
			visible = false;

		if (visible) {
			/*
			 * Collision
			 */
			if (y >= Panel.HEIGHT - radius) {
				y = Panel.HEIGHT - radius;
				dy *= -1;

			}
			if (y <= 0) {
				y = 0;
				dy *= -1;
			}

			if (x >= Panel.WIDTH - radius) {
				x = Panel.WIDTH - radius;
				dx *= -1;
			}
			if (x <= 0) {
				x = 0;
				dx *= -1;
			}

			x += dx;
			y += dy;
		}

	}
}
