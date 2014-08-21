package game;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;

public class Game extends Applet implements Runnable, MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;

	private final String TITLE = "Pattern Matcher";

	public static int frameHeight = 700;
	public static int frameWidth = 420;

	public static int boardHeight = 560;

	public static int numberOfSensors = 12;
	public static int sensorBuffer = 6;

	public static int shapeSpeed = 3;
	public static long shapeRate = 1500;

	Graphics2D g2d;

	private Image image, background;
	private Graphics second;

	private URL base;

	public static ArrayList<Shape> shapes = new ArrayList<Shape>();

	private Sensor sensors[];

	private ShapeThread shapeThread;

	private int mouseStartX;
	private int mouseStartY;
	private int mouseEndY;
	private int mouseEndX;
	private boolean mouseDown;

	private Sensor startSensor;

	private ArrayList<int[]> sensorLines;

	@Override
	public void init() {

		setSize(frameWidth, frameHeight);
		setBackground(Color.BLACK);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle(TITLE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Image Setups
		background = getImage(base, "data/clouds.png");

		// shapes.add(new Shape());

		// line = new Line(2);

		createSensors();
		moveSensors(-4, 5);

		sensorLines = new ArrayList<int[]>();
	}

	@Override
	public void start() {

		Thread thread = new Thread(this);
		thread.start();

		shapeThread = new ShapeThread();
		shapeThread.start();

	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void run() {
		while (true) {

			for (int i = 0; i < shapes.size(); i++) {
				if (shapes.get(i).isVisible()) {
					shapes.get(i).update();
				} else {
					shapes.remove(i);
				}
			}

			checkSensors();

			repaint();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void update(Graphics g) {
		doubleBuffer(g);

	}

	@Override
	public void paint(Graphics g) {

		g.drawImage(background, 0, 0, this);

		g.setColor(new Color(255, 51, 255));
		drawPatterns(g);

		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, boardHeight, frameWidth, frameHeight);

		g.setColor(Color.DARK_GRAY);
		drawSensors(g);

		// mouse test
		if (mouseDown) {
			g.setColor(Color.white);
			g.drawLine(mouseStartX, mouseStartY, mouseEndX, mouseEndY);

			for (int i = 0; i < sensorLines.size(); i++) {
				g.drawLine(sensorLines.get(i)[0], sensorLines.get(i)[1],
						sensorLines.get(i)[2], sensorLines.get(i)[3]);
			}

		}

	}

	private void drawPatterns(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));

		int circWidth = 8;

		for (int i = 0; i < shapes.size(); i++) {
			ArrayList<Line> lines = shapes.get(i).getLines();
			for (int j = 0; j < lines.size(); j++) {
				g2.drawLine(lines.get(j).getStartX(), lines.get(j).getStartY(),
						lines.get(j).getEndX(), lines.get(j).getEndY());
				g2.fillOval(lines.get(j).getStartX() - circWidth / 2, lines
						.get(j).getStartY() - circWidth / 2, circWidth,
						circWidth);
				g2.fillOval(lines.get(j).getEndX() - circWidth / 2, lines
						.get(j).getEndY() - circWidth / 2, circWidth, circWidth);
			}
		}
	}

	private void drawSensors(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));

		for (int i = 0; i < sensors.length; i++) {
			g2.drawOval(sensors[i].getX(), sensors[i].getY(),
					sensors[i].getWidth(), sensors[i].getWidth());
		}
		g2.setStroke(new BasicStroke(2));
	}

	private void doubleBuffer(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	private void createSensors() {
		sensors = new Sensor[numberOfSensors];

		int width = 24;

		sensors[0] = new Sensor(52, 582, width);
		sensors[1] = new Sensor(112, 582, width);
		sensors[2] = new Sensor(172, 582, width);
		sensors[3] = new Sensor(232, 582, width);
		sensors[4] = new Sensor(292, 582, width);
		sensors[5] = new Sensor(352, 582, width);
		sensors[6] = new Sensor(52, 642, width);
		sensors[7] = new Sensor(112, 642, width);
		sensors[8] = new Sensor(172, 642, width);
		sensors[9] = new Sensor(232, 642, width);
		sensors[10] = new Sensor(292, 642, width);
		sensors[11] = new Sensor(352, 642, width);
	}

	private void checkSensors() {
		if (mouseDown) {

			for (int i = 0; i < sensors.length; i++) {
				if (mouseStartX > sensors[i].getX() - sensorBuffer
						&& mouseStartX < (sensors[i].getX() + sensors[i]
								.getWidth() + sensorBuffer)) {
					if (mouseStartY > sensors[i].getY() - sensorBuffer
							&& mouseStartY < (sensors[i].getY() + sensors[i]
									.getWidth() + sensorBuffer)) {
						startSensor = sensors[i];
						System.out.println("mark");
					}

				}
			}

			for (int i = 0; i < sensors.length; i++) {
				if (mouseEndX > sensors[i].getX() - sensorBuffer
						&& mouseEndX < (sensors[i].getX() + sensors[i]
								.getWidth() + sensorBuffer)) {
					if (mouseEndY > sensors[i].getY() - sensorBuffer
							&& mouseEndY < (sensors[i].getY() + sensors[i]
									.getWidth() + sensorBuffer)) {

						int temp[] = {
								startSensor.getX() + startSensor.getWidth() / 2,
								startSensor.getY() + startSensor.getWidth() / 2,
								sensors[i].getX() + sensors[i].getWidth() / 2,
								sensors[i].getY() + sensors[i].getWidth() / 2 };
						
						sensorLines.add(temp);
						mouseStartX = temp[2];
						mouseStartY = temp[3];

						// create player lines here
						// compare
						// and remove from shape

						System.out.println("added");
						// g.drawLine(mouseStartX, mouseStartY,
						// sensors[i].getX() + 12, sensors[i].getY() + 12);
					}

				}
			}
		} else {
			sensorLines.clear();
		}
	}

	private void moveSensors(int xAmount, int yAmount) {
		for (int i = 0; i < sensors.length; i++) {
			sensors[i].setX(sensors[i].getX() + xAmount);
			sensors[i].setY(sensors[i].getY() + yAmount);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseStartX = e.getX();
		mouseStartY = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseDown = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseEndX = e.getX();
		mouseEndY = e.getY();
		mouseDown = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
