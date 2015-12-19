package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import Display.Panel;

public class Player {

	int x, y, gunX, gunY;
	private double angle;
	private final int width = 30, height = 30, gunWidth = 5, gunHeight = 25,
			rotateAngle = 2;
	private int speed = 1;
	private int dx = speed, dy = speed;
	// Move flags
	private boolean forward, left, right;

	// Fire short burst of 10 bullets?
	private SimpleBullet[] bullets;

	public Player() {
		// TODO Auto-generated constructor stub
		x = Panel.WIDTH / 2;
		y = Panel.HEIGHT / 2;
		angle = (Math.PI / 8);
		gunX = x + (width / 2) - gunWidth;
		gunY = y + (height / 2);

		forward = false;
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
		g.rotate(angle, x + (width / 2), y + (height / 2));

		// Player
		g.setColor(Color.white);
		g.fill3DRect(x, y, width, height, true);

		// Player gun, centered on the square
		g.setColor(Color.black);
		g.fillRect(gunX, gunY, gunWidth, gunHeight);

		g.rotate(-angle, x + (width / 2), y + (height / 2));

		// if visible
		renderBullets(g);

		/*
		 * //offsetAngle int offsetAngle = 98; g.setColor(Color.red);
		 * g.fillRect( (int) (gunX + (Math.cos(angle +
		 * Math.toRadians(offsetAngle)) * gunHeight)), (int) (gunY +
		 * (Math.sin(angle + Math.toRadians(offsetAngle)) * gunHeight)), 3, 3);
		 */

	}

	public void update() {

		if (Math.toDegrees(angle) >= 360 || Math.toDegrees(angle) <= -360) {
			angle = 0;
		}

		move();
		updateBullets();

	}

	private void move() {
		//System.out.println("Degrees: " + Math.toDegrees(angle));
		System.out.println("Sin: " +Math.ceil(Math.sin(angle)));
		
		if (forward) {
			
			y += Math.sin(angle);
			gunY += Math.sin(angle);
			x += Math.cos(angle);
			gunX += Math.cos(angle);

		}
		if (left) {
			angle -= Math.toRadians(rotateAngle);

		}
		if (right) {
			angle += Math.toRadians(rotateAngle);

		}

	}

	int offsetAngle = 98;

	public void checkAvailableBullets() {

		for (int index = 0; index < bullets.length; index++) {

			// If this bullet is available use it and fire it
			if (!bullets[index].getVisibility()) {

				int bulletX = (int) (gunX + (Math.cos(angle
						+ Math.toRadians(offsetAngle)) * gunHeight));
				int bulletY = (int) (gunY + (Math.sin(angle
						+ Math.toRadians(offsetAngle)) * gunHeight));

				bullets[index].setX(bulletX);
				bullets[index].setY(bulletY);
				double bulletAngle = (angle);
				bullets[index].setDx(Math.cos(bulletAngle));
				bullets[index].setDy(Math.sin(bulletAngle));
				bullets[index].setVisibility(true);

				return;
			}

		}

	}

	public void fireBullet() {

		checkAvailableBullets();

	}

	public void moveForward(boolean move) {
		// TODO Auto-generated method stub

		forward = move;

	}

	public void moveRight(boolean move) {
		// TODO Auto-generated method stub
		left = move;

	}

	public void moveLeft(boolean move) {
		// TODO Auto-generated method stub
		right = move;

	}
}
