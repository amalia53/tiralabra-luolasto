package tietorakenteet;

import static java.lang.System.nanoTime;

public class Satunnainen {

	public int satunnainen() {
		long satunnainen = nanoTime() % 2147483647;
		return (int) satunnainen;
	}
	
	public int kokonaisluku(int max) { 
		if (max <= 0) {
			return 0;
		}
		int satunnainen = satunnainen() % (max+1);
		return satunnainen;
	}
	
	public int kokonaislukuValilta(int min, int max) { 
		if (max-min <= 0) {
			return 0;
		}
		int satunnainen = satunnainen() % (max-min+1);
		satunnainen += min;
		return satunnainen;
	}
	
	public float todennakoisyys() {
		float satunnainen = kokonaisluku(100);
		satunnainen = satunnainen / 100;
		return satunnainen;
	}
	
	public boolean totuusarvo() {
		return (todennakoisyys() > 0.5);
	}

}
