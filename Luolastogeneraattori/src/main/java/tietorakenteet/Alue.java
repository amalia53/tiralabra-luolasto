package tietorakenteet;

public class Alue {

	private Satunnainen satunnainen = new Satunnainen();
	private int minKoko = 10;
	private int korkeus, leveys;
	private int x, y;
	private Alue vasen, oikea;
	private Luola luola;

	public Alue(int x, int y, int korkeus, int leveys) {
		this.x = x;
		this.y = y;
		this.korkeus = korkeus;
		this.leveys = leveys;
	}

	public Alue getVasen() {
		return vasen;
	}

	public Alue getOikea() {
		return oikea;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Luola getLuola() {
		return luola;
	}

	/**
	 * Jakaa luola-alueen kahteen pienempään luola-alueeseen, mikäli luola-alue ei
	 * ole jo tarpeeksi pieni. Satunnaisesti jakaa joko vaakasuunnassa tai
	 * pystysuunnassa satunnaisen kokoisiksi alueiksi.
	 *
	 * @return true, jos pystyttiin jakamaan kahtia; false, jos ei voi enää jakaa
	 *         pienemmiksi.
	 */

	public boolean jaaAlue() {
		boolean vaakataso;
		if (leveys / 2 >= minKoko || korkeus / 2 >= minKoko) {
			vaakataso = satunnainen.totuusarvo();
			if (leveys / 2 < minKoko) {
				vaakataso = true;
			}
			if (korkeus / 2 < minKoko) {
				vaakataso = false;
			}
		} else {
			return false;
		}
		int pituus = vaakataso ? korkeus : leveys;
		int leikkaus = satunnainen.kokonaislukuValilta(minKoko, pituus-minKoko);
		if (vaakataso) {
			vasen = new Alue(x, y, leikkaus, leveys);
			oikea = new Alue(x, y + leikkaus, korkeus - leikkaus, leveys);
		} else {
			vasen = new Alue(x, y, korkeus, leikkaus);
			oikea = new Alue(x + leikkaus, y, korkeus, leveys - leikkaus);
		}
		return true;
	}
	
	/**
	 * Luo satunnaisen kokoisen luolan alueen sisälle
	 * 
	 * @param luolasto
	 *
	 * @return luolasto-matriisi, jossa uusi luola
	 */
	
	public boolean[][] luoLuola(boolean[][] luolasto) {
		this.luola = new Luola();
		return this.luola.luoLuola(luolasto, x, y, leveys, korkeus);
	}

	public String toString() {
		return "(" + this.x + "," + this.y + "), " + this.leveys + " x " + this.korkeus;
	}

}
