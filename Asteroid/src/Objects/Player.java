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

import Display.Panel;

public class Player {

	private int x, y;
	private BufferedImage shipImg;
	private int cannonX, cannonY;
	private double angle;
	private final int speed = 2, width, height;
	// Ship Dimensions Width: 62, Height :113. Note the ship is being rotated!!!

	// Move flags
	private boolean up, down;

	// Fire short burst of 10 bullets?
	private SimpleBullet[] bullets;

	public Player() {
		// TODO Auto-generated constructor stub

		shipImg = null;

		try {
			shipImg = ImageIO
					.read(new File(
							"E:\\Program Files\\Computer Science\\Asteroid\\Asteroid\\src\\ship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error opening the ship image...");
			e.printStackTrace();
			System.exit(0);
		}

		width = 31;
		height = 57;

		x = width - 15;
		y = Panel.HEIGHT / 2;

		angle = Math.toRadians(90);

		up = false;
		down = false;

		bullets = new SimpleBullet[10];

		// Initialize bullets
		initBullets();

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

		// Note: Probably using transform incorrectly, read docs carefully!!!!!
		AffineTransform transform = g.getTransform();

		cannonX = x + width / 2;
		cannonY = y + 4;

		// Rotate the ship around the center
		g.rotate(angle, x + (width / 2), y + (height / 2));

		// Draw Ship
		g.drawImage(shipImg, x, y, width, height, null);

		// Draw bounds of the ship
		g.setColor(Color.red);
		g.drawRect(getX(), getY(), getWidth(), getHeight());

		g.setTransform(transform);

		// Draw the bullets
		renderBullets(g);

		bulletCount(g);

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

	private void move() {
		if (y <= 0) {
			y = 1;
		} else if (y >= Panel.HEIGHT - height) {
			y = Panel.HEIGHT - height - 1;
		} else {
			if (up) {
				y -= speed;
			}
			if (down) {
				y += speed;

			}
		}
	}

	public void checkAvailableBullets() {

		for (int index = 0; index < bullets.length; index++) {

			// If this bullet is available use it and fire it
			if (!bullets[index].getVisibility()) {

				bullets[index].setX(cannonX + 30);
				bullets[index].setY(cannonY + 22);
				bullets[index].setDx(1);
				bullets[index].setDy(0);
				bullets[index].setBulletAngle(angle);
				bullets[index].setVisibility(true);

				return;
			}

		}

	}

	public void fireBullet() {

		checkAvailableBullets();

	}

	public void moveUp(boolean move) {
		// TODO Auto-generated method stub
		up = move;

	}

	public void moveDown(boolean move) {
		// TODO Auto-generated method stub
		down = move;

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
}
