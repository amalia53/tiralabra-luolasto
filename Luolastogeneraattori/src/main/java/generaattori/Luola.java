package generaattori;

import java.util.Random;

public class Luola {

	private Random random = new Random();
	private int minKoko;
	private int korkeus, leveys;
	private int x, y;
	private Luola vasen, oikea;

	public Luola getVasen() {
		return vasen;
	}

	public Luola getOikea() {
		return oikea;
	}

	public Luola(int x, int y, int korkeus, int leveys, int minKoko) {
		this.x = x;
		this.y = y;
		this.korkeus = korkeus;
		this.leveys = leveys;
		this.minKoko = minKoko;
	}

	/*
	 * Jakaa luola-alueen kahteen pienempään luola-alueeseen, mikäli luola-alue ei
	 * ole jo tarpeeksi pieni. Satunnaisesti jakaa joko vaakasuunnassa tai
	 * pystysuunnassa satunnaisen kokoisiksi alueiksi.
	 *
	 * @return true, jos pystyttiin jakamaan kahtia; false, jos ei voi enää jakaa
	 * pienemmiksi.
	 */

	public boolean jaaAlue() {
		boolean vaakataso;
		if (leveys / 2 >= minKoko || korkeus / 2 >= minKoko) {
			vaakataso = random.nextBoolean();
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
		int leikkaus;
		if (pituus - minKoko - minKoko == 0) {
			leikkaus = minKoko;
		} else {
			leikkaus = random.nextInt(pituus - minKoko - minKoko) + minKoko;
		}
		if (vaakataso) {
			vasen = new Luola(x, y, leikkaus, leveys, minKoko);
			oikea = new Luola(x, y + leikkaus, korkeus - leikkaus, leveys, minKoko);
		} else {
			vasen = new Luola(x, y, korkeus, leikkaus, minKoko);
			oikea = new Luola(x + leikkaus, y, korkeus, leveys - leikkaus, minKoko);
		}
		return true;
	}

	/*
	 * Luo luola-alueen sisään mahtuvan satunnaisen kokoisen luolan satunnaiseen kohtaan aluetta. Lisää luodun luolan annettuun luolastoon.
	 *
	 * @param luolasto
	 * 
	 * @return luolasto, jossa lisättynä luola
	 */
	
	public boolean[][] luoLuola(boolean[][] luolasto) {
		int minLeveys = this.leveys / 3;
		int minKorkeus = this.leveys / 3;
		int maxLeveys = this.leveys - 1;
		int maxKorkeus = this.korkeus - 1;
		int leveys = random.nextInt(maxLeveys - minLeveys) + minLeveys;
		int korkeus = random.nextInt(maxKorkeus - minKorkeus) + minKorkeus;
		int alkuX = this.x + random.nextInt(this.leveys - leveys);
		int alkuY = this.y + random.nextInt(this.korkeus - korkeus);
		for (int x = this.x; x < this.x + this.leveys; x++) {
			for (int y = this.y; y < this.y + this.korkeus; y++) {
				int loppuX = alkuX + leveys;
				int loppuY = alkuY + korkeus;
				if (x < alkuX || x >= alkuX + leveys || y < alkuY || y >= alkuY + korkeus) {
					luolasto[x][y] = true;
				}
			}
		}
		return luolasto;
	}

}
