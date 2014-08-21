package game;

public class PlayerLine {
	
	private int type;
	private int location;

	public PlayerLine(int type, int location) {
		this.type = type;
		this.location = location;
	}

	public int getType() {
		return type;
	}

	public int getLocation() {
		return location;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setLocation(int location) {
		this.location = location;
	}

}
