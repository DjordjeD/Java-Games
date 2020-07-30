package paket;

import java.awt.Color;

public class Baloon extends CircleFigure {

	public Baloon(Vector center, Color color, int r, Scene s) {
		super(center, color, r, s, new Vector(0, 10));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moveFigure() {
		// TODO Auto-generated method stub
		this.getCenter().add_Vector(this.speed);

	}

	@Override
	public void crashed() {
		// TODO Auto-generated method stub

	}

}
