package tietorakenteet;

import org.junit.*;

public class ListaTest {

	Lista lista;

	@Before
	public void SetUp() {
		lista = new Lista();
	}

	@Test
	public void lisaaLisaaKoordinantinListaan() {
		lista.lisaa(new Koordinantti(0, 0));
		Assert.assertEquals(lista.koko(), 1);
	}
	
	@Test
	public void lisaaTayteenListaanKasvattaaKokoaTuplaksi() {
		Assert.assertEquals(lista.getLista().length, 10);
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		Assert.assertEquals(lista.getLista().length, 20);
	}
	
	@Test
	public void haeIndeksistaPalauttaaOikeanKoordinantin() {
		lista.lisaa(new Koordinantti(0, 1));
		lista.lisaa(new Koordinantti(0, 2));
		lista.lisaa(new Koordinantti(0, 3));
		Assert.assertEquals(lista.hae(0).getY(), 1);
		Assert.assertEquals(lista.hae(2).getY(), 3);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void heaIndeksistaJokaLiianPieniHeittaaVirheen() {
	    Koordinantti k = lista.hae(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void heaIndeksistaJokaLiianSuuriHeittaaVirheen() {
	    Koordinantti k = lista.hae(3);
	}
	
	@Test 
	public void poistaIndeksistaPoistaaOikeanKoordinantin() {
		lista.lisaa(new Koordinantti(0, 1));
		lista.lisaa(new Koordinantti(0, 2));
		lista.lisaa(new Koordinantti(0, 3));
		Assert.assertEquals(lista.sisaltaa(new Koordinantti(0,1)), true);
		lista.poista(0);
		Assert.assertEquals(lista.sisaltaa(new Koordinantti(0,1)), false);
	}
	
	@Test 
	public void poistaIndeksistaJalkeenListanIndeksitOikein() {
		lista.lisaa(new Koordinantti(0, 1));
		lista.lisaa(new Koordinantti(0, 2));
		lista.lisaa(new Koordinantti(0, 3));
		Assert.assertEquals(lista.hae(0).getY(), 1);
		lista.poista(0);
		Assert.assertEquals(lista.hae(0).getY(), 2);
		Assert.assertEquals(lista.hae(1).getY(), 3);
	}
	
	@Test 
	public void poistaIndeksistaPienentaaListanKokoa() {
		lista.lisaa(new Koordinantti(0, 1));
		lista.lisaa(new Koordinantti(0, 2));
		lista.lisaa(new Koordinantti(0, 3));
		Assert.assertEquals(lista.koko(), 3);
		lista.poista(0);
		Assert.assertEquals(lista.koko(), 2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void poistandeksistaJokaLiianPieniHeittaaVirheen() {
	    lista.poista(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void poistaIndeksistaJokaLiianSuuriHeittaaVirheen() {
	    lista.poista(3);
	}
	
	@Test 
	public void haeJaPoistaPaallimmaisinHakeeOikeanKoordinantin() {
		lista.lisaa(new Koordinantti(0, 1));
		lista.lisaa(new Koordinantti(0, 2));
		lista.lisaa(new Koordinantti(0, 3));
		Assert.assertEquals(lista.haeJaPoistaPaallimmaisin().getY(), 3);
	}
	
	@Test 
	public void haeJaPoistaPaallimmaisinPoistaaPaallimmaisenAlkion() {
		lista.lisaa(new Koordinantti(0, 1));
		lista.lisaa(new Koordinantti(0, 2));
		lista.lisaa(new Koordinantti(0, 3));
		Assert.assertEquals(lista.koko(), 3);
		Assert.assertEquals(lista.sisaltaa(new Koordinantti(0,3)), true);
		lista.haeJaPoistaPaallimmaisin();
		Assert.assertEquals(lista.koko(), 2);
		Assert.assertEquals(lista.sisaltaa(new Koordinantti(0,3)), false);
	}
	
	@Test
	public void sisaltaaKoordinantinPalauttaaTrueJosLoytyyFalseJosEiLoydy() {
		lista.lisaa(new Koordinantti(3,8));
		Assert.assertEquals(lista.sisaltaa(new Koordinantti(8,3)), false);
		Assert.assertEquals(lista.sisaltaa(new Koordinantti(3,8)), true);
	}
	
	@Test
	public void onTaynnaPalauttaaTrueJosTaynnaJaFalseJosEi() {
		Assert.assertEquals(lista.onTaynna(), false);
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		lista.lisaa(new Koordinantti(0, 0));
		Assert.assertEquals(lista.onTaynna(), true);
	}

	@Test
	public void onTyhjaPalauttaaTrueJosEiLisattyjaKoordinanttejaJaFalseJosLisatty() {
		Assert.assertEquals(lista.onTyhja(), true);
		lista.lisaa(new Koordinantti(0, 0));
		Assert.assertEquals(lista.onTyhja(), false);
	}

}
