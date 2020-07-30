package paket;

import java.awt.Color;

public abstract class CircleFigure extends Circle {

	Scene scene_figure;
	Vector speed;

	public CircleFigure(Vector center, Color color, int r, Scene s, Vector speed) {
		super(center, color, r);
		this.scene_figure = s;
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}

	public abstract void moveFigure();

	public abstract void crashed();

}
