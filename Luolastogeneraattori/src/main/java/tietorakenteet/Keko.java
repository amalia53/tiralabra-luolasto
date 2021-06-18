package tietorakenteet;

public class Keko {

	private Etaisyys[] keko;
	private int indeksi;

	public Keko() {
		keko = new Etaisyys[10];
	}

	/**
	 * Lisää Etäisyys-tietorakenteen kekoon oikeaan kohtaan etäisyyde mukaan. Mikäli keko on täynnä, kasvattaa kekoa
	 * ennen lisäystä. Päivittää indeksin seuraavaksi.
	 * 
	 * @param lisattava Etäisyys, joka lisätään kekoon
	 */

	public void lisaa(Etaisyys lisattava) {
		if (onTaynna()) {
			kasvata();
		}
		keko[indeksi] = lisattava;
		int uudenIndeksi = indeksi;
		for (int i = indeksi - 1; i >= 0; i--) {
			if (keko[i].lahempi(lisattava)) {
				vaihda(keko[i], lisattava, i, uudenIndeksi);
				uudenIndeksi = i;
			}
		}
		indeksi++;
	}
	
	/**
	 * Hakee ylimmän eli pienimmän etäisyyden omaavan Etäisyys-tietorakenteen ja poistaa sen keosta.
	 * 
	 * @return pienimmän etäisyyden Etäisyys-tietorakenne
	 */

	public Etaisyys haeJaPoista() {
		Etaisyys palautettava = keko[indeksi - 1];
		keko[indeksi - 1] = null;
		indeksi--;
		return palautettava;
	}
	
	/**
	 * Tarkistaa onko keko tyhjä.
	 * 
	 * @return totuusarvo true, jos on tyhjä, false, jos ei ole tyhjä
	 */

	public boolean onTyhja() {
		return indeksi == 0;
	}
	
	/**
	 * Palauttaa keon koon eli alkioiden määrän keossa
	 * 
	 * @return montako alkiota keossa on
	 */

	public int koko() {
		return indeksi;
	}
	
	/**
	 * Vaihtaa kahden alkion paikkaa keskenään keossa
	 * 
	 * @param yksi ensimmäinen vaihdettava alkio
	 * 
	 * @param kaksi toinen vaihdettava alkio
	 * 
	 * @param indeksi1 ensimmäisen vaihdettavan alkion alkuperäinen indeksi
	 * 
	 * @param indeksi2 toisen vaihdettavan alkion alkuperäinen indeksi
	 * 
	 */
	
	public void vaihda(Etaisyys yksi, Etaisyys kaksi, int indeksi1, int indeksi2) {
		keko[indeksi1] = kaksi;
		keko[indeksi2] = yksi;
	}
	
	/**
	 * Kasvattaa keon koon kaksinkertiaseksi
	 * 
	 */

	public void kasvata() {
		Etaisyys[] uusiKeko = new Etaisyys[indeksi * 2];
		for (int i = 0; i < indeksi; i++) {
			uusiKeko[i] = keko[i];
		}
		this.keko = uusiKeko;
	}
	
	/**
	 * Tarkistaa onko keko täynnä
	 * 
	 * @return totuusatrvo true, jos keko on täynnä, false, jos on vielä tilaa
	 */

	public boolean onTaynna() {
		return indeksi == keko.length;
	}

}
