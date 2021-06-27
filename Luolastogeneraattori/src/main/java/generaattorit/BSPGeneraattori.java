package generaattorit;

import tietorakenteet.Alue;
import tietorakenteet.Koordinantti;
import tietorakenteet.Lista;
import tietorakenteet.Luola;
import tietorakenteet.LuolaLista;
import tietorakenteet.Satunnainen;

public class BSPGeneraattori {

	private boolean[][] luolasto;
	private LuolaLista lista;
	private Satunnainen satunnainen;

	/**
	 * Generoi algoritmin
	 * 
	 * @param koko
	 *
	 * @return luolasto-matriisi
	 */

	public boolean[][] generoi(int koko) {
		this.satunnainen = new Satunnainen();
		luolasto = new boolean[koko][koko];
		Alue juuri = new Alue(0, 0, koko, koko);
		alustaLuolasto(juuri);
		luoLuolasto(juuri);
		lista = new LuolaLista();
		tallennaListaan(juuri);
		int tunneleita = 2;
		luoTunnelit(lista, luolasto, tunneleita);
		teeYhtenaiseksi(luolasto);
		return luolasto;
	}

	/**
	 * Luo puun, joka koostuu erikokoisista luola-alueista. Jakaa luolan kahdeksi
	 * pienemmäksi, mikäli alue on tarpeeksi suuri.
	 * 
	 * @param alue
	 */

	public void alustaLuolasto(Alue alue) {
		boolean onnistui = alue.jaaAlue();
		if (onnistui) {
			alustaLuolasto(alue.getVasen());
			alustaLuolasto(alue.getOikea());
		}
	}

	/**
	 * Luo luolat puun lehtien luola-alueisiin.
	 * 
	 * @param luola
	 *
	 */

	public void luoLuolasto(Alue alue) {
		if (alue.getVasen() != null) {
			luoLuolasto(alue.getVasen());
			luoLuolasto(alue.getOikea());
		} else {
			luolasto = alue.luoLuola(luolasto);
		}
	}

	/**
	 * Tallentaa luolat listaan tarkistamalla, onko alue puun lehti.
	 * 
	 * @param Alue
	 *
	 */

	public void tallennaListaan(Alue alue) {
		if (alue.getVasen() != null) {
			tallennaListaan(alue.getVasen());
			tallennaListaan(alue.getOikea());
		} else {
			lista.lisaa(alue);
		}
	}

	/**
	 * Luo tunneleita satunnaisesti luolien välille käymällä läpi listan, ja
	 * luomalla max 3 tunnelia listan luolasta satunnaiseen toiseen luolaan. Mikäli
	 * tunneli menee toisen tunnelin tai luolan läpi, tunnelin luonti pysäytetään
	 * siihen.
	 * 
	 * @param lista    Lista alueista, jossa luola
	 * 
	 * @param luolasto
	 *
	 */

	public void luoTunnelit(LuolaLista lista, boolean[][] luolasto, int tunnelienMaara) {
		for (int i = 0; i < lista.koko(); i++) {
			Luola luola = lista.hae(i).getLuola();
			while (luola.kaytavienMaara() <= tunnelienMaara) {
				Alue yhdistettavaAlue = lista.hae(satunnainen.kokonaisluku(lista.koko() - 1));
				Luola yhdistettava = yhdistettavaAlue.getLuola();
				int x = haeLukuValilta(luola.getX(), luola.getLoppuX(), yhdistettava.getX(), yhdistettava.getLoppuX());
				int y = haeLukuValilta(luola.getY(), luola.getLoppuY(), yhdistettava.getY(), yhdistettava.getLoppuY());
				if (x == -1 && y == -1) {
					luoMutkainenTunneli(luola, yhdistettava);
				} else if (x != -1) {
					luoSuoraYTunneli(luola, yhdistettava, x);
				} else if (y != -1) {
					luoSuoraXTunneli(luola, yhdistettava, y);
				}
				luola.lisaaKaytava();
			}
		}
	}

	/**
	 * Tarkistaa, onko luolasto yhtenäinen, ja jos ei, niin luo tunnelin erillisten
	 * luolastojen välille. Luolasto on yhtenäinen, mikäli ei löydy vierailematonta
	 * koordinanttia.
	 * 
	 * @param luolasto
	 *
	 */

	public void teeYhtenaiseksi(boolean[][] luolasto) {
		boolean[][] vierailtu = new boolean[luolasto.length][luolasto.length];
		Koordinantti aloitus = haeAloitus(luolasto, vierailtu);
		tarkistaYhtenaisyys(luolasto, vierailtu, aloitus);
		aloitus = haeAloitus(luolasto, vierailtu);
		boolean yhtenainen = aloitus == null;
		while (!yhtenainen) {
			Alue alue = haeLuolaKoordinantissa(aloitus, lista);
			luoTunneli(alue);
			vierailtu = new boolean[luolasto.length][luolasto.length];
			tarkistaYhtenaisyys(luolasto, vierailtu, aloitus);
			aloitus = haeAloitus(luolasto, vierailtu);
			yhtenainen = aloitus == null;
		}
	}

	/**
	 * Luo tunnelin lähtien annetun alueen luolasta satunnaiseen toiseen luolaan.
	 * 
	 * @param Alue alue, jonka luolasta tunnelin pitää lähteä
	 *
	 */

	public void luoTunneli(Alue alue) {
		Alue yhdistettavaAlue = lista.hae(satunnainen.kokonaisluku(lista.koko() - 1));
		Luola alku = alue.getLuola();
		Luola loppu = yhdistettavaAlue.getLuola();
		int xY = alku.getY();
		int alkuX;
		int loppuX;
		if (alku.getX() < loppu.getLoppuX()) {
			alkuX = alku.getLoppuX();
			loppuX = loppu.getX();
			for (int tunneliX = alkuX; tunneliX <= loppuX; tunneliX++) {
				luolasto[tunneliX][xY] = false;
			}
		} else {
			alkuX = alku.getX();
			loppuX = loppu.getLoppuX();
			for (int tunneliX = alkuX; tunneliX > loppuX; tunneliX--) {
				luolasto[tunneliX][xY] = false;
			}
		}
		int alkuY = xY;
		int loppuY = loppu.getY();
		int yX = loppuX;
		if (loppuY <= alkuY) {
			for (int tunneliY = alkuY; tunneliY >= loppuY; tunneliY--) {
				luolasto[yX][tunneliY] = false;
			}
		} else {
			for (int tunneliY = alkuY; tunneliY <= loppuY; tunneliY++) {
				luolasto[yX][tunneliY] = false;
			}

		}
	}

	/**
	 * Palauttaa satunnaisen luvun, joka on sekä lukujen alku1 loppu1 että alku2 ja
	 * loppu2 välillä. Mikäli lukua ei löydy väliltä, palauttaa -1.
	 * 
	 * @param alku1
	 * 
	 * @param loppu1
	 * 
	 * @param alku2
	 * 
	 * @param loppu2
	 *
	 * @return Satunnainen luku annettujen arvojen väliltä tai -1
	 */

	public int haeLukuValilta(int alku1, int loppu1, int alku2, int loppu2) {
		int suurinAlku = alku1 > alku2 ? alku1 : alku2;
		int pieninLoppu = loppu1 < loppu2 ? loppu1 : loppu2;
		if (suurinAlku > pieninLoppu) {
			return -1;
		}
		return satunnainen.kokonaislukuValilta(suurinAlku, pieninLoppu);

	}

	/**
	 * Luo tunnelin, joka tekee mutkan päästäkseen maaliluolaan. Mikäli törmää
	 * luolaan tai toiseen käytävään matkanvarrella, lopettaa tunnelin luonnin
	 * siihen.
	 * 
	 * @param luola        lähtöluola
	 * 
	 * @param yhdistettava maaliluola
	 */

	public void luoMutkainenTunneli(Luola luola, Luola yhdistettava) {
		Luola alku = yhdistettava;
		Luola loppu = luola;
		if (alku.getX() > loppu.getLoppuX()) {
			alku = luola;
			loppu = yhdistettava;
		}
		int alkuX = alku.getLoppuX();
		int loppuX = loppu.getX();
		int xY = alku.getY();
		boolean keskeytetty = false;
		for (int tunneliX = alkuX + 1; tunneliX <= loppuX; tunneliX++) {
			if (!luolasto[tunneliX][xY]) {
				keskeytetty = true;
				break;
			}
			luolasto[tunneliX][xY] = false;
		}
		if (!keskeytetty) {
			int alkuY = xY;
			int loppuY = loppu.getY();
			int yX = loppuX;
			if (loppuY <= alkuY) {
				for (int tunneliY = alkuY - 1; tunneliY >= loppuY; tunneliY--) {
					if (!luolasto[yX][tunneliY]) {
						break;
					}
					luolasto[yX][tunneliY] = false;
				}
			} else {
				for (int tunneliY = alkuY + 1; tunneliY <= loppuY; tunneliY++) {
					if (!luolasto[yX][tunneliY]) {
						break;
					}
					luolasto[yX][tunneliY] = false;
				}
			}
		}
	}

	/**
	 * Luo vaakatasossa olevan suoran tunnelin luolien välille Mikäli törmää luolaan
	 * tai toiseen käytävään matkanvarrella, lopettaa tunnelin luonnin siihen.
	 * 
	 * @param luola        lähtöluola
	 * 
	 * @param yhdistettava maaliluola
	 * 
	 * @param x
	 */

	public void luoSuoraYTunneli(Luola luola, Luola yhdistettava, int x) {
		int alku = luola.getY() < yhdistettava.getY() ? luola.getLoppuY() : yhdistettava.getLoppuY();
		int loppu = luola.getY() < yhdistettava.getY() ? yhdistettava.getY() : luola.getY();
		for (int tunneliY = alku + 1; tunneliY < loppu; tunneliY++) {
			if (!luolasto[x][tunneliY]) {
				break;
			}
			luolasto[x][tunneliY] = false;
		}
	}

	/**
	 * Luo pystysuorassa olevan suoran tunnelin luolien välille Mikäli törmää
	 * luolaan tai toiseen käytävään matkanvarrella, lopettaa tunnelin luonnin
	 * siihen.
	 * 
	 * @param luola        lähtöluola
	 * 
	 * @param yhdistettava maaliluola
	 * 
	 * @param y
	 */

	public void luoSuoraXTunneli(Luola luola, Luola yhdistettava, int y) {
		int alku = luola.getX() < yhdistettava.getX() ? luola.getLoppuX() : yhdistettava.getLoppuX();
		int loppu = luola.getX() < yhdistettava.getX() ? yhdistettava.getX() : luola.getX();
		for (int tunneliX = alku + 1; tunneliX < loppu; tunneliX++) {
			if (!luolasto[tunneliX][y]) {
				break;
			}
			luolasto[tunneliX][y] = false;
		}
	}

	/**
	 * Luodaan vierailtu-matriisi, joka kertoo, mihin luolaston osiin pääsee
	 * aloituskoordinantista yhtenäisyyden tarkistamiseksi.
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtu    Matriisi, joka kertoo, missä luolaston osissa on haun
	 *                     aikana vierailtu
	 *
	 * @param koordinantti Koordinantti, jota tarkistetaan
	 */

	public void tarkistaYhtenaisyys(boolean[][] luolasto, boolean[][] vierailtu, Koordinantti koordinantti) {
		vierailtu[koordinantti.getX()][koordinantti.getY()] = true;
		Lista kutsuttavat = koordinantti.haeNaapurit(luolasto.length);
		for (int i = 0; i < kutsuttavat.koko(); i++) {
			Koordinantti kutsuttava = kutsuttavat.hae(i);
			if (!kutsuttava.onSeina(luolasto) && !vierailtu[kutsuttava.getX()][kutsuttava.getY()]) {
				tarkistaYhtenaisyys(luolasto, vierailtu, kutsuttava);
			}
		}
	}

	/**
	 * Haetaan koordinantti luolastosta, joka on luolaa ja jossa ei olla vielä
	 * vierailtu. Palautetaan null, jos kaikkialla on vierailtu ja luolasto on
	 * yhtenäinen.
	 * 
	 * @param luolasto
	 * 
	 * @param vierailtu Matriisi, joka kertoo, missä luolaston osissa on vierailtu
	 * 
	 * @return Luolan koordinantti, jossa ei vielä vierailtu
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

	/**
	 * Haetaan alue listasta, jonka luola alkaa annetusta koordinantista. Palauttaa
	 * null, jos koordinantista ei löydy aluetta, mutta tähän ei koodissa pitäisi
	 * ikinä joutua.
	 * 
	 * @param koordinantti
	 * 
	 * @param lista        Lista, jossa kaikki luolalliset alueet
	 *
	 * @return Alue, jonka luola alkaa annetusta koordinantista.
	 */

	public Alue haeLuolaKoordinantissa(Koordinantti koordinantti, LuolaLista lista) {
		for (int i = 0; i < lista.koko(); i++) {
			if (lista.hae(i).getLuola().getX() == koordinantti.getX()
					&& lista.hae(i).getLuola().getY() == koordinantti.getY()) {
				return lista.hae(i);
			}
		}
		return null;
	}
}
