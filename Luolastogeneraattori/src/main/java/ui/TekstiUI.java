package ui;

import java.util.Scanner;
import generaattori.Generaattori;

public class TekstiUI {

	private Scanner lukija;

	public TekstiUI(Scanner lukija) {
		this.lukija = lukija;
	}

	public void kaynnista() {
		int koko = kysyKoko();
		Generaattori gen = new Generaattori();
		gen.generoi(koko);
		gen.tulosta();
	}

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
}
