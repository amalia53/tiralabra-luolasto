package tietorakenteet;

import static java.lang.System.nanoTime;

public class Satunnainen {

	/**
	 * Luo ja palauttaa satunnaisen kokonaisluvun välillä 0 - 2147483647
	 * 
	 * @return satunnainen kokonaisluku välillä 0 - 2147483647
	 */

	public int satunnainen() {
		long satunnainen = nanoTime() % 2147483647;
		return (int) satunnainen;
	}

	/**
	 * Luo ja palauttaa satunnaisen kokonaisluvun välillä 0 - parametrina annettu
	 * maksimiluku
	 * 
	 * @param max maksimiluku, joka satunnainen luku voi olla
	 * 
	 * @return satunnainen kokonaisluku välillä 0 - annettu maksimi
	 */

	public int kokonaisluku(int max) {
		if (max <= 0) {
			return 0;
		}
		int satunnainen = satunnainen() % (max + 1);
		return satunnainen;
	}

	/**
	 * Luo ja palauttaa satunnaisen kokonaisluvun välillä annettu minimi -
	 * parametrina annettu maksimiluku. Herjaa indexoutofbounds, mikäli minimi on
	 * suurempi kuin maksimi
	 * 
	 * @param min minimiluku, joka satunnainen luku voi olla
	 * 
	 * @param max maksimiluku, joka satunnainen luku voi olla
	 * 
	 * @return satunnainen kokonaisluku välillä annettu minimi - annettu maksimi
	 */

	public int kokonaislukuValilta(int min, int max) {
		if (max - min < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (max == min) {
			return min;
		}
		int satunnainen = satunnainen() % (max - min + 1);
		satunnainen += min;
		return satunnainen;
	}
	
	/**
	 * Luo ja palauttaa satunnaisen kokonaisluvun välillä 0 - 1
	 * 
	 * @return satunnainen kokonaisluku välillä 0 - 1
	 */

	public float todennakoisyys() {
		float satunnainen = kokonaisluku(100);
		satunnainen = satunnainen / 100;
		return satunnainen;
	}
	
	/**
	 * Luo ja palauttaa satunnaisen totuusarvon true tai false
	 * 
	 * @return satunnainen totuusarvo true tai false
	 */

	public boolean totuusarvo() {
		return (todennakoisyys() > 0.5);
	}

}
