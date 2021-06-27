package tietorakenteet;

public class LuolaLista {

	private Alue[] lista;
	private int indeksi;

	public LuolaLista() {
		lista = new Alue[10];
		indeksi = 0;

	}

	/**
	 * Lisää listaan annetun Luolan ja kasvattaa indeksiä yhdellä. Mikäli lista on
	 * jo täysi, kasvattaa ensin listan kokoa.
	 * 
	 * @param lisattava luola
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
	 * Hakee annetusta indeksistä luolan ja palauttaa sen. Mikäli indeksi ei ole
	 * listan sisällä, antaa virheviestin.
	 * 
	 * @param i haluttu indeksi
	 * 
	 * @return pyydetty luola
	 */

	public Alue hae(int i) {
		if (i < indeksi && i >= 0) {
			return lista[i];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Poistaa annetusta indeksistä luolan ja siirtää sen jälkeiset listan alkiot
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
	 * @return päällimmäisin luola
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
	 * Tarkistaa, sisältääkö lista annettua luolaa
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
	 * @return totuusatrvo true, jos lista on täynnä, false, jos on vielä tilaa
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

	/**
	 * Tulostaa listan
	 * 
	 */

	public void printtaa() {
		if (!onTyhja()) {
			System.out.print("[");
			for (int i = 0; i < indeksi - 1; i++) {
				System.out.print(lista[i] + ", ");
			}
			System.out.println(lista[indeksi - 1] + "]");
		} else {
			System.out.println("Lista on tyhjä");
		}
	}
}
