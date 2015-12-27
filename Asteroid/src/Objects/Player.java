package Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Display.LoadImage;
import Display.Panel;

public class Player {

	private int x, y;
	private BufferedImage shipImg;
	private int cannonX, cannonY;
	private final int speed = 2, width, height;
	private int score = 0;

	// Move flags
	private boolean left, right;

	// Fire short burst of 10 bullets?
	private SimpleBullet[] bullets;

	public Player() {
		// TODO Auto-generated constructor stub

		shipImg = LoadImage
				.loadImage(
						"E:\\Program Files\\Computer Science\\Asteroid\\Asteroid\\src\\ship.png",
						"Ship Image");

		// width = 31;
		// height = 57;
		width = shipImg.getWidth() / 2;
		height = shipImg.getHeight() / 2;

		x = width - 15;
		y = Panel.HEIGHT - height;

		left = false;
		right = false;

		bullets = new SimpleBullet[10];

		// Initialize bullets
		initBullets();

	}

	private void initBullets() {
		for (int index = 0; index < bullets.length; index++)
			bullets[index] = new SimpleBullet();
	}

	private void renderBullets(Graphics2D g) {
		for (int index = 0; index < bullets.length; index++)
			bullets[index].render(g);
	}

	private void updateBullets() {
		for (int index = 0; index < bullets.length; index++)
			bullets[index].update();
	}

	public void draw(Graphics2D g) {

		cannonX = x + width / 2;
		cannonY = y;

		// Draw Ship
		g.drawImage(shipImg, x, y, width, height, null);

		// Draw the bullets
		renderBullets(g);
		// Show bullet count on the screen
		bulletCount(g);
		scoreCount(g);

	}

	private void bulletCount(Graphics2D g2d) {

		Font cFont = g2d.getFont();
		Font newFont = cFont.deriveFont(cFont.getSize() * 2.0f);

		g2d.setFont(newFont);

		g2d.setColor(Color.white);

		String bulletCountString = "Clip: " + availableBullets() + "/"
				+ bullets.length;

		g2d.drawString(bulletCountString, Panel.WIDTH - 200, 20);

	}

	private void scoreCount(Graphics2D g2d) {

		g2d.setColor(Color.white);

		String bulletCountString = "Score: " + score;

		g2d.drawString(bulletCountString, Panel.WIDTH - 200, 40);
	}

	private int availableBullets() {
		// Total available bullets
		int total = 0;

		for (int index = 0; index < bullets.length; index++) {
			// If the bullet is available (not visible) to be used add to the
			// total
			if (!bullets[index].getVisibility()) {
				total += 1;
			}
		}

		return total;
	}

	public void update() {
		move();
		updateBullets();
	}

	/**
	 * Controls player movement
	 */
	private void move() {
		if (x <= 0) {
			x = 1;
		} else if (x >= Panel.WIDTH - this.width) {
			x = Panel.WIDTH - this.width - 1;
		} else {
			if (left) {
				x -= speed;
			}
			if (right) {
				x += speed;
			}
		}
	}

	public void checkAvailableBullets() {

		for (int index = 0; index < bullets.length; index++) {

			// If this bullet is available use it and fire it
			if (!bullets[index].getVisibility()) {

				bullets[index].setX(cannonX);
				bullets[index].setY(cannonY);
				bullets[index].setDx(0);
				bullets[index].setDy(-1);
				bullets[index].setVisibility(true);

				// Allows only one bullet
				return;
			}

		}

	}

	public void updateScore(int points) {

		score += points;

	}

	public void fireBullet() {

		checkAvailableBullets();

	}

	public void moveLeft(boolean move) {
		// TODO Auto-generated method stub
		left = move;

	}

	public void moveRight(boolean move) {
		// TODO Auto-generated method stub
		right = move;

	}

	public Bullet[] getBullets() {
		// TODO Auto-generated method stub

		return bullets;

	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getScore() {
		return score;
	}
}
