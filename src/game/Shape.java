package game;

import java.util.ArrayList;
import java.util.Random;

public class Shape {
	
	public static final int[] startingLocations = {60, 180, 300};

	private Random rand;
	
	private int nLines;
	private int startX;
	
	private ArrayList<Line> lines;

	public Shape() {
		lines = new ArrayList<Line>();
		rand = new Random();
		nLines = rand.nextInt(3) + 1;
		
		startX = rand.nextInt(startingLocations.length);
		
		for(int i= 0; i < nLines; i++) {
			lines.add(new Line(rand.nextInt(6), startX, Game.shapeSpeed));
		}
		
	}
	
	public void update() {
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).update();
		}
	}
	
	
	public void test() {
		
	}
	
	public boolean isVisible() {
		
		int max = Math.max(lines.get(0).getStartY(), lines.get(0).getEndY());
		for(int i = 1; i < lines.size(); i++) {
			int temp = Math.max(lines.get(i).getStartY(),lines.get(i).getEndY());
			if(temp < max) {
				max = temp;
			}
		}
		return max < Game.boardHeight;
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}

}
