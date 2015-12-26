package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Display.Panel;

public class SimpleBullet implements Bullet {

	private int x, y, radius;
	private double bulletAngle;
	double dx, dy;
	private boolean visible;

	public SimpleBullet() {

		x = 0;
		y = 0;
		radius = 5;
		bulletAngle = 0;
		dx = 0;
		dy = 0;
		visible = false;

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if (visible) {

			// Draw bullet
			g.setColor(Color.YELLOW);
			g.fillOval(x, y, radius, radius);

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
	public void setBulletAngle(double angle) {
		// TODO Auto-generated method stub
		bulletAngle = angle;
	}

}
