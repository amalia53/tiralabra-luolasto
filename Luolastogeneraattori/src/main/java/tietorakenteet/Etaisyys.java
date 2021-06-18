package tietorakenteet;

public class Etaisyys {
	
	private int etaisyys;
	private Koordinantti koordinantti;
	
	public Etaisyys(int etaisyys, Koordinantti koordinantti) {
		this.etaisyys = etaisyys;
		this.koordinantti = koordinantti;
	}
	
	public boolean lahempi(Etaisyys verrattava) {
		return this.etaisyys < verrattava.etaisyys;
	}

	public int getEtaisyys() {
		return etaisyys;
	}

	public Koordinantti getKoordinantti() {
		return koordinantti;
	}
	
	
	
	

}
