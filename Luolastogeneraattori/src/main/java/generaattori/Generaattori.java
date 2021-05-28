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
	 * @param koko luolaston koko
	 *
	 * @return luolasto-matriisi
	 */

	public boolean[][] generoi(int koko) {
		luolasto = new boolean[koko][koko];
		alustaLuolasto();
		for (int i = 0; i < kierroksia; i++) {
			luolasto = paranna(luolasto);
		}
		luolasto = teeYhtenaiseksi(luolasto);
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
	 * @param luolasto paranneltava luolasto-matriisi
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
	 * @param luolasto paranneltava luolasto-matriisi
	 * 
	 * @param x ruudun x-koordinaatti
	 * 
	 * @param y ruudun y-koordinaatti
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
	 * @param luolasto paranneltava luolasto-matriisi
	 * 
	 * @param x ruudun x-koordinaatti
	 * 
	 * @param y ruudun y-koordinaatti
	 *
	 * @return true, jos on seinä; false, jos luola
	 */

	public boolean onSeina(boolean[][] luolasto, int x, int y) {
		return x < 0 || y < 0 || x >= luolasto.length || y >= luolasto.length || luolasto[x][y];
	}

	public boolean[][] teeYhtenaiseksi(boolean[][] luolasto) {
		tulosta(luolasto);
		int luolanKoko;
		int luolia = 0;
		boolean[][] vierailtu = new boolean[luolasto.length][luolasto.length];		
		while (true) {
			int x_alku = haeAloitusX(luolasto, vierailtu);
			if (x_alku == -1) {
				break;
			}
			int y_alku = haeAloitusY(luolasto, vierailtu);
			boolean[][] vierailtuKierroksella = new boolean[luolasto.length][luolasto.length];
			luolanKoko = tarkistaYhtenaisyys(x_alku, y_alku, luolasto, vierailtuKierroksella);
			luolia++;
			lisaaVierailtu(luolasto, vierailtu, vierailtuKierroksella);
			System.out.println();
			System.out.println("Luolan koko: " + luolanKoko);
			if (luolanKoko < 1.5 * luolasto.length) {
				System.out.println("Poistetaan");
				poistaLuola(luolasto, vierailtuKierroksella);
			} else {
				if (luolia > 1) {
					System.out.println("Yhdistetään");
					yhdistaLuola(luolasto, vierailtuKierroksella);
				}
			}
		}
		System.out.println("Luolia oli " + luolia);
		return luolasto;
	}

	public boolean[][] poistaLuola(boolean[][] luolasto, boolean[][] vierailtuKierroksella) {
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				if (!luolasto[x][y] && vierailtuKierroksella[x][y]) {
					luolasto[x][y] = true;
				}
			}
		}
		return luolasto;
	}
	
	public boolean[][] yhdistaLuola(boolean[][] luolasto, boolean[][] vierailtuKierroksella) {
		return luolasto;
	}
	
	public boolean[][] lisaaVierailtu(boolean[][] luolasto, boolean[][] vierailtu, boolean[][] vierailtuKierroksella) {
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				if (!luolasto[x][y] && vierailtuKierroksella[x][y]) {
					vierailtu[x][y] = true;
				}
			}
		}
		return vierailtu;
	}

	public int haeAloitusX(boolean[][] luolasto, boolean[][] vierailtu) {
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				if (!luolasto[x][y] && !vierailtu[x][y]) {
					return x;
				}
			}
		}
		return -1;
	}

	public int haeAloitusY(boolean[][] luolasto, boolean[][] vierailtu) {
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				if (!luolasto[x][y] && !vierailtu[x][y]) {
					return y;
				}
			}
		}
		return -1;
	}

	public int tarkistaYhtenaisyys(int x, int y, boolean[][] luolasto, boolean[][] vierailtu) {
		if (onSeina(luolasto, x, y) || vierailtu[x][y]) {
			return 0;
		}
		vierailtu[x][y] = true;
//		if (!onSeina(luolasto, x, y + 1) && !vierailtu[x][y + 1]) {
//			tarkistaYhtenaisyys(x, y + 1, luolasto, vierailtu, koko);
//		}
//		if (!onSeina(luolasto, x, y - 1) && !vierailtu[x][y - 1]) {
//			tarkistaYhtenaisyys(x, y - 1, luolasto, vierailtu, koko);
//		}
//		if (!onSeina(luolasto, x + 1, y) && !vierailtu[x + 1][y]) {
//			tarkistaYhtenaisyys(x + 1, y, luolasto, vierailtu, koko);
//		}
//		if (!onSeina(luolasto, x - 1, y) && !vierailtu[x - 1][y]) {
//			tarkistaYhtenaisyys(x - 1, y, luolasto, vierailtu, koko);
//		}
		return 1 + tarkistaYhtenaisyys(x, y + 1, luolasto, vierailtu)
				+ tarkistaYhtenaisyys(x, y - 1, luolasto, vierailtu)
				+ tarkistaYhtenaisyys(x + 1, y, luolasto, vierailtu)
				+ tarkistaYhtenaisyys(x - 1, y, luolasto, vierailtu);
	}

	public void tulosta(boolean[][] luolasto) {
		System.out.println();
		for (int i = 0; i < luolasto.length + 2; i++) {
			System.out.print("-");
		}
		for (int x = 0; x < luolasto.length; x++) {
			System.out.println();
			System.out.print("|");
			for (int y = 0; y < luolasto.length; y++) {
				if (luolasto[x][y]) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("|");
		}
		System.out.println();
		for (int i = 0; i < luolasto.length + 2; i++) {
			System.out.print("-");
		}
	}
}
