package generaattori;

import java.util.Random;

public class CAGeneraattori {

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
		int luolanKoko;
		int isojaLuolia = 0;
		boolean[][] vierailtu = new boolean[luolasto.length][luolasto.length];
		while (true) {
			int xAlku = haeAloitusX(luolasto, vierailtu);
			if (xAlku == -1) {
				break;
			}
			int yAlku = haeAloitusY(luolasto, vierailtu);
			boolean[][] vierailtuKierroksella = new boolean[luolasto.length][luolasto.length];
			luolanKoko = etsiYhtenainenLuolaJaKoko(xAlku, yAlku, luolasto, vierailtuKierroksella);
			if (luolanKoko < 1.5 * luolasto.length) {
//				System.out.println("Poistetaan");
				poistaLuola(luolasto, vierailtuKierroksella);
			} else {
				isojaLuolia++;
				if (isojaLuolia > 1) {
//					System.out.println("Yhdistetään");
//					yhdistaLuola(luolasto, vierailtu, vierailtuKierroksella, xAlku, yAlku);
				}
			}
			lisaaVierailtu(luolasto, vierailtu, vierailtuKierroksella);
		}
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
	 * @param vierailtu aluksi tyhjä matriisi, johon täytetään vieraillut luolan
	 * osat
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
	 * Yhdistetään suurempi erillinen luola toiseen löydettyyn suurempaan luolaan.
	 * Etsitään lyhin reitti muuhun luolastoon ja luodaan siitä tunnelli.
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtu luolasto-matriisi, joihin uusi luola yhdistetään
	 * 
	 * @param vierailtuKierroksella yhdistettävä luola-matriisi
	 *
	 * @return luolasto-matriisi, jossa luola on yhdistetty
	 */

	public boolean[][] yhdistaLuola(boolean[][] luolasto, boolean[][] vierailtu, boolean[][] vierailtuKierroksella,
			int xAlku, int yAlku) {
		// voi yhdistää myös poistettavaan luolaan, koska vierailtu säilyttää myös
		// niiden tietoja. Miten pois?
		int[][] etaisyys = new int[luolasto.length][luolasto.length];
		boolean muutos;
		//Etsitään jokaisesta yhdistettävän luolan pisteestä erikseen lyhin reitti
		//Tarkistetaan mikä lopulta lyhin
		for (int x = 0; x < etaisyys.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				etaisyys[x][y] = 100000;
			}
		}
		for (int i = 0; i < etaisyys.length; i++) {
			for (int j = 0; j < luolasto.length; j++) {
				if (!vierailtuKierroksella[i][j]) {
					etaisyys[i][j] = 0;
					//tarvitaan aina oma etaisyystaulukko, ei voi käyttää samaa per piste
					//also nyt alkaa olla jo nii monta sisäistä for loopia, huhhuh
					//tee oma metodi tosta whilesta, jossa annetaan aina uusi etaisyys matriksi, joka sitten tallennetaan vaikka listaan
					while (true) {
						muutos = false;
						for (int x = 0; x < luolasto.length; x++) {
							for (int y = 0; y < luolasto.length; y++) {
								muutos = etsiLyhinReitti(luolasto, etaisyys, vierailtuKierroksella, x + 1, y, etaisyys[x][y]);
								muutos = etsiLyhinReitti(luolasto, etaisyys, vierailtuKierroksella, x - 1, y, etaisyys[x][y]);
								muutos = etsiLyhinReitti(luolasto, etaisyys, vierailtuKierroksella, x, y + 1, etaisyys[x][y]);
								muutos = etsiLyhinReitti(luolasto, etaisyys, vierailtuKierroksella, x, y - 1, etaisyys[x][y]);
							}
						}
						if (!muutos) {
							break;
						}
					}
				}
			}
		}
		return luoTunneli(luolasto, etaisyys);
	}

	/*
	 * Etsii lyhimmän reitin yhdistettävästä luolasta luolaston annettuun
	 * koordinanttiin
	 * 
	 * @param luolasto
	 * 
	 * @param etaisyys etaisyys-matiisi, johon tallennetaan etaisyys luolasta kartan
	 * eri pisteisiin
	 * 
	 * @param vierailtuKierroksella yhdistettävä luola-matriisi
	 * 
	 * @param x tutkittavan kartan osan X-koordinantti
	 * 
	 * @param y tutkittavan kartan osan Y-koordinantti
	 * 
	 * @param kutsuvanEtaisyys kutsuvan koordinantin etäisyys yhdistettävästä
	 * luolasta
	 *
	 * @return luolasto-matriisi, jossa luola on yhdistetty
	 */

	public boolean etsiLyhinReitti(boolean[][] luolasto, int[][] etaisyys, boolean[][] vierailtuKierroksella, int x,
			int y, int kutsuvanEtaisyys) {
		if (x >= luolasto.length || x < 0 || y >= luolasto.length || y < 0) {
			return false;
		}
		if (vierailtuKierroksella[x][y]) {
			return false;
		}
		int paino = 1;
		if (onSeina(luolasto, x, y)) {
			paino = 2;
		}
		int nyky = etaisyys[x][y];
		int uusi = kutsuvanEtaisyys + paino;
		if (nyky == 0 || uusi < nyky) {
			etaisyys[x][y] = uusi;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Etsii etäisyyden perusteella luolaston pisteen, johon luola yhdistetään. Luo
	 * tunnelin luolan ja luolaston välille (vielä tekemättä).
	 * 
	 * @param luolasto
	 * 
	 * @param etaisyys etaisyys-matiisi, johon tallennetaan etaisyys luolasta kartan
	 * eri pisteisiin
	 *
	 * @return luolasto-matriisi, jossa luola on yhdistetty
	 */

	public boolean[][] luoTunneli(boolean[][] luolasto, int[][] etaisyys) {
		int pieninEtaisyys = luolasto.length * luolasto.length;
		int pienimmanX = 0;
		int pienimmanY = 0;
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				if (etaisyys[x][y] == 100000) {
					System.out.print("*,");
				}
				System.out.print(etaisyys[x][y] + ",");
				if (etaisyys[x][y] < pieninEtaisyys && etaisyys[x][y] != 0 && !luolasto[x][y]) {
					pieninEtaisyys = etaisyys[x][y];
					pienimmanX = x;
					pienimmanY = y;
				}
			}
			System.out.println();
		}

		System.out.println("X: " + pienimmanX + ", Y: " + pienimmanY);
		return luolasto;
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
