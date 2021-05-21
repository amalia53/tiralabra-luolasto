package ui;

import java.util.Scanner;

public class TekstiUI {
	
	Scanner lukija;
	
	public TekstiUI(Scanner lukija) {
		this.lukija = lukija;
	}
	
	 public void kaynnista() {
		 System.out.println("Anna luolaston koko: ");
		 int n = lukija.nextInt();
		 //Luolasto luolasto = LuolastoGeneraattori.luoLuolasto(n);
		 //System.out.println(luolasto);
		 System.out.println("Hei hei!");
	 }
}