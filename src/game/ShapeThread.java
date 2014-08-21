package game;

public class ShapeThread extends Thread{

	public void run() {
		while(true) {
			Game.shapes.add(new Shape());
			
			try {
				ShapeThread.sleep(Game.shapeRate);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
