package sovellus;

import ui.TekstiUI;
import java.util.Scanner;

public class Main {

	 public static void main(String[] args) {
		 TekstiUI ui = new TekstiUI(new Scanner(System.in));
		 ui.kaynnista();
	 }
}
