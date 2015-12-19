package Objects;

import java.awt.Graphics2D;

public interface Bullet {

	public void setX(int newX);

	public void setY(int newY);

	public void render(Graphics2D g);

	public void update();

	public void setVisibility(boolean isVisible);

	public boolean getVisibility();

	public void setDx(double cos);

	public void setDy(double sin);

}
