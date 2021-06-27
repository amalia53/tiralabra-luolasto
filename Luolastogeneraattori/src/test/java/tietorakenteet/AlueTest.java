package tietorakenteet;

import org.junit.*;

public class AlueTest {
	
	Alue juuri;
	
	@Before
	public void SetUp() {
		juuri = new Alue(0,0,10,10);
	}
	
//	@Test
//	public void jaaAlueJakaaAlueenPienemmiksiAlueiksiJosAlueTarpeeksiSuuri() {
//		Assert.assertEquals(juuri.getVasen(), null);
//		Assert.assertEquals(juuri.getOikea(), null);
//		juuri.jaaAlue();
//		boolean luotu = juuri.getVasen() != null;
//		Assert.assertEquals(luotu, true);
//		luotu = juuri.getOikea() != null;
//		Assert.assertEquals(luotu, true);
//	}
//	
//	@Test
//	public void jaaAlueJEiJaaAluettaPienemmiksiAlueiksiJosAlueLiianPieni() {
//		juuri = new Alue(0,0,5,5);
//		Assert.assertEquals(juuri.getVasen(), null);
//		Assert.assertEquals(juuri.getOikea(), null);
//		juuri.jaaAlue();
//		boolean luotu = juuri.getVasen() != null;
//		Assert.assertEquals(luotu, false);
//		luotu = juuri.getOikea() != null;
//		Assert.assertEquals(luotu, false);
//	}
//	
//	@Test
//	public void jakaaAlueenVaakatasossaJosEiVoiPystytasossa() {
//		juuri = new Alue(0,0,5,8);
//		juuri.jaaAlue();
//		Assert.assertEquals(juuri.getOikea().getY(), juuri.getY());
//		Assert.assertNotEquals(juuri.getOikea().getX(), juuri.getX());
//	}
//	
//	@Test
//	public void jakaaAlueenPystytasossaJosEiVoiVaakatasossa() {
//		juuri = new Alue(0,0,10,4);
//		juuri.jaaAlue();
//		Assert.assertNotEquals(juuri.getOikea().getY(), juuri.getY());
//		Assert.assertEquals(juuri.getOikea().getX(), juuri.getX());
//	}

}
