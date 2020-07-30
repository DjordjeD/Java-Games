package paket;

public class Vector implements Cloneable {
	private int x;
	private int y;

	public void pow_scalar(int scalar) {
		this.x = this.x * scalar;
		this.y = this.y * scalar;

	}

	public void add_Vector(Vector vec) {
		//
		this.x += vec.x;
		this.y += vec.y;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Vector(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Vector v1 = (Vector) super.clone();
		return v1;
	}

}
