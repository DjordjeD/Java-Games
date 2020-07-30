package tanks;

import java.awt.Color;
import java.awt.Graphics;

public class Coin extends Figure {

	public Coin(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Tile t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Figure fig = (Figure) obj;
		return fig.tile.getRow_y() == this.tile.getRow_y() && fig.tile.getColumn_x() == this.tile.getColumn_x();
	}

	@Override
	public synchronized void draw() {
		// TODO Auto-generated method stub
		Graphics g = tile.getGraphics();
		g.setColor(Color.YELLOW);
		g.fillOval(tile.getWidth() / 3, tile.getHeight() / 3, tile.getWidth() / 3, tile.getHeight() / 3);

	}

}
