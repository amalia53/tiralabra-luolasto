package tietorakenteet;

public class Keko {
	
	Etaisyys[] keko;
	int indeksi;
	
	public Keko() {
		keko = new Etaisyys[10];
	}
	
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

	public Etaisyys haeJaPoista() {
		Etaisyys palautettava = keko[indeksi - 1];
		keko[indeksi - 1] = null;
		indeksi--;
		return palautettava;
	}
	
	public boolean onTyhja() {
		return indeksi == 0;
	}
	
	public void vaihda(Etaisyys yksi, Etaisyys kaksi, int indeksi1, int indeksi2) {
		keko[indeksi1] = kaksi;
		keko[indeksi2] = yksi;
	}
	
	public int koko() {
		return indeksi;
	}

	public void kasvata() {
		Etaisyys[] uusiKeko = new Etaisyys[indeksi * 2];
		for (int i = 0; i < indeksi; i++) {
			uusiKeko[i] = keko[i];
		}
		this.keko = uusiKeko;
	}
	
	public boolean onTaynna() {
		return indeksi == keko.length;
	}

}
 