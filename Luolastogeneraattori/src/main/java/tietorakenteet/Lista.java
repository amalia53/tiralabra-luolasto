package tietorakenteet;

public class Lista {

	private Koordinantti[] lista;
	private int indeksi;

	public Lista() {
		lista = new Koordinantti[10];
		indeksi = 0;

	}

	public void lisaa(Koordinantti lisattava) {
		if (onTaynna()) {
			kasvata();
		}
		lista[indeksi] = lisattava;
		indeksi++;
	}

	public Koordinantti hae(int i) {
		if (i < indeksi && i >= 0) {
			return lista[i];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

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

	public Koordinantti haeJaPoistaPaallimmaisin() {
		Koordinantti palautettava = lista[indeksi - 1];
		lista[indeksi - 1] = null;
		indeksi--;
		return palautettava;
	}

	public boolean sisaltaa(Koordinantti etsittava) {
		for (int i = 0; i < indeksi; i++) {
			if (lista[i].equals(etsittava)) {
				return true;
			}
		}
		return false;
	}

	public boolean onTyhja() {
		return indeksi == 0;
	}

	public boolean onTaynna() {
		return indeksi == lista.length;
	}

	public int koko() {
		return indeksi;
	}

	public void kasvata() {
		Koordinantti[] uusiLista = new Koordinantti[indeksi * 2];
		for (int i = 0; i < indeksi; i++) {
			uusiLista[i] = lista[i];
		}
		this.lista = uusiLista;
	}

	public void printtaa() {
		System.out.print("[");
		for (int i = 0; i < indeksi - 1; i++) {
			System.out.print(lista[i] + ", ");
		}
		System.out.println(lista[indeksi - 1] + "]");
	}
}
