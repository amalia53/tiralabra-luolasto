package generaattorit;

//import tietorakenteet.Koordinantti;
//import tietorakenteet.Lista;
import tietorakenteet.Luola;
//import tietorakenteet.Satunnainen;

public class BSPGeneraattori {

	private boolean[][] luolasto;

	/**
	 * Generoi algoritmin
	 * 
	 * @param koko
	 *
	 * @return luolasto-matriisi
	 */

	public boolean[][] generoi(int koko, int pieninLuola) {
		luolasto = new boolean[koko][koko];
		Luola juuri = new Luola(0, 0, koko, koko, pieninLuola);
		alustaLuolasto(juuri);
		luoLuolasto(juuri);
		return luolasto;
	}

	/**
	 * Luo puun, joka koostuu erikokoisista luola-alueista. Jakaa luolan kahdeksi
	 * pienemmäksi
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

	/**
	 * Luo luolat puun lehtien luola-alueisiin. Tulevaisuudessa tulee myös luomaan
	 * käytävät luolien välille
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
	
	

//	public boolean[][] luoTunnelit(boolean[][] luolasto) {
//		Lista aloituspisteet = new Lista();
//		for (int x = 0; x < luolasto.length; x++) {
//			for (int y = 0; y < luolasto.length; y++) {
//				if (naapuritSeinia(luolasto, x, y)) {
//					aloituspisteet.lisaa(new Koordinantti(x, y));
//				}
//			}
//		}
//		if (!aloituspisteet.onTyhja()) {
////			Random random = new Random();
//			Satunnainen satunnainen = new Satunnainen();
//			Koordinantti tunnelinAlku = (Koordinantti) aloituspisteet.hae(satunnainen.kokonaisluku(aloituspisteet.koko()-1));
//			boolean[][] vierailtu = new boolean[luolasto.length][luolasto.length];
//			Lista pino = new Lista();
//			pino.lisaa(tunnelinAlku);
//			while (!pino.onTyhja()) {
//				Koordinantti kasiteltava = ((Koordinantti) pino.haeJaPoistaPaallimmaisin());
//				vierailtu[kasiteltava.getX()][kasiteltava.getY()] = true;
//				luolasto[kasiteltava.getX()][kasiteltava.getY()] = false;
//				Lista naapurit = kasiteltava.getNaapurit(luolasto.length);
//				while (!naapurit.onTyhja()) {
//					int randomI = 0;
//					if (naapurit.koko() > 1) {
//						randomI = satunnainen.kokonaisluku(naapurit.koko()-1);
//					}
//					Koordinantti naapuri = (Koordinantti) naapurit.hae(randomI);
////					System.out.println(kasiteltava + ":n naapuri on " + naapuri);
//					if (saaKulkea(luolasto, vierailtu, naapuri.getX(), naapuri.getY(), kasiteltava.getX(), kasiteltava.getY())
//							&& !vierailtu[naapuri.getX()][naapuri.getY()]) {
////						if (random.nextFloat() < 0.7) {
//						luolasto[naapuri.getX()][naapuri.getY()] = false;
//						pino.lisaa(naapuri);
//						break;
////						}
//					}
//					naapurit.poista(randomI);
//				}
//			}
//		}
//		return luolasto;
//	}
//	
//	public boolean[][] luoOvet(boolean[][] luolasto) {
//		
//		return luolasto;
//	}
////
//	public boolean naapuritSeinia(boolean[][] luolasto, int x, int y) {
//		return onSeina(luolasto, x, y) && onSeina(luolasto, x - 1, y) && onSeina(luolasto, x + 1, y)
//				&& onSeina(luolasto, x, y - 1) && onSeina(luolasto, x, y + 1) && onSeina(luolasto, x - 1, y - 1)
//				&& onSeina(luolasto, x - 1, y + 1) && onSeina(luolasto, x + 1, y + 1)
//				&& onSeina(luolasto, x + 1, y - 1);
//	}
//
////	public boolean vierailemattomatNaapuritSeinia(boolean[][] luolasto, boolean[][] vierailtu, int x, int y) {
////		if (x - 1 < 0 || x + 1 >= luolasto.length || y < 0 || y - 1 < 0 || y + 1 >= luolasto.length) {
////			return false;
////		}
////		return (onSeina(luolasto, x, y) || vierailtu[x][y]) 
////				&& (onSeina(luolasto, x - 1, y) || vierailtu[x - 1][y])
////				&& (onSeina(luolasto, x + 1, y) || vierailtu[x + 1][y])
////				&& (onSeina(luolasto, x, y - 1) || vierailtu[x][y - 1])
////				&& (onSeina(luolasto, x, y + 1) || vierailtu[x][y + 1])
////				&& (onSeina(luolasto, x - 1, y - 1) || vierailtu[x - 1][y - 1])
////				&& (onSeina(luolasto, x - 1, y + 1) || vierailtu[x - 1][y + 1])
////				&& (onSeina(luolasto, x + 1, y + 1) || vierailtu[x + 1][y + 1])
////				&& (onSeina(luolasto, x + 1, y - 1) || vierailtu[x + 1][y - 1]);
////	}
//	
//	public boolean saaKulkea(boolean[][] luolasto, boolean[][] vierailtu, int x, int y, int edellinenX, int edellinenY) {
//		if (x - 1 < 0 || x + 1 >= luolasto.length || y < 0 || y - 1 < 0 || y + 1 >= luolasto.length) {
//			return false;
//		}
//		return (onSeina(luolasto, x - 1, y) || onEdellinen(x-1, y, edellinenX, edellinenY))
//				&& (onSeina(luolasto, x + 1, y) ||onEdellinen(x-1, y, edellinenX, edellinenY))
//				&& (onSeina(luolasto, x, y - 1) || onEdellinen(x-1, y, edellinenX, edellinenY))
//				&& (onSeina(luolasto, x, y + 1) || onEdellinen(x-1, y, edellinenX, edellinenY));
////				&& onSeina(luolasto, x - 1, y - 1)
////				&& onSeina(luolasto, x - 1, y + 1)
////				&& onSeina(luolasto, x + 1, y + 1)
////				&& onSeina(luolasto, x + 1, y - 1);
//	}
//	
//	public boolean onEdellinen(int x, int y, int edellinenX, int edellinenY) {
//		return x == edellinenX && y == edellinenY;
//	}
//
//	public boolean onSeina(boolean[][] luolasto, int x, int y) {
//		if (x < 0 || y < 0 || x >= luolasto.length || y >= luolasto.length) {
//			return false;
//		}
//		return luolasto[x][y];
//	}

}
