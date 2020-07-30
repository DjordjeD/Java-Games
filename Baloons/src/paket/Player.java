package paket;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends CircleFigure {

	public Player(Vector center, Color color, int r, Scene s) {
		super(center, color, r, s, new Vector(0, 0));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moveFigure() {
		// TODO Auto-generated method stub

	}

	public synchronized void movePlayer(int x) {
		this.getCenter().add_Vector(new Vector(x, 0));
	}

	public void draw(Scene s) {
		Graphics graphics = s.getGraphics();
		graphics.setColor(color);
		graphics.fillOval((int) (center.getX() - r), (int) (center.getY() - r), (int) (2 * getR()), (int) (2 * getR()));
		graphics.setColor(Color.BLUE);
		graphics.fillOval((int) (center.getX() - r / 2), (int) (center.getY() - r / 2), (int) (getR()), (int) (getR()));

	}

	@Override
	public void crashed() {
		// TODO Auto-generated method stub
		// interrupt thread
		// zovi funkciju stani
		this.scene_figure.end_game();

	}

}
