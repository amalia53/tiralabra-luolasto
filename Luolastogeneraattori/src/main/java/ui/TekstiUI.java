package ui;

import java.util.Scanner;
import generaattori.Generaattori;

public class TekstiUI {

	private Scanner lukija;
	
	/*
	 * Luodaan käyttöliittymä syötteenä annetulla lukijalla
	 * 
	 * @param käyttäjän syötteitä lukeva lukija
	 */

	public TekstiUI(Scanner lukija) {
		this.lukija = lukija;
	}

	/*
	 * Käynnistetään käyttöliittymä. Kysytään kokoa ja generoidaan luolasto ja
	 * tulostetaan se.
	 */

	public void kaynnista() {
		int koko = kysyKoko();
		Generaattori gen = new Generaattori();
		boolean[][] luolasto = gen.generoi(koko);
		tulosta(luolasto);
	}

	/*
	 * Kysytään käyttäjältä luolaston kokoa. Mikäli käyttäjä syöttää positiivisen
	 * kokonaisluvun, palautetaan syötetty luku. Mikäli käyttäjä syöttää
	 * negatiivisen luvun, nollan tai ei-luvun, palautetaan virheilmoitus
	 * "Syötä positiivinen kokonaisluku" ja kutsutaan metodia uudelleen.
	 * 
	 * @return koko
	 */

	public int kysyKoko() {
		System.out.print("Anna luolaston koko: ");
		if (lukija.hasNextInt()) {
			int koko = lukija.nextInt();
			if (koko > 0) {
				return koko;
			} else {
				System.out.println("Syötä positiivinen kokonaisluku");
				return kysyKoko();
			}
		} else {
			lukija.next();
			System.out.println("Syötä positiivinen kokonaisluku");
			return kysyKoko();
		}
	}
	
	/*
	 * Tulostetaan parametrina annettu matriisi. Seinät näytetään tähtinä, luolat tyhjinä ja luolasto on rajattu viivoin.
	 * 
	 * @param tulostettava luolasto-matriisi
	 */

	public void tulosta(boolean[][] luolasto) {
		System.out.println();
		for (int i = 0; i < luolasto.length + 2; i++) {
			System.out.print("-");
		}
		for (int x = 0; x < luolasto.length; x++) {
			System.out.println();
			System.out.print("|");
			for (int y = 0; y < luolasto.length; y++) {
				if (luolasto[x][y]) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("|");
		}
		System.out.println();
		for (int i = 0; i < luolasto.length + 2; i++) {
			System.out.print("-");
		}
	}
}
