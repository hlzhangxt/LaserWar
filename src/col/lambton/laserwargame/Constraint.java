package col.lambton.laserwargame;

class Constraint {
	int x0;
	int y0;
	int x1;
	int y1;

	public Constraint(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;

	}

	public Constraint(String x0, String y0, String x1, String y1) {
		this.x0 = Integer.parseInt(x0);
		this.y0 = Integer.parseInt(y0);
		this.x1 = Integer.parseInt(x1);
		this.y1 = Integer.parseInt(y1);

	}

	public boolean inside(int x, int y) {

		return (x > x0 && x < x1 && y > y0 && y < y1);
	}

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getWidth() {

		return x1 - x0;
	}

	public int getHeight() {

		return y1 - y0;
	}

}
