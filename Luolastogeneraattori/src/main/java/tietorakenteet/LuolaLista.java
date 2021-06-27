package tietorakenteet;

public class LuolaLista {

	private Alue[] lista;
	private int indeksi;

	public LuolaLista() {
		lista = new Alue[10];
		indeksi = 0;

	}

	/**
	 * Lisää listaan annetun Luolan alueen ja kasvattaa indeksiä yhdellä. Mikäli lista on
	 * jo täysi, kasvattaa ensin listan kokoa.
	 * 
	 * @param lisattava luolan alue
	 * 
	 */

	public void lisaa(Alue lisattava) {
		if (onTaynna()) {
			kasvata();
		}
		lista[indeksi] = lisattava;
		indeksi++;
	}

	/**
	 * Hakee annetusta indeksistä luolan alueen ja palauttaa sen. Mikäli indeksi ei ole
	 * listan sisällä, antaa virheviestin.
	 * 
	 * @param i haluttu indeksi
	 * 
	 * @return pyydetty luolan alue
	 */

	public Alue hae(int i) {
		if (i < indeksi && i >= 0) {
			return lista[i];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Poistaa annetusta indeksistä luolan alue ja siirtää sen jälkeiset listan alkiot
	 * yhden taaksepäin. Mikäli indeksi ei ole listan sisällä, antaa virheviestin.
	 * 
	 * @param i haluttu indeksi
	 * 
	 */

	public void poista(int poistettavaI) {
		if (poistettavaI < indeksi && poistettavaI >= 0) {
			for (int i = poistettavaI; i < indeksi - 1; i++) {
				lista[i] = lista[i + 1];
			}
			lista[indeksi - 1] = null;
			indeksi--;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Hakee ja poistaa päällimmäisen alkion listasta
	 * 
	 * @return päällimmäisin luolan alue
	 * 
	 */

	public void poista(Alue alue) {
		for (int i = 0; i < indeksi; i++) {
			if (lista[i].equals(alue)) {
				poista(i);
			}
		}
	}

	/**
	 * Tarkistaa, sisältääkö lista annettua luolan aluetta
	 * 
	 * @param etsittava
	 * 
	 * @return totuusarvo true, jos sisältää, false, jos ei
	 * 
	 */

	public boolean sisaltaa(Alue etsittava) {
		for (int i = 0; i < indeksi; i++) {
			if (lista[i].equals(etsittava)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tarkistaa onko lista tyhjä.
	 * 
	 * @return totuusarvo true, jos on tyhjä, false, jos ei ole tyhjä
	 */

	public boolean onTyhja() {
		return indeksi == 0;
	}

	/**
	 * Tarkistaa onko lista täynnä
	 * 
	 * @return totuusarvo true, jos lista on täynnä, false, jos on vielä tilaa
	 */

	public boolean onTaynna() {
		return indeksi == lista.length;
	}

	/**
	 * Palauttaa listan koon eli alkioiden määrän listassa
	 * 
	 * @return alkioiden määrä listassa
	 */

	public int koko() {
		return indeksi;
	}

	/**
	 * Kasvattaa listan koon kaksinkertiaseksi
	 * 
	 */

	public void kasvata() {
		Alue[] uusiLista = new Alue[indeksi * 2];
		for (int i = 0; i < indeksi; i++) {
			uusiLista[i] = lista[i];
		}
		this.lista = uusiLista;
	}
}
