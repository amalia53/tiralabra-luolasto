package tietorakenteet;

import org.junit.*;

public class KekoTest {

	Keko keko;

	@Before
	public void SetUp() {
		keko = new Keko();
	}

	@Test
	public void uudenKeonLuominenLuo0KokoisenKeon() {
		Assert.assertEquals(keko.koko(), 0);
	}

	@Test
	public void onTyhjaPalauttaaTrueKunEiLisattyjaAlkioitaJaFalseKunOn() {
		Assert.assertEquals(keko.onTyhja(), true);
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		Assert.assertEquals(keko.onTyhja(), false);

	}

	@Test
	public void KeonKokoKasvaaYhdellaKunLisataanAlkio() {
		Assert.assertEquals(keko.koko(), 0);
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		Assert.assertEquals(keko.koko(), 1);
	}

	@Test
	public void haeKeostaPalauttaaAlkionKeostaJollaPieninEtaisyys() {
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		Assert.assertEquals(keko.haeJaPoista().getEtaisyys(), 0);
	}

	@Test
	public void keonListanKokoTuplataanKunLisataanTayteenListaan() {
		Assert.assertEquals(keko.getKeko().length, 10);
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		Assert.assertEquals(keko.getKeko().length, 20);
	}

	@Test
	public void onTaynnaPalauttaaTrueKunKekoOnTaynnaJaFalseKunEi() {
		Assert.assertEquals(keko.onTaynna(), false);
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		keko.lisaa(new Etaisyys(0, new Koordinantti(0, 0)));
		keko.lisaa(new Etaisyys(1, new Koordinantti(1, 1)));
		Assert.assertEquals(keko.onTaynna(), true);
	}

}
