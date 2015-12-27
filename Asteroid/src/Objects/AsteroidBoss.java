package Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Display.LoadImage;
import Display.Panel;

public class AsteroidBoss {

	private int x, y;
	private final int SPEED = 1;
	private BufferedImage bossImg;
	private int width, height;
	private Random random;
	private int dx, dy;
	private boolean visible;
	private int health = 2000;

	public AsteroidBoss() {
		random = new Random();

		bossImg = LoadImage
				.loadImage(
						"E:\\Program Files\\Computer Science\\Asteroid\\Asteroid\\src\\asteroidBoss.png",
						"Boss Image");

		generateAsteroid();
	}

	private void generateAsteroid() {
		visible = true;

		width = bossImg.getWidth();
		height = bossImg.getHeight();

		y = 0;
		x = random.nextInt(Panel.WIDTH + 1);

		dy = SPEED;

		// Range of dx will be from -3 to 3
		dx = random.nextInt(5) - 2;

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
			g.drawImage(bossImg, x, y, width, height, null);
		}
	}

	public void update() {

		System.out.println("Health: " + health);

		if (visible) {

			if (y > Panel.HEIGHT) {
				y = 0;
			}

			if (x < 0) {
				x = Panel.WIDTH - width;
			}
			if (x > Panel.WIDTH - width) {
				x = 1;
			}

			x += dx;
			y += dy;
		}

	}

	public void setVisibility(boolean visibility) {
		// TODO Auto-generated method stub

		visible = visibility;

	}

	public boolean getVisibility() {
		// TODO Auto-generated method stub
		return visible;
	}

	// Check collision with player
	public boolean checkPlayerCollission(Player player) {

		Rectangle asteroidRect, playerRect;

		asteroidRect = new Rectangle(getX(), getY(), width, height);
		playerRect = new Rectangle(player.getX(), player.getY(),
				player.getWidth(), player.getHeight());

		return asteroidRect.intersects(playerRect);

	}

	// Check bullet collision
	public void checkBulletCollision(Bullet bullet, Player player) {

		Rectangle bulletRect, asteroidRect;

		asteroidRect = new Rectangle(getX(), getY(), height, width);

		bulletRect = new Rectangle(bullet.getX(), bullet.getY(),
				bullet.getRadius(), bullet.getRadius());

		// Bullet hit the asteroid
		if (bulletRect.intersects(asteroidRect)) {

			health -= 5;
			bullet.setVisibility(false);

			player.updateScore(40);
			if (health <= 0) {
				this.setVisibility(false);
			}

		}

	}

}
