package Objects;

import java.awt.Color;
import java.awt.Graphics2D;

import Display.Panel;

public class SimpleBullet implements Bullet {

	private int x, y, radius, speed;
	double dx, dy;
	private boolean visible;

	public SimpleBullet() {

		x = 0;
		y = 0;
		radius = 5;
		speed = 2;
		dx = 0;
		dy = 0;
		visible = false;

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if (visible) {
			g.setColor(Color.red);
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

	@Override
	public void setDx(double cos) {
		// TODO Auto-generated method stub
		dx = cos;
	}

	@Override
	public void setDy(double sin) {
		// TODO Auto-generated method stub
		dy = sin;
	}

}
