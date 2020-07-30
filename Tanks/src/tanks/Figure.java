package tanks;

public abstract class Figure {
	protected Tile tile;

	public Figure(Tile tile) {
		super();
		this.tile = tile;
	}

	public abstract void move(Tile t);

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public abstract void draw();

	public Tile getTile() {
		return tile;
	}

}
