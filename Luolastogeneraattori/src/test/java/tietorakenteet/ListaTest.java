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
	public void heaIndeksistaJokaListanUlkopuolellaHeittaaVirheen() {
	    Koordinantti k = lista.hae(3);
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
