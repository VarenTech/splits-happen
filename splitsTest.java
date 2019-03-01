import static org.junit.Assert.*;

import org.junit.Test;


public class splitsTest {

	@Test
	public void testStrikes() {
		assertTrue(splits.calculate("XXXXXXXXXXXX")== 300);
	}
	
	@Test
	public void testMisses() {
		assertTrue(splits.calculate("9-9-9-9-9-9-9-9-9-9-") == 90);
	}

	@Test
	public void testSpares() {
		assertTrue(splits.calculate("5/5/5/5/5/5/5/5/5/5/5") == 150);
	}
	
	@Test
	public void testGame1() {
		assertTrue(splits.calculate("X7/9-X-88/-6XXX81") == 167);
	}

}
