package paket;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {

	protected Vector center;
	protected Color color;
	int r;

	public Circle(Vector center, Color color, int r) {
		super();
		this.center = center;
		this.color = color;
		this.r = r;
	}

	public static boolean intersect(Circle c1, Circle c2) {

		int x1 = c1.getCenter().getX();
		int x2 = c2.getCenter().getX();
		int y1 = c1.getCenter().getY();
		int y2 = c2.getCenter().getY();
		int r1 = c1.getR();
		int r2 = c2.getR();
		int distSq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
		int radSumSq = (r1 + r2) * (r1 + r2);

		if (distSq <= radSumSq)
			return true;
		else
			return false;

	}

	public boolean intersect(Circle c1) {
		return intersect(c1, this);
	}

	public void draw(Scene s) {
		Graphics graphics = s.getGraphics();
		graphics.setColor(color);
		graphics.fillOval((int) (center.getX() - r), (int) (center.getY() - r), (int) (2 * getR()), (int) (2 * getR()));

	}

	public Vector getCenter() {
		return center;
	}

	public Color getColor() {
		return color;
	}

	public int getR() {
		return r;
	}

}
