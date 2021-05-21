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

}
