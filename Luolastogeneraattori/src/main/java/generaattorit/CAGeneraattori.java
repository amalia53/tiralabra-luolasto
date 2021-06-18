package generaattorit;

import java.util.Random;

import tietorakenteet.Etaisyys;
import tietorakenteet.Keko;
import tietorakenteet.Koordinantti;
import tietorakenteet.Lista;
//import tietorakenteet.Satunnainen;

public class CAGeneraattori {

	private boolean[][] luolasto;

	private float mahdollisuusOllaSeina = 0.45f;
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

	/**
	 * Alustetaan luolasto. Aluksi luolaston kaikki boolean-arvot ovat false eli
	 * luolasto on vain luolaa. Metodissa lisätään seinä matriisin ruutuun
	 * luokkamuuttujassa mahdollisuusOllaSeina määritetyllä todennäköisyydellä.
	 * 
	 */

	public void alustaLuolasto() {
//		Satunnainen satunnainen = new Satunnainen();
		Random random = new Random();
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				// if (satunnainen.todennakoisyys() < this.mahdollisuusOllaSeina) {
				if (random.nextFloat() < this.mahdollisuusOllaSeina) {
					luolasto[x][y] = true;
				}
			}
		}
	}

	/**
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

	/**
	 * Lasketaan parametrina annetun ruudun naapurit, jotka ovat seiniä Käydään läpi
	 * kaikki kahdeksan ruutua, jotka ovat ruudun naapureina. Ei käsitellä itse
	 * ruutua. Mikäli naapuriruutu on seinä, lisätään laskuriin seinä.
	 * 
	 * @param luolasto paranneltava luolasto-matriisi
	 * 
	 * @param x        ruudun x-koordinaatti
	 * 
	 * @param y        ruudun y-koordinaatti
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

	/**
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
			Koordinantti aloitus = haeAloitus(luolasto, vierailtu);
			if (aloitus == null) {
				break;
			}
			boolean[][] vierailtuKierroksella = new boolean[luolasto.length][luolasto.length];
			luolanKoko = etsiYhtenainenLuolaJaKoko(aloitus.getX(), aloitus.getY(), luolasto, vierailtuKierroksella);
			if (luolanKoko < 1.5 * luolasto.length) {
				poistaLuola(luolasto, vierailtuKierroksella);
			} else {
				isojaLuolia++;
				if (isojaLuolia > 1) {
					yhdista(luolasto, haeAloitus(luolasto, vierailtuKierroksella), vierailtuKierroksella);
				}
			}
			lisaaVierailtu(luolasto, vierailtu, vierailtuKierroksella);
		}
		return luolasto;
	}

	/**
	 * Haetaan koordinantti, josta löytyy vielä vierailemattoman luolan
	 * aloistuspiste
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtu kaikki tähän mennessä löydetyt luolaston osat
	 *
	 * @return mikäli löytyy vierailemattomia luolaston osia, palauttaa ensimmäisen
	 *         vierailemattoman luolan osan X-koordinantin. Mikäli kaikki luolaston
	 *         osat on löydetty, palauttaa -1
	 */

	public Koordinantti haeAloitus(boolean[][] luolasto, boolean[][] vierailtu) {
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				if (!luolasto[x][y] && !vierailtu[x][y]) {
					return new Koordinantti(x, y);
				}
			}
		}
		return null;
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

	/**
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

	/**
	 * Yhdistetään suurempi erillinen luola toiseen löydettyyn suurempaan luolaan.
	 * Etsitään lyhin reitti muuhun luolastoon ja luodaan siitä tunneli.
	 * 
	 * @param luolasto
	 * 
	 * @param alku      koordinantti yhdeistettävässä luolassa, josta etäisyydet
	 *                  lasketaan muualle karttaan
	 * 
	 * @param vierailtu luolasto-matriisi, joihin uusi luola yhdistetään
	 *
	 * @return luolasto-matriisi, jossa luola on yhdistetty
	 */

	public boolean[][] yhdista(boolean[][] luolasto, Koordinantti alku, boolean[][] vierailtu) {
		boolean[][] kasitelty = new boolean[luolasto.length][luolasto.length];
		int[][] etaisyys = new int[luolasto.length][luolasto.length];
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				etaisyys[x][y] = 100000;
			}
		}
		etaisyys[alku.getX()][alku.getY()] = 0;
		Keko keko = new Keko();
		keko.lisaa(new Etaisyys(0, alku));
		while (!keko.onTyhja()) {
			Etaisyys tutkittava = keko.haeJaPoista();
			int x = tutkittava.getKoordinantti().getX();
			int y = tutkittava.getKoordinantti().getY();
			if (kasitelty[x][y]) {
				continue;
			}
			kasitelty[x][y] = true;
			Lista naapurit = tutkittava.getKoordinantti().getNaapurit(luolasto.length);
			for (int i = 0; i < naapurit.koko(); i++) {
				Koordinantti naapuri = naapurit.hae(i);
				int paino = luolasto[naapuri.getX()][naapuri.getY()] ? 2 : 1;
				int nyky = etaisyys[naapuri.getX()][naapuri.getY()];
				int uusi = etaisyys[x][y] + paino;
				if (uusi < nyky) {
					etaisyys[naapuri.getX()][naapuri.getY()] = uusi;
					keko.lisaa(new Etaisyys(uusi, naapuri));
				}
			}
		}
		return luoTunneli(luolasto, etaisyys, vierailtu);
	}

	/**
	 * Etsii etäisyyksien alkupisteestä perusteella lyhimmän reitin luolastojen
	 * välillä, ja muuttaa sen tunneliksi luolien välillä.
	 * 
	 * @param luolasto
	 * 
	 * @param etaisyys  etaisyys-matiisi, johon tallennetaan etaisyys yhdestä luolan
	 *                  pisteestä kartan eri pisteisiin
	 * 
	 * @param vierailtu vierailtu-matriisi, joka kertoo yhistettävän luolan
	 *                  sijainnin kartalla
	 *
	 * @return luolasto-matriisi, jossa luolat on yhdistetty
	 */

	public boolean[][] luoTunneli(boolean[][] luolasto, int[][] etaisyys, boolean[][] vierailtu) {
		Etaisyys lahin = new Etaisyys(10000, new Koordinantti(-1, -1));
		for (int x = 0; x < vierailtu.length; x++) {
			for (int y = 0; y < vierailtu.length; y++) {
				if (vierailtu[x][y] && etaisyys[x][y] < lahin.getEtaisyys()) {
					lahin = new Etaisyys(etaisyys[x][y], new Koordinantti(x, y));
				}
			}
		}
		Keko keko = new Keko();
		keko.lisaa(lahin);
		boolean valmis = false;
		boolean[][] muutettu = new boolean[luolasto.length][luolasto.length];
		while (!keko.onTyhja()) {
			Koordinantti seinaksi = keko.haeJaPoista().getKoordinantti();
			luolasto[seinaksi.getX()][seinaksi.getY()] = false;
			muutettu[seinaksi.getX()][seinaksi.getY()] = true;
			vierailtu[seinaksi.getX()][seinaksi.getY()] = true;
			Lista naapurit = seinaksi.getNaapurit(luolasto.length);
			for (int i = 0; i < naapurit.koko(); i++) {
				int naapurinEtaisyys = etaisyys[naapurit.hae(i).getX()][naapurit.hae(i).getY()];
				if (naapurinEtaisyys == 0) {
					valmis = true;
					break;
				}
				if (naapurinEtaisyys <= lahin.getEtaisyys()
						&& !muutettu[naapurit.hae(i).getX()][naapurit.hae(i).getY()]) {
					lahin = new Etaisyys(naapurinEtaisyys, naapurit.hae(i));
				}
			}
			if (!valmis) {
				keko.lisaa(lahin);
			}
		}
		return luolasto;
	}

	/**
	 * Päivitetään vierailtu-matriisiin kierroksella löydetty luola
	 * 
	 * @param luolasto
	 * 
	 *                              osat
	 * 
	 * @param vierailtuKierroksella tällä kierroksella löydetty luolaston osa
	 *
	 * @return vierailtu-matriisi, jossa näkyy kaikki tähän asti vieraillut osat
	 *         luolastosta
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
}
