package game;

public class LineComparer {

	public LineComparer() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean compare(Line l, PlayerLine p) {
		if(l.getType() == p.getType() && l.getStartingLocation() == p.getLocation()) {
			return true;
		} else {
			return false;
		}
	}

}
