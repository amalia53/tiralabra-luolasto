package generaattorit;

import tietorakenteet.Etaisyys;
import tietorakenteet.Keko;
import tietorakenteet.Koordinantti;
import tietorakenteet.Lista;

public class TunneliGeneraattori {

	

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
}
