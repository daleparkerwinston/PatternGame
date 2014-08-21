package game;

public class Line {

	private static final int LINE_LENGTH = 60;
	

	private static int[][][] lineTypes = { { { 0, 0 }, { 0, 1 } },
			{ { 0, 0 }, { 0, 1 } }, { { 0, 1 }, { 1, 1 } },
			{ { 1, 0 }, { 1, 1 } }, { { 0, 0 }, { 1, 1 } },
			{ { 1, 0 }, { 0, 1 } } };
	

	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
	private int ySpeed;

	private int type;
	private int startingLocation;

	public Line(int type, int startingLocation, int ySpeed) {
		this.type = type;
		this.ySpeed = ySpeed;
		this.startingLocation = startingLocation;

		startX = lineTypes[type][0][0] * LINE_LENGTH + Shape.startingLocations[startingLocation];
		endX = lineTypes[type][1][0] * LINE_LENGTH + Shape.startingLocations[startingLocation];
		startY = lineTypes[type][0][1] * LINE_LENGTH - (LINE_LENGTH * 2);
		endY = lineTypes[type][1][1] * LINE_LENGTH - (LINE_LENGTH * 2);


	}

	public int getType() {
		return type;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public void update() {
		startY += ySpeed;
		endY += ySpeed;
	}
	

	public int getStartingLocation() {
		return startingLocation;
	}

	public void setStartingLocation(int startingLocation) {
		this.startingLocation = startingLocation;
	}

}
