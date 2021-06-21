package tietorakenteet;

public class Etaisyys {

	private int etaisyys;
	private Koordinantti koordinantti;

	public Etaisyys(int etaisyys, Koordinantti koordinantti) {
		this.etaisyys = etaisyys;
		this.koordinantti = koordinantti;
	}

	/**
	 * Vertaa annettua Etäisyys-tietorakenteen etäisyyttä omaan etäisyyteen, ja
	 * kertoo onko oma etäisyys pienempi kuin annetun
	 * 
	 * @return true, jos oma lähempi, false, jos annettu etäisyys lähempi
	 */

	public boolean lahempiKuin(Etaisyys verrattava) {
		return this.etaisyys < verrattava.etaisyys;
	}

	public int getEtaisyys() {
		return etaisyys;
	}

	public Koordinantti getKoordinantti() {
		return koordinantti;
	}

}
