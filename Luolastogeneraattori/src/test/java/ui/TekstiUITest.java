package ui;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.*;

public class TekstiUITest {
	
	TekstiUI ui;
	
	@Before
    public void setUp() {
        ui = new TekstiUI(new Scanner(System.in));
    }
	
	@Test
	public void kysyyUudelleenJosNegatiivinenSyote() {
		String syote = "-1";
	    System.setIn(new ByteArrayInputStream(syote.getBytes()));
		Assert.assertEquals(ui.kysyKoko(), "Anna luolaston koko: ");
	}

}
