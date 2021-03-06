package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Display.LoadImage;
import Display.Panel;

public class Asteroid {

	private int x, y;
	private final int SPEED = 1;
	private BufferedImage asteroidImg;
	private int width, height;
	private Random random;
	private int dx, dy;
	private boolean visible;
	private int choice = 0;

	public Asteroid() {
		random = new Random();

		asteroidImg = LoadImage
				.loadImage(
						"E:\\Program Files\\Computer Science\\Asteroid\\Asteroid\\src\\asteroid1.png",
						"Asteroid Image");

		generateAsteroid();
	}

	private void generateAsteroid() {
		visible = true;
		choice = random.nextInt(4);

		width = asteroidImg.getWidth();
		height = asteroidImg.getHeight();

		switch (choice) {

		case 0:
			width /= 1;
			height /= 1;
			break;
		case 1:
			width /= 2;
			height /= 2;
			break;
		case 2:
			width /= 3;
			height /= 3;
			break;
		case 3:
			width /= 4;
			height /= 4;
			break;
		default:
			width /= 6;
			height /= 6;
		}

		y = 0;
		x = random.nextInt(Panel.WIDTH + 1);

		dy = SPEED;

		// Range of dx will be from -3 to 3
		dx = random.nextInt(3) - 1;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void render(Graphics2D g) {
		if (visible) {
			// Draw Ship
			g.drawImage(asteroidImg, x, y, width, height, null);
		}
	}

	public void update() {

		if (y > Panel.HEIGHT) {
			generateAsteroid();
		}
		
		if(x < 0)
		{
			x = Panel.WIDTH - width;
		}
		if(x > Panel.WIDTH - width)
		{
			x = 1;
		}

		x += dx;
		y += dy;

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

		Rectangle asteroidRect, playerRect;

		asteroidRect = new Rectangle(getX(), getY(), height, width);
		playerRect = new Rectangle(player.getX(), player.getY(),
				player.getWidth(), player.getHeight());
		

		return asteroidRect.intersects(playerRect);

	}

	// Check bullet collision
	public void checkBulletCollision(Bullet bullet, Player player) {

		Rectangle bulletRect, asteroidRect;

		asteroidRect = new Rectangle(getX(), getY(), width, height);

		bulletRect = new Rectangle(bullet.getX(), bullet.getY(),
				bullet.getRadius(), bullet.getRadius());

		// Bullet hit the asteroid
		if (bulletRect.intersects(asteroidRect)) {
			bullet.setVisibility(false);
			this.setVisibility(false);
			player.updateScore(5);
		}

	}

}
