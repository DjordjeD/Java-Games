package tanks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

public class Player extends Figure {

	public Player(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void move(Tile t) {
		// TODO Auto-generated method stub
		if (t != null)
			if (!t.isWall()) {
				t.setPlayerTile(this.getTile().getPlayerTile());
				if (t.getCoinTile() != null) {
					t.field.points++;
					t.field.getGame_field().points.setText("" + t.field.points);
					for (Iterator<Coin> iterator = t.field.getCoinList().iterator(); iterator.hasNext();) {
						Coin coin = (Coin) iterator.next();
						if (coin.getTile().equals(t)) {
							iterator.remove();

						}
					}

					t.field.coins--;
					if (t.field.coins == 0)
						t.field.stop();

				}
				for (Iterator<Tank> iterator = t.field.getTankList().iterator(); iterator.hasNext();) {
					Tank coin = (Tank) iterator.next();
					if (coin.getTile().equals(t))
						t.field.stop();
				}
				this.getTile().setPlayerTile(null);
				this.tile = t;
				// this.tile.tankTile=this;

			}
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
		g.setColor(Color.RED);
		g.drawLine(tile.getWidth() / 2, 0, tile.getWidth() / 2, tile.getHeight());
		g.drawLine(0, tile.getHeight() / 2, tile.getWidth(), tile.getHeight() / 2);

	}

}
