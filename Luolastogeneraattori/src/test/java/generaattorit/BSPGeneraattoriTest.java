package generaattorit;

import org.junit.*;

import tietorakenteet.Alue;
import tietorakenteet.Koordinantti;

public class BSPGeneraattoriTest {

	BSPGeneraattori gen;
	boolean[][] testiLuolasto;
	boolean[][] testiLuolastoEiYhtenainen;
	boolean[][] testiLuolastoKaksiSuurta;

	@Before
	public void setUp() {
		gen = new BSPGeneraattori();
	}

	@Test
	public void alustaLuolastoLuoPuun() {
		Alue juuri = new Alue(0, 0, 30, 30);
		boolean jaettu = juuri.getVasen() != null;
		Assert.assertEquals(jaettu, false);
		gen.alustaLuolasto(juuri);
		jaettu = juuri.getVasen() != null;
		Assert.assertEquals(jaettu, true);
		jaettu = juuri.getOikea() != null;
		Assert.assertEquals(jaettu, true);

	}


	public void luoTestiLuolasto() {
		testiLuolasto = new boolean[30][30];
		testiLuolasto[10][0] = true;
		testiLuolasto[10][1] = true;
		testiLuolasto[10][2] = true;
		testiLuolasto[10][3] = true;
		testiLuolasto[10][4] = true;
		testiLuolasto[10][5] = true;
		testiLuolasto[10][6] = true;
		testiLuolasto[10][7] = true;
		testiLuolasto[10][8] = true;
		testiLuolasto[10][9] = true;
		testiLuolasto[10][10] = true;
		testiLuolasto[10][11] = true;
		testiLuolasto[10][12] = true;
		testiLuolasto[10][13] = true;
		testiLuolasto[10][14] = true;
		testiLuolasto[10][15] = true;
		testiLuolasto[10][16] = true;
		testiLuolasto[10][17] = true;
		testiLuolasto[10][18] = true;
		testiLuolasto[10][19] = true;
		testiLuolasto[10][20] = true;
		testiLuolasto[10][22] = true;
		testiLuolasto[10][23] = true;
		testiLuolasto[10][24] = true;
		testiLuolasto[10][25] = true;
		testiLuolasto[10][26] = true;
		testiLuolasto[10][27] = true;
		testiLuolasto[10][28] = true;
		testiLuolasto[10][29] = true;
		testiLuolasto[25][0] = true;
		testiLuolasto[25][1] = true;
		testiLuolasto[25][2] = true;
		testiLuolasto[25][3] = true;
		testiLuolasto[25][4] = true;
		testiLuolasto[25][5] = true;
		testiLuolasto[25][6] = true;
		testiLuolasto[25][7] = true;
		testiLuolasto[25][8] = true;
		testiLuolasto[25][9] = true;
		testiLuolasto[25][10] = true;
		testiLuolasto[25][11] = true;
		testiLuolasto[25][12] = true;
		testiLuolasto[25][13] = true;
		testiLuolasto[25][14] = true;
		testiLuolasto[25][15] = true;
		testiLuolasto[25][16] = true;
		testiLuolasto[25][18] = true;
		testiLuolasto[25][19] = true;
		testiLuolasto[25][20] = true;
		testiLuolasto[25][21] = true;
		testiLuolasto[25][22] = true;
		testiLuolasto[25][23] = true;
		testiLuolasto[25][24] = true;
		testiLuolasto[25][25] = true;
		testiLuolasto[25][26] = true;
		testiLuolasto[25][27] = true;
		testiLuolasto[25][28] = true;
		testiLuolasto[25][29] = true;	
	}

	public void luoTestiLuolastoEiYhtenainen() {
		testiLuolastoEiYhtenainen = new boolean[30][30];
		testiLuolastoEiYhtenainen[10][0] = true;
		testiLuolastoEiYhtenainen[10][1] = true;
		testiLuolastoEiYhtenainen[10][2] = true;
		testiLuolastoEiYhtenainen[10][3] = true;
		testiLuolastoEiYhtenainen[10][4] = true;
		testiLuolastoEiYhtenainen[10][5] = true;
		testiLuolastoEiYhtenainen[10][6] = true;
		testiLuolastoEiYhtenainen[10][7] = true;
		testiLuolastoEiYhtenainen[10][8] = true;
		testiLuolastoEiYhtenainen[10][9] = true;
		testiLuolastoEiYhtenainen[10][10] = true;
		testiLuolastoEiYhtenainen[10][11] = true;
		testiLuolastoEiYhtenainen[10][12] = true;
		testiLuolastoEiYhtenainen[10][13] = true;
		testiLuolastoEiYhtenainen[10][14] = true;
		testiLuolastoEiYhtenainen[10][15] = true;
		testiLuolastoEiYhtenainen[10][16] = true;
		testiLuolastoEiYhtenainen[10][17] = true;
		testiLuolastoEiYhtenainen[10][18] = true;
		testiLuolastoEiYhtenainen[10][19] = true;
		testiLuolastoEiYhtenainen[10][20] = true;
		testiLuolastoEiYhtenainen[10][21] = true;
		testiLuolastoEiYhtenainen[10][22] = true;
		testiLuolastoEiYhtenainen[10][23] = true;
		testiLuolastoEiYhtenainen[10][24] = true;
		testiLuolastoEiYhtenainen[10][25] = true;
		testiLuolastoEiYhtenainen[10][26] = true;
		testiLuolastoEiYhtenainen[10][27] = true;
		testiLuolastoEiYhtenainen[10][28] = true;
		testiLuolastoEiYhtenainen[10][29] = true;
		testiLuolastoEiYhtenainen[25][0] = true;
		testiLuolastoEiYhtenainen[25][1] = true;
		testiLuolastoEiYhtenainen[25][2] = true;
		testiLuolastoEiYhtenainen[25][3] = true;
		testiLuolastoEiYhtenainen[25][4] = true;
		testiLuolastoEiYhtenainen[25][5] = true;
		testiLuolastoEiYhtenainen[25][6] = true;
		testiLuolastoEiYhtenainen[25][7] = true;
		testiLuolastoEiYhtenainen[25][8] = true;
		testiLuolastoEiYhtenainen[25][9] = true;
		testiLuolastoEiYhtenainen[25][10] = true;
		testiLuolastoEiYhtenainen[25][11] = true;
		testiLuolastoEiYhtenainen[25][12] = true;
		testiLuolastoEiYhtenainen[25][13] = true;
		testiLuolastoEiYhtenainen[25][14] = true;
		testiLuolastoEiYhtenainen[25][15] = true;
		testiLuolastoEiYhtenainen[25][16] = true;
		testiLuolastoEiYhtenainen[25][18] = true;
		testiLuolastoEiYhtenainen[25][19] = true;
		testiLuolastoEiYhtenainen[25][20] = true;
		testiLuolastoEiYhtenainen[25][21] = true;
		testiLuolastoEiYhtenainen[25][22] = true;
		testiLuolastoEiYhtenainen[25][23] = true;
		testiLuolastoEiYhtenainen[25][24] = true;
		testiLuolastoEiYhtenainen[25][25] = true;
		testiLuolastoEiYhtenainen[25][26] = true;
		testiLuolastoEiYhtenainen[25][27] = true;
		testiLuolastoEiYhtenainen[25][28] = true;
		testiLuolastoEiYhtenainen[25][29] = true;
		
	}

}
