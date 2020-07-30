package tanks;

import java.awt.Canvas;

public class Tile extends Canvas {
	protected Field field;
	protected Tile matrix[][];
	protected int column_x;
	protected int row_y;
	protected boolean wallFlag;
	protected Coin coinTile;
	protected Player playerTile;
	protected Tank tankTile;

	public Tile(Field f, int i, int j) {
		super();
		this.field = f;
		this.matrix = new Tile[field.getSizeField()][field.getSizeField()];
		this.matrix = f.getMatrix();
		this.row_y = i;
		this.column_x = j;
	}

	public int[] position(Tile t) {
		// TODO Auto-generated method stub
		int temp[] = new int[2];
		for (int i = 0; i < field.getSizeField(); i++) {
			for (int j = 0; j < field.getSizeField(); j++) {
				if (t.equals(matrix[i][j])) {
					temp[0] = i;
					temp[1] = j;
					return temp;
				}
			}
		}
		return null;
	}

	public Tile tileMovedBy(int i, int j) {
		try {
			return matrix[row_y + i][column_x + j];
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public boolean coinOk(Figure t) {
		if (t instanceof Coin && coinTile != null) {
			return false;
		}
		return true;

	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Tile t = (Tile) obj;
		return this.column_x == t.column_x && this.row_y == t.row_y;
	}

	public boolean isWall() {
		return wallFlag;
	}

	public Coin getCoinTile() {
		return coinTile;
	}

	public void setCoinTile(Coin coinTile) {
		this.coinTile = coinTile;
	}

	public Player getPlayerTile() {
		return playerTile;
	}

	public void setPlayerTile(Player playerTile) {
		this.playerTile = playerTile;
	}

	public Tank getTankTile() {
		return tankTile;
	}

	public void setTankTile(Tank tankTile) {
		this.tankTile = tankTile;
	}

	public int getColumn_x() {
		return column_x;
	}

	public void setColumn_x(int column_x) {
		this.column_x = column_x;
	}

	public int getRow_y() {
		return row_y;
	}

	public void setRow_y(int row_y) {
		this.row_y = row_y;
	}

}
