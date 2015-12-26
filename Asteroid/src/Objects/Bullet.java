package Objects;

import java.awt.Graphics2D;

public interface Bullet {

	public void setX(int newX);

	public void setY(int newY);

	public int getX();

	public int getY();

	public void render(Graphics2D g);

	public void update();

	public void setVisibility(boolean isVisible);

	public boolean getVisibility();

	public void setDx(double cos);

	public void setDy(double sin);

	public void setBulletAngle(double angle);

}
