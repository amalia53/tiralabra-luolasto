package generaattori;

public class BSPGeneraattori {

	private boolean[][] luolasto;
	
	/*
	 * Generoi algoritmin
	 * 
	 * @param koko
	 *
	 * @return luolasto-matriisi
	 */

	public boolean[][] generoi(int koko) {
		luolasto = new boolean[koko][koko];
		Luola juuri = new Luola(0, 0, koko, koko, koko/5);
		alustaLuolasto(juuri);
		luoLuolasto(juuri);
		return luolasto;
	}
	
	/*
	 * Luo puun, joka koostuu erikokoisista luola-alueista. Jakaa luolan kahdeksi pienemmäksi
	 * 
	 * @param luola
	 */

	public void alustaLuolasto(Luola luola) {
		boolean onnistui = luola.jaaAlue();
		if (onnistui) {
			alustaLuolasto(luola.getVasen());
			alustaLuolasto(luola.getOikea());
		}
	}
	
	/*
	 * Luo luolat puun lehtien luola-alueisiin. Tulevaisuudessa tulee myös luomaan käytävät luolien välille
	 * 
	 * @param luola
	 *
	 */

	public void luoLuolasto(Luola luola) {
		if (luola.getVasen() != null) {
			luoLuolasto(luola.getVasen());
			luoLuolasto(luola.getOikea());
		} else {
			luolasto = luola.luoLuola(luolasto);
		}

	}

}
