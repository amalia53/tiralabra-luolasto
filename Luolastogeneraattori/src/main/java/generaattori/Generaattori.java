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
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int xNaapuri = x + i;
				int yNaapuri = y + j;
				if (i == 0 && j == 0) {
					continue;
				}
				if (onSeina(luolasto, xNaapuri, yNaapuri)) {
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

	/*
	 * Tehdään luolastosta yhtenäinen poistamalla tai yhdistämällä toisiinsa
	 * mahdolliset erilliset luolat
	 * 
	 * @param luolasto yhdistettävä luolasto-matriisi
	 *
	 * @return yhdistetty luolasto-matriisi
	 */

	public boolean[][] teeYhtenaiseksi(boolean[][] luolasto) {
		tulosta(luolasto);
		int luolanKoko;
		int luolia = 0;
		boolean[][] vierailtu = new boolean[luolasto.length][luolasto.length];
		while (true) {
			int xAlku = haeAloitusX(luolasto, vierailtu);
			if (xAlku == -1) {
				break;
			}
			int yAlku = haeAloitusY(luolasto, vierailtu);
			boolean[][] vierailtuKierroksella = new boolean[luolasto.length][luolasto.length];
			luolanKoko = etsiYhtenainenLuolaJaKoko(xAlku, yAlku, luolasto, vierailtuKierroksella);
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

	
	/*
	 * Haetaan X-koordinantti, josta löytyy vielä vierailemattoman luolan
	 * aloistuspiste
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtu kaikki tähän mennessä löydetyt luolaston osat
	 *
	 * @return mikäli löytyy vierailemattomia luolaston osia, palauttaa ensimmäisen
	 * vierailemattoman luolan osan X-koordinantin. Mikäli kaikki luolaston osat on
	 * löydetty, palauttaa -1
	 */

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

	/*
	 * Haetaan Y-koordinantti, josta löytyy vielä vierailemattoman luolan
	 * aloituspiste
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtu kaikki tähän mennessä löydetyt luolaston osat
	 *
	 * @return mikäli löytyy vierailemattomia luolaston osia, palauttaa ensimmäisen
	 * vierailemattoman luolan osan Y-koordinantin. Mikäli kaikki luolaston osat on
	 * löydetty, palauttaa -1. Tähän ei pitäisi koskaan joutua Y-koordinantin
	 * kohdalla, sillä looppi katkaistaan, mikäli X:n haku palauttaa -1
	 */

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

	/*
	 * Syvyyshaku, joka palauttaa löydetyn luolan koon ja merkitsee
	 * vierailtu-matriisiin kierroksella vieraillut luolan osat
	 * 
	 * @param x X-koordinantti, josta haku alkaa
	 * 
	 * @param y Y-koordinantti, josta haku alkaa
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtu aluksi tyhjä matriisi, johon täytetään vieraillut luolan osat
	 *
	 * @return haun aikana löydetyn luolan koko
	 */

	public int etsiYhtenainenLuolaJaKoko(int x, int y, boolean[][] luolasto, boolean[][] vierailtu) {
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
		return 1 + etsiYhtenainenLuolaJaKoko(x, y + 1, luolasto, vierailtu)
				+ etsiYhtenainenLuolaJaKoko(x, y - 1, luolasto, vierailtu)
				+ etsiYhtenainenLuolaJaKoko(x + 1, y, luolasto, vierailtu)
				+ etsiYhtenainenLuolaJaKoko(x - 1, y, luolasto, vierailtu);
	}

	/*
	 * Päivitetään vierailtu-matriisiin kierroksella löydetty luola
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtu kaikki täät kierrosta ennen löydetyt luolaston osat
	 * 
	 * @param vierailtuKierroksella tällä kierroksella löydetty luolaston osa
	 *
	 * @return vierailtu-matriisi, jossa näkyy kaikki tähän asti vieraillut osat
	 * luolastosta
	 */

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

	
	/*
	 * Poistetaan liian pieni erillinen luola
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtuKierroksella poistettava luola-matriisi
	 *
	 * @return luolasto-matriisi, josta poistettu luola
	 */

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

	/*
	 * Yhdistetään suurempi erillinen luola toiseen suurempaan luolaan
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtuKierroksella yhdistettävä luola-matriisi
	 *
	 * @return luolasto-matriisi, jossa luola on yhdistetty
	 */
	
	public boolean[][] yhdistaLuola(boolean[][] luolasto, boolean[][] vierailtuKierroksella) {
		return luolasto;
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
