package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

		generateAsteroid();
	}

	private void generateAsteroid() {
		visible = true;
		radius = random.nextInt(61) + 30;

		y = random.nextInt(Panel.HEIGHT + 1 - radius);
		x = Panel.WIDTH - radius;

		dy = 0;
		dx = -SPEED;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void render(Graphics2D g) {
		if (visible) {
			g.setColor(Color.red);
			g.drawOval(x, y, radius, radius);
		}
	}

	public void update() {

		if (x < 0 - radius) {
			generateAsteroid();
		}

		x += dx;
		y += dy;

	}

	public int getAsteroidRadius() {
		return radius;
	}

	public void setVisibility(boolean visibility) {
		// TODO Auto-generated method stub

		visible = visibility;
		generateAsteroid();

	}

	public boolean getVisibility() {
		// TODO Auto-generated method stub
		return visible;
	}

	// Check collision with player
	public boolean checkPlayerCollission(Player player) {

		// g2d.drawLine(player.getX() + player.getWidth() + 10,
		// player.getY() + 12, player.getX() + player.getWidth() + 10,
		// player.getY() + player.getHeight() - 12);

		// Hardcoded values
		// if (this.getX() > 1
		// && this.getX() <= player.getX() + player.getWidth() + 10) {
		// if (this.getY() > player.getY() + 12
		// && this.getY() < player.getY() + player.getHeight() - 12) {
		//
		// return true;
		//
		// }
		//
		// }

		int playerY = player.getY() + 12;
		int playerWidth = player.getX() + player.getWidth() + 10;
		int playerHeight = player.getY() + player.getHeight() - 12;
s
		Rectangle playerRect = new Rectangle(1, playerY, playerWidth,
				playerHeight);

		Rectangle asteroidRect = new Rectangle(this.getX(), this.getY(),
				this.getAsteroidRadius(), this.getAsteroidRadius());

		return playerRect.intersects(asteroidRect);

	}

}
