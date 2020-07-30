package tanks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Tank extends Figure implements Runnable {

	private Thread tank_thr;

	public Tank(Tile tile) {
		super(tile);

		startTank();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.interrupted()) {
			try {
				move(tile);
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO: handle exception
				tank_thr.interrupt();
			}
		}

	}

	public void startTank() {
		tank_thr = new Thread(this);
		tank_thr.start();
	}

	public void stopTank() {
		tank_thr.interrupt();
	}

	@Override
	public synchronized void move(Tile t) {
		// TODO Auto-generated method stub
		int temp = 1;
		while (temp != 0) {
			Random rand = new Random();
			int randint = rand.nextInt(4) + 1;

			switch (randint) {
			case 1: { // gore
				Tile t1 = t.tileMovedBy(-1, 0);
				if (t1 != null)
					if (!t1.isWall()) {
						this.tile = t1;
						// this.tile.tankTile=this;
						temp--;

					}
				break;
			}
			case 2: { // dole
				Tile t1 = t.tileMovedBy(1, 0);
				if (t1 != null)
					if (!t1.isWall()) {
						this.tile = t1;
						// this.tile.tankTile=this;
						temp--;

					}
				break;
			}
			case 3: { // left
				Tile t1 = t.tileMovedBy(0, -1);
				if (t1 != null)
					if (!t1.isWall()) {
						this.tile = t1;
						// this.tile.tankTile=this;
						temp--;

					}
				break;
			}
			case 4:

			{ // right
				Tile t1 = t.tileMovedBy(0, 1);
				if (t1 != null)
					if (!t1.isWall()) {
						this.tile = t1;
						// this.tile.tankTile=this;
						temp--;

					}
				break;
			}

			}

			t.repaint();
		}

		if (this.tile.getPlayerTile() != null)
			this.tile.field.stop();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Figure fig = (Figure) obj;
		return fig.tile.getRow_y() == this.tile.getRow_y() && fig.tile.getColumn_x() == this.tile.getColumn_x();
	}

	@Override
	public void draw() {

		// TODO Auto-generated method stub
		Graphics g = tile.getGraphics();
		g.setColor(Color.black);
		g.drawLine(0, 0, tile.getWidth(), tile.getHeight());
		g.drawLine(0, tile.getHeight(), tile.getWidth(), 0);

	}

}
