package generaattori;

import org.junit.*;

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
	public void laskeNaapuritJotkaSeiniaPalauttaaOikeinKunYlaNurkka() {
		int seinat = gen.laskeNaapuritJotkaSeinia(testiLuolasto, 0, 0);
		Assert.assertEquals(seinat, 6);
	}
	
	@Test
	public void laskeNaapuritJotkaSeiniaPalauttaaOikeinKunAlaNurkka() {
		int seinat = gen.laskeNaapuritJotkaSeinia(testiLuolasto, 4, 4);
		Assert.assertEquals(seinat, 5);
	}
	
	@Test
	public void parannaMuuttaaLuolaksiJosNaapureistaAlle4OnSeinia() {
		Assert.assertEquals(testiLuolasto[3][2], true);
		testiLuolasto = gen.paranna(testiLuolasto);
		Assert.assertEquals(testiLuolasto[3][2], false);
	}
	
	@Test
	public void parannaMuuttaaSeinaksiJosNaapureistaYli4OnSeinia() {
		Assert.assertEquals(testiLuolasto[0][0], false);
		testiLuolasto = gen.paranna(testiLuolasto);
		Assert.assertEquals(testiLuolasto[0][0], true);
	}
	
	@Test
	public void parannaEiMuutaLuolaksiJosNaapureistaAlle4OnSeinia() {
		Assert.assertEquals(testiLuolasto[0][2], true);
		testiLuolasto = gen.paranna(testiLuolasto);
		Assert.assertEquals(testiLuolasto[0][2], true);
	}
	
	@Test
	public void parannaEiMuutaSeinaksiJosNaapureistaYli4OnSeinia() {
		Assert.assertEquals(testiLuolasto[1][1], false);
		testiLuolasto = gen.paranna(testiLuolasto);
		Assert.assertEquals(testiLuolasto[1][1], false);
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
