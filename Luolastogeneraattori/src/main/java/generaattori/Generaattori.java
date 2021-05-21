package generaattori;

import java.util.Random;

public class Generaattori {
	
	private boolean[][] luolasto;
	
	private float mahdolllisuusOllaSeina = 0.45f;
	private int luolaRaja = 4;
	private int seinaRaja = 4;
	private int kierroksia = 8;
	
	public void generoi(int koko) {
		luolasto = new boolean[koko][koko];
		alustaLuolasto();
		for (int i = 0; i < kierroksia; i++) {
			luolasto = paranna();
		}
	}
	
	public void alustaLuolasto() {
		Random r = new Random();
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				if (r.nextFloat() < this.mahdolllisuusOllaSeina) {
					luolasto[x][y] = true;
				}
			}
		}
	}
	
	public boolean[][] paranna() {
		boolean[][] uusi = new boolean[luolasto.length][luolasto.length];
		for (int x = 0; x < luolasto.length; x++) {
			for (int y = 0; y < luolasto.length; y++) {
				int naapureita = laskeNaapuritJotkaSeinia(luolasto, x, y);
				if (luolasto[x][y]) {
					uusi[x][y] = naapureita < luolaRaja ? false : true;
				} else {
					uusi[x][y] = naapureita > seinaRaja ? true : false;
				}
			}
		}
		return uusi;
	}
	
	public int laskeNaapuritJotkaSeinia(boolean[][] luolasto, int x, int y) {
		int laskuri = 0;
		for (int x_i = -1; x_i < 2; x_i++) {
			for (int y_i = -1; y_i < 2; y_i++) {
				int x_naapuri = x + x_i;
				int y_naapuri = y + y_i;
				if (x_i == 0 && y_i == 0) {
					continue;
				}
				if (onSeina(luolasto, x_naapuri, y_naapuri)) {
					laskuri++;
				}
			}
		}
		return laskuri;
	}
	
	public boolean onSeina(boolean[][] luolasto, int x, int y) {
		return x < 0 || y < 0 || x >= luolasto.length || y >= luolasto.length || luolasto[x][y];
	}
	
	public void tulosta() {
		System.out.println();
		for (int i = 0; i < luolasto.length+2; i++) {
			System.out.print("-");
		}
		for (int x = 0; x < luolasto.length; x++) {
			System.out.println();
			System.out.print("|");
			for (int y = 0; y < luolasto.length; y++) {
				if (this.luolasto[x][y]) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("|");
		}
		System.out.println();
		for (int i = 0; i < luolasto.length+2; i++) {
			System.out.print("-");
		}
	}

}
