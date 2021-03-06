package tietorakenteet;

public class Lista {

	private Koordinantti[] lista;
	private int indeksi;

	public Lista() {
		lista = new Koordinantti[10];
		indeksi = 0;

	}

	/**
	 * Lisää listaan annetun Koordinantin ja kasvattaa indeksiä yhdellä. Mikäli
	 * lista on jo täysi, kasvattaa ensin listan kokoa.
	 * 
	 * @param lisattava koordinantti
	 * 
	 */

	public void lisaa(Koordinantti lisattava) {
		if (onTaynna()) {
			kasvata();
		}
		lista[indeksi] = lisattava;
		indeksi++;
	}
	
	/**
	 * Lisää listaan annetut neljä Koordinanttia ja kasvattaa indeksiä neljällä. Mikäli
	 * lista on jo täysi, kasvattaa ensin listan kokoa.
	 * 
	 * @param lisattava koordinantti
	 * 
	 */
	
	public void lisaa(Koordinantti lisattava, Koordinantti lisattava2, Koordinantti lisattava3, Koordinantti lisattava4) {
		lisaa(lisattava);
		lisaa(lisattava2);
		lisaa(lisattava3);
		lisaa(lisattava4);
	}

	/**
	 * Hakee annetusta indeksistä koordinantin ja palauttaa sen. Mikäli indeksi ei
	 * ole listan sisällä, antaa virheviestin.
	 * 
	 * @param i haluttu indeksi
	 * 
	 * @return pyydetty koordinantti
	 */

	public Koordinantti hae(int i) {
		if (i < indeksi && i >= 0) {
			return lista[i];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Poistaa annetusta indeksistä koordinantin ja siirtää sen jälkeiset listan
	 * alkiot yhden taaksepäin. Mikäli indeksi ei ole listan sisällä, antaa
	 * virheviestin.
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
	 * @return päällimmäisin koordinantti
	 * 
	 */

	public Koordinantti haeJaPoistaPaallimmaisin() {
		Koordinantti palautettava = lista[indeksi - 1];
		lista[indeksi - 1] = null;
		indeksi--;
		return palautettava;
	}
	
	/**
	 * Tarkistaa, sisältääkö lista annettua koordinanttia
	 * 
	 * @param etsittava 
	 * 
	 * @return totuusarvo true, jos sisältää, false, jos ei
	 * 
	 */

	public boolean sisaltaa(Koordinantti etsittava) {
		for (int i = 0; i < indeksi; i++) {
			if (lista[i].onSamaKuin(etsittava)) {
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
		Koordinantti[] uusiLista = new Koordinantti[indeksi * 2];
		for (int i = 0; i < indeksi; i++) {
			uusiLista[i] = lista[i];
		}
		this.lista = uusiLista;
	}
	
	public Koordinantti[] getLista() {
		return lista;
	}
}
