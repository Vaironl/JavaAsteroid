package Objects;

import java.awt.Graphics2D;

public class AsteroidManager {

	private Asteroid[] asteroids;

	public AsteroidManager() {

		asteroids = new Asteroid[3];

		for (int index = 0; index < asteroids.length; index++) {
			asteroids[index] = new Asteroid();
		}
	}

	public void render(Graphics2D g) {

		for (int index = 0; index < asteroids.length; index++) {
			asteroids[index].render(g);
		}

	}

	public void update() {
		for (int index = 0; index < asteroids.length; index++) {
			asteroids[index].update();
		}
	}

	public Asteroid[] getAsteroids() {
		return asteroids;
	}

}
