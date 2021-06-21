package tietorakenteet;

import org.junit.*;

public class EtaisyysTest {
	
	@Test
	public void lahempiPalauttaaTrueJosKutsuvaEtaisyysPienempiKuinParametrin() {
		Etaisyys pienempi = new Etaisyys(0, new Koordinantti(0,0));
		Etaisyys suurempi = new Etaisyys(1, new Koordinantti(0,1));
		Assert.assertEquals(pienempi.lahempiKuin(suurempi), true);
	}
	
	@Test
	public void lahempiPalauttaaFalseJosKutsuvaEtaisyysSuurempiKuinParametrin() {
		Etaisyys pienempi = new Etaisyys(0, new Koordinantti(0,0));
		Etaisyys suurempi = new Etaisyys(1, new Koordinantti(0,1));
		Assert.assertEquals(suurempi.lahempiKuin(pienempi), false);
	}
}