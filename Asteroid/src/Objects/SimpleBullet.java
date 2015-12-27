package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import Display.LoadImage;
import Display.Panel;

public class SimpleBullet implements Bullet {

	private int x, y, radius;
	double dx, dy;
	private boolean visible;
	private BufferedImage bulletImg;

	private Random random;

	public SimpleBullet() {

		x = 0;
		y = 0;
		radius = 12;

		bulletImg = LoadImage
				.loadImage(
						"E:\\Program Files\\Computer Science\\Asteroid\\Asteroid\\src\\bullet1.png",
						"Bullet 1");

		dx = 0;
		dy = 0;
		visible = false;
		random = new Random();

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

		if (visible) {

			// Draw bullet
			g.setColor(Color.YELLOW);
			g.setColor(new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255)));
			// g.fillOval(x, y, radius, radius);
			g.drawImage(bulletImg, x, y, radius, radius, null);
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		x += dx;
		y += dy;

		// Not accurate
		if (x >= Panel.WIDTH || x <= 0 || y >= Panel.HEIGHT || y <= 0)
			visible = false;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void setX(int newX) {
		// TODO Auto-generated method stub
		x = newX;
	}

	@Override
	public void setY(int newY) {
		// TODO Auto-generated method stub
		y = newY;
	}

	@Override
	public void setVisibility(boolean isVisible) {
		// TODO Auto-generated method stub

		visible = isVisible;
	}

	@Override
	public boolean getVisibility() {
		// TODO Auto-generated method stub
		return visible;
	}

	public void setDx(double newDx) {
		// TODO Auto-generated method stub
		dx = newDx;
	}

	@Override
	public void setDy(double newDy) {
		// TODO Auto-generated method stub
		dy = newDy;
	}

	@Override
	public int getRadius() {
		// TODO Auto-generated method stub
		return radius;
	}

}
