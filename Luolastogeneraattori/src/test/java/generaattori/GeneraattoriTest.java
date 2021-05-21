package generaattori;

import org.junit.*;
import generaattori.Generaattori;

public class GeneraattoriTest {
	
	Generaattori gen;
	boolean[][] testiLuolasto;

	@Before
	public void setUp() {
		gen = new Generaattori();
		luoTestiLuolasto();
	}
	
	@Test
	public void laskeNaapuritJotkaSeiniaPalauttaaOikein() {
		int seinat = gen.laskeNaapuritJotkaSeinia(testiLuolasto, 2, 2);
		Assert.assertEquals(seinat, 3);
	}
	
	@Test
	public void laskeNaapuritJotkaSeiniaPalauttaaOikeinKunNurkka() {
		int seinat = gen.laskeNaapuritJotkaSeinia(testiLuolasto, 0, 0);
		Assert.assertEquals(seinat, 6);
	}
	

	public void luoTestiLuolasto() {
		testiLuolasto = new boolean[5][5];
		testiLuolasto[0][2] = true;
		testiLuolasto[0][3] = true;
		testiLuolasto[0][4] = true;
		testiLuolasto[1][0] = true;
		testiLuolasto[1][3] = true;
		testiLuolasto[1][4] = true;
		testiLuolasto[2][0] = true;
		testiLuolasto[2][4] = true;
		testiLuolasto[3][0] = true;
		testiLuolasto[3][1] = true;
		testiLuolasto[3][2] = true;
		testiLuolasto[4][0] = true;
	}
}
