package tietorakenteet;

public class Luola {

	private Satunnainen satunnainen = new Satunnainen();
	private int minLeveys, minKorkeus, maxLeveys, maxKorkeus;
	private int korkeus, leveys;
	private int x, y;
	private int kaytavia;

	public boolean[][] luoLuola(boolean[][] luolasto, int x, int y, int leveys, int korkeus) {
		minLeveys = leveys / 3 + leveys / 6;
		minKorkeus = korkeus / 3 + korkeus / 6;
		maxLeveys = leveys - 1;
		maxKorkeus = korkeus - 1;
		if (minLeveys < 3) {
			minLeveys = maxLeveys;
		}
		if (minKorkeus < 3) {
			minKorkeus = maxKorkeus;
		}
		this.leveys = satunnainen.kokonaislukuValilta(minLeveys, maxLeveys);
		this.korkeus = satunnainen.kokonaislukuValilta(minKorkeus, maxKorkeus);
		this.x = x + satunnainen.kokonaislukuValilta(1, leveys - this.leveys);
		this.y = y + satunnainen.kokonaislukuValilta(1, korkeus - this.korkeus);
		for (int i = x; i < x + leveys; i++) {
			for (int j = y; j < y + korkeus; j++) {
				if (i < this.x || i >= this.x + this.leveys || j < this.y || j >= this.y + this.korkeus) {
					luolasto[i][j] = true;
				}
			}
		}
		return luolasto;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLoppuX() {
		return x + leveys - 1;
	}

	public int getLoppuY() {
		return y + korkeus - 1;
	}

	public int kaytavienMaara() {
		return kaytavia;
	}
	
	public void lisaaKaytava() {
		kaytavia++;
	}

	/**
	 * Luo luola-alueen sisään mahtuvan satunnaisen kokoisen luolan satunnaiseen
	 * kohtaan aluetta. Lisää luodun luolan annettuun luolastoon.
	 *
	 * @param luolasto
	 * 
	 * @return luolasto, jossa lisättynä luola
	 */

	public String toString() {
		return "(" + this.x + "," + this.y + "), " + this.leveys + " x " + this.korkeus;
	}

}
