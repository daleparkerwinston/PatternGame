package game;

public class Sensor {
	
	private int x, y, width;

	private boolean active;

	public Sensor(int x, int y, int width) {
		this.x = x;
		this.y = y;
		this.width = width;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public boolean isActive() {
		return active;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


}
