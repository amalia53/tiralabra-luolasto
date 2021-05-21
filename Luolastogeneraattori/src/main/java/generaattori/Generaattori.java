package generaattori;

import java.util.Random;

public class Generaattori {

	private boolean[][] luolasto;

	private float mahdolllisuusOllaSeina = 0.45f;
	private int luolaRaja = 4;
	private int seinaRaja = 4;
	private int kierroksia = 8;

	/**
	 * Generoidaan luolasto. Alustetaan luolasto ja parannetaan sitä
	 * kierroksia-luokkamuuttujan määrittämän määrän kertaa.
	 * 
	 * @param koko		luolaston koko
	 *
	 * @return luolasto-matriisi
	 */

	public boolean[][] generoi(int koko) {
		luolasto = new boolean[koko][koko];
		alustaLuolasto();
		for (int i = 0; i < kierroksia; i++) {
			luolasto = paranna(luolasto);
		}
		return luolasto;
	}

	/*
	 * Alustetaan luolasto. Aluksi luolaston kaikki boolean-arvot ovat false eli
	 * luolasto on vain luolaa. Metodissa lisätään seinä matriisin ruutuun
	 * luokkamuuttujassa mahdollisuusOllaSeina määritetyllä todennäköisyydellä.
	 * 
	 */

	public void alustaLuolasto() {
		Random r = new Random();
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				if (r.nextFloat() < this.mahdolllisuusOllaSeina) {
					luolasto[x][y] = true;
				}
			}
		}
	}

	/*
	 * Parannetaan luolastoa. Luodaan uusi matriisi, johon paranneltu luolasto
	 * tallennetaan. Käydään läpi parannettava luolasto. Lasketaan jokaiselle
	 * ruudulle, kuinka moni sen naapureista on seinä. Mikäli ruutu on seinä,
	 * muutetaan se luolaksi, jos laskettu seinien määrä on pienempi kuin
	 * luokkamuuttujan luolaRaja arvo. Mikäli ruutu on luola, muutetaan se seinäksi,
	 * jos laskettu seinien määrä on suurempu kuin luokkamuuttujan seinaRaja arvo
	 * 
	 * @param luolasto		paranneltava luolasto-matriisi
	 *
	 * @return paranneltu luolasto-matriisi
	 */

	public boolean[][] paranna(boolean[][] luolasto) {
		boolean[][] uusi = new boolean[luolasto.length][luolasto.length];
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				int seiniaNaapureina = laskeNaapuritJotkaSeinia(luolasto, x, y);
				if (luolasto[x][y]) {
					uusi[x][y] = seiniaNaapureina < luolaRaja ? false : true;
				} else {
					uusi[x][y] = seiniaNaapureina > seinaRaja ? true : false;
				}
			}
		}
		return uusi;
	}

	/*
	 * Lasketaan parametrina annetun ruudun naapurit, jotka ovat seiniä Käydään läpi
	 * kaikki kahdeksan ruutua, jotka ovat ruudun naapureina. Ei käsitellä itse
	 * ruutua. Mikäli naapuriruutu on seinä, lisätään laskuriin seinä.
	 * 
	 * @param luolasto		paranneltava luolasto-matriisi
	 * @param x				ruudun x-koordinaatti
	 * @param y 			ruudun y-koordinaatti
	 *
	 * @return seinien määrä annetun ruudun naapureissa
	 */

	public int laskeNaapuritJotkaSeinia(boolean[][] luolasto, int x, int y) {
		int laskuri = 0;
		for (int x_i = -1; x_i < 2; x_i++) {
			for (int y_i = -1; y_i < 2; y_i++) {
				int x_naapuri = x + x_i;
				int y_naapuri = y + y_i;
				if (x_i == 0 && y_i == 0) {
					continue;
				}
				if (onSeina(luolasto, x_naapuri, y_naapuri)) {
					laskuri++;
				}
			}
		}
		return laskuri;
	}

	/*
	 * Tutkitaan onko syötteenä annettu ruutu seinä. Tulkitaan luolaston
	 * ulkopuolella olevat ruudut seinäksi.
	 * 
	 * @param paranneltava luolasto-matriisi, ruudun x ja y -koordinaatit
	 *
	 * @return true, jos on seinä; false, jos luola
	 */

	public boolean onSeina(boolean[][] luolasto, int x, int y) {
		return x < 0 || y < 0 || x >= luolasto.length || y >= luolasto.length || luolasto[x][y];
	}

}
