package tietorakenteet;

import org.junit.*;

public class KoordinanttiTest {

	@Test
	public void haeNaapuritPalauttaaKoordinantinYmparillaOlevatKoordinantitSisaltavanListan() {
		Koordinantti k = new Koordinantti(1,1);
		Lista naapurit = k.haeNaapurit(3);
		Assert.assertEquals(naapurit.koko(), 4);
		Assert.assertEquals(naapurit.hae(0).getX(), 2);
		Assert.assertEquals(naapurit.hae(1).getX(), 0);
		Assert.assertEquals(naapurit.hae(2).getY(), 2);
		Assert.assertEquals(naapurit.hae(3).getY(), 0);
	}
	
	@Test
	public void haeNaapuritLisaaVainKartanSisaisetNaapurit() {
		Koordinantti k = new Koordinantti(0,2);
		Lista naapurit = k.haeNaapurit(3);
		Assert.assertEquals(naapurit.koko(), 2);
		k.setX(2);
		k.setY(0);
		naapurit = k.haeNaapurit(3);
		Assert.assertEquals(naapurit.koko(), 2);
	}
	
	@Test
	public void onSamaKuinPalauttaaTrueJosKoordinanteillaSamaXJaY() {
		Koordinantti k = new Koordinantti(0,2);
		Koordinantti sama = new Koordinantti(0,2);
		Assert.assertEquals(k.onSamaKuin(sama), true);
	}
	
	@Test
	public void onSamaKuinPalauttaaFalseJosKoordinanteillaEriXTaiY() {
		Koordinantti k = new Koordinantti(0,2);
		Koordinantti eriY = new Koordinantti(0,1);
		Koordinantti eriX = new Koordinantti(1,2);
		Koordinantti erit = new Koordinantti(3,4);
		Assert.assertEquals(k.onSamaKuin(eriY), false);
		Assert.assertEquals(k.onSamaKuin(eriX), false);
		Assert.assertEquals(k.onSamaKuin(erit), false);

	}
}
