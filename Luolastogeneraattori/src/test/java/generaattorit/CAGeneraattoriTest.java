package generaattorit;

import org.junit.*;

import tietorakenteet.Koordinantti;

public class CAGeneraattoriTest {

	CAGeneraattori gen;
	boolean[][] testiLuolasto;
	boolean[][] testiLuolastoEiYhtenainen;
	boolean[][] testiLuolastoKaksiSuurta;

	@Before
	public void setUp() {
		gen = new CAGeneraattori();
		luoTestiLuolasto();
		luoTestiLuolastoEiYhtenainen();
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

	@Test
	public void lisaaVierailtuLisaaUudellaKierroksellaLoytyneenLuolanMatriisiin() {
		boolean[][] vierailtuKierroksella = new boolean[5][5];
		boolean[][] vierailtu = new boolean[5][5];
		vierailtuKierroksella[2][1] = true;
		vierailtuKierroksella[3][1] = true;
		vierailtuKierroksella[4][0] = true;
		vierailtuKierroksella[4][1] = true;
		Assert.assertEquals(vierailtu[2][1], false);
		gen.lisaaVierailtu(testiLuolastoEiYhtenainen, vierailtu, vierailtuKierroksella);
		Assert.assertEquals(vierailtu[2][1], true);
	}

	@Test
	public void poistaLuolaPoistaaLiianPienenErillisenLuolan() {
		boolean[][] vierailtuKierroksella = new boolean[5][5];
		vierailtuKierroksella[2][1] = true;
		vierailtuKierroksella[3][1] = true;
		vierailtuKierroksella[4][0] = true;
		vierailtuKierroksella[4][1] = true;
		Assert.assertEquals(testiLuolastoEiYhtenainen[3][1], false);
		gen.poistaLuola(testiLuolastoEiYhtenainen, vierailtuKierroksella);
		Assert.assertEquals(testiLuolastoEiYhtenainen[3][1], true);
	}

	@Test
	public void yhdistaYhdistaaSuurenErillisenLuolan() {
		boolean[][] vierailtuKierroksella = new boolean[5][5];
		vierailtuKierroksella[2][1] = true;
		vierailtuKierroksella[3][1] = true;
		vierailtuKierroksella[4][0] = true;
		vierailtuKierroksella[4][1] = true;
		Assert.assertEquals(testiLuolastoEiYhtenainen[3][1], false);
		gen.poistaLuola(testiLuolastoEiYhtenainen, vierailtuKierroksella);
		Assert.assertEquals(testiLuolastoEiYhtenainen[3][1], true);
	}

	@Test
	public void etsiYhtenainenLuolaJaKokoPalauttaaYhtenaisenLuolanKoon() {
		boolean[][] vierailtu = new boolean[5][5];
		Assert.assertEquals(gen.etsiYhtenainenLuolaJaKoko(0, 0, testiLuolastoEiYhtenainen, vierailtu), 11);
	}

	@Test
	public void haeAloitusAntaaVierailemattomanLuolanXKoordinaatin() {
		boolean[][] vierailtu = new boolean[5][5];
		Assert.assertEquals(gen.haeAloitus(testiLuolastoEiYhtenainen, vierailtu).getX(), 0);
		vierailtu[0][0] = true;
		vierailtu[0][1] = true;
		vierailtu[0][2] = true;
		vierailtu[0][3] = true;
		vierailtu[0][4] = true;
		vierailtu[1][3] = true;
		vierailtu[1][4] = true;
		vierailtu[2][3] = true;
		vierailtu[2][4] = true;
		vierailtu[3][3] = true;
		vierailtu[3][4] = true;
		Assert.assertEquals(gen.haeAloitus(testiLuolastoEiYhtenainen, vierailtu).getX(), 2);
	}

	@Test
	public void haeAloitusYAntaaVierailemattomanLuolanYKoordinaatin() {
		boolean[][] vierailtu = new boolean[5][5];
		Assert.assertEquals(gen.haeAloitus(testiLuolastoEiYhtenainen, vierailtu).getY(), 0);
		vierailtu[0][0] = true;
		vierailtu[0][1] = true;
		vierailtu[0][2] = true;
		vierailtu[0][3] = true;
		vierailtu[0][4] = true;
		vierailtu[1][3] = true;
		vierailtu[1][4] = true;
		vierailtu[2][3] = true;
		vierailtu[2][4] = true;
		vierailtu[3][3] = true;
		vierailtu[3][4] = true;
		Assert.assertEquals(gen.haeAloitus(testiLuolastoEiYhtenainen, vierailtu).getY(), 1);
	}

	@Test
	public void teeYhtenaiseksiLoytaaPienenErillisenLuolanJaPoistaaSen() {
		Assert.assertEquals(testiLuolastoEiYhtenainen[3][1], false);
		gen.teeYhtenaiseksi(testiLuolastoEiYhtenainen);
		Assert.assertEquals(testiLuolastoEiYhtenainen[3][1], true);
	}
	
	@Test
	public void teeYhtenaiseksiYhdistaaLuolatJosSuuretErillisetLuolat() {
		luoTestiLuolastoKaksiSuurta();
		Assert.assertEquals(testiLuolastoKaksiSuurta[0][2], true);
		testiLuolastoKaksiSuurta = gen.teeYhtenaiseksi(testiLuolastoKaksiSuurta);
		Assert.assertEquals(testiLuolastoKaksiSuurta[0][2], false);
	}

	@Test
	public void yhdistaLuoTunnelinErillistenLuolastojenValille() {
		luoTestiLuolastoKaksiSuurta();
		boolean[][] vierailtu = new boolean[5][5];
		vierailtu[0][3] = true;
		vierailtu[0][4] = true;
		vierailtu[1][3] = true;
		vierailtu[1][4] = true;
		vierailtu[2][3] = true;
		vierailtu[2][4] = true;
		vierailtu[3][3] = true;
		vierailtu[3][4] = true;
		vierailtu[4][3] = true;
		vierailtu[4][4] = true;
		Koordinantti aloitus = gen.haeAloitus(testiLuolastoKaksiSuurta, vierailtu);
		Assert.assertEquals(testiLuolastoKaksiSuurta[0][2], true);
		testiLuolastoKaksiSuurta = gen.yhdista(testiLuolastoKaksiSuurta, aloitus, vierailtu);
		Assert.assertEquals(testiLuolastoKaksiSuurta[0][2], false);
	}
	
	@Test
	public void tarkistaLuolanOsuusPalauttaaOikeanProsentin() {
		boolean[][] luolasto = new boolean[2][2];
		Assert.assertEquals(gen.tarkistaLuolanOsuus(luolasto), 1, 0);
		luolasto[0][0] = true;
		Assert.assertEquals(gen.tarkistaLuolanOsuus(luolasto), 0.75, 0);
		luolasto[1][1] = true;
		float puolet = 0.5f;
		Assert.assertEquals(gen.tarkistaLuolanOsuus(luolasto), puolet, 0);
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

	public void luoTestiLuolastoEiYhtenainen() {
		testiLuolastoEiYhtenainen = new boolean[5][5];
		testiLuolastoEiYhtenainen[1][0] = true;
		testiLuolastoEiYhtenainen[1][1] = true;
		testiLuolastoEiYhtenainen[1][2] = true;
		testiLuolastoEiYhtenainen[2][0] = true;
		testiLuolastoEiYhtenainen[2][2] = true;
		testiLuolastoEiYhtenainen[3][0] = true;
		testiLuolastoEiYhtenainen[3][2] = true;
		testiLuolastoEiYhtenainen[4][2] = true;
		testiLuolastoEiYhtenainen[4][3] = true;
		testiLuolastoEiYhtenainen[4][4] = true;
	}

	public void luoTestiLuolastoKaksiSuurta() {
		testiLuolastoKaksiSuurta = new boolean[5][5];
		testiLuolastoKaksiSuurta[0][2] = true;
		testiLuolastoKaksiSuurta[1][2] = true;
		testiLuolastoKaksiSuurta[2][2] = true;
		testiLuolastoKaksiSuurta[3][2] = true;
		testiLuolastoKaksiSuurta[4][2] = true;
	}
}
