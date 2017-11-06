/**
 * 
 */
package bowling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author suhas
 *
 */
public class BowlingFrameTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link bowling.BowlingFrame#getScore()}.
	 */
	@Test
	public void testAddRoll_ifNoneExists_add1() {
		BowlingFrame frame = new BowlingFrame();
		assertNull(frame.getRolls());
		frame.addRoll(Roll.EIGHT);

		assertEquals(1, frame.getRolls().size());
	}

	/**
	 * Test method for {@link bowling.BowlingFrame#getScore()}.
	 */
	@Test
	public void testAddRoll_Add2Rolls_add2() {
		BowlingFrame frame = new BowlingFrame();
		assertNull(frame.getRolls());
		frame.addRoll(Roll.EIGHT);
		frame.addRoll(Roll.ONE);

		assertEquals(2, frame.getRolls().size());
	}

}
