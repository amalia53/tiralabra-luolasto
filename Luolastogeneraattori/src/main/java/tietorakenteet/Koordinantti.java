package tietorakenteet;

public class Koordinantti {

	private int x, y;

	public Koordinantti(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Luo listan, jossa kaikki koordinaatin naapurien koordinaatit ja palauttaa sen
	 * 
	 * @param pituus kartan koko
	 * 
	 * @return lista naapurien koordinaateista
	 */

	public Lista haeNaapurit(int pituus) {
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
	
	/**
	 * Tarkistaa onko annetulla koordinantilla samat x- ja y-koordinantit
	 * 
	 * @param verrattava
	 * 
	 * @return true, jos samat, false, jos erit
	 */

	public boolean onSamaKuin(Koordinantti verrattava) {
		return (this.x == verrattava.x && this.y == verrattava.y);
	}
	
	/**
	 * Tarkistaa, onko koordinantti annetussa luolastossa seinää vai luolaa
	 * 
	 * @param luolasto
	 * 
	 * @return true, jos seinää, false, jos luolaa
	 */
	
	public boolean onSeina(boolean[][] luolasto) {
		return x < 0 || y < 0 || x >= luolasto.length || y >= luolasto.length || luolasto[x][y];
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
}
