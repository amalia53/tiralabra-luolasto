package tietorakenteet;

public class Koordinantti {

	int x, y;

	public Koordinantti(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Lista getNaapurit(int pituus) {
		Lista naapurit = new Lista();
		if (x + 1 < pituus) {
			naapurit.lisaa(new Koordinantti(x + 1, y));
		}
		if (x - 1 >= 0) {
			naapurit.lisaa(new Koordinantti(x - 1, y));
		}
		if (y + 1 < pituus) {
			naapurit.lisaa(new Koordinantti(x, y + 1));
		}
		if (y - 1 >= 0) {
			naapurit.lisaa(new Koordinantti(x, y - 1));
		}
		return naapurit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	public boolean equals(Koordinantti verrattava) {
		return (this.x == verrattava.x && this.y == verrattava.y);
	}

	@Override
	public String toString() {
		return "Koordinantti (" + x + ", " + y + ")";
	}

}
