package Objects;

import java.awt.Graphics2D;

public class AsteroidManager {

	private Asteroid[] asteroids;
	private AsteroidBoss asteroidBoss;
	private boolean bossMode = false;

	public AsteroidManager() {

		asteroids = new Asteroid[3];
		asteroidBoss = new AsteroidBoss();

		for (int index = 0; index < asteroids.length; index++) {
			asteroids[index] = new Asteroid();
		}
	}

	public void render(Graphics2D g) {

		if (bossMode) {
			asteroidBoss.render(g);

		} else {
			for (int index = 0; index < asteroids.length; index++) {
				asteroids[index].render(g);
			}
		}

	}

	public void update() {

		if (bossMode) {
			asteroidBoss.update();
		} else {
			for (int index = 0; index < asteroids.length; index++) {
				asteroids[index].update();
			}
		}
	}

	public Asteroid[] getAsteroids() {
		return asteroids;
	}

	public AsteroidBoss getAsteroidBoss() {
		return asteroidBoss;
	}

	public void setBossMode(boolean mode) {
		bossMode = mode;
	}

	public boolean getBossMode() {
		// TODO Auto-generated method stub
		return bossMode;
	}

}
