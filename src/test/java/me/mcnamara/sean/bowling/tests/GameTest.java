package me.mcnamara.sean.bowling.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import me.mcnamara.sean.bowling.Game;

public class GameTest {

	private Game g;
	
	@Before
	public void setUp() {
		g = new Game();
	}
	
	//Required Tests
	
	@Test
	public void allFramesNineScoreShouldBe90() {
		g.setBowlingLine("9-9-9-9-9-9-9-9-9-9-");
		assertEquals(90, g.score());
	}
	
	@Test
	public void allSparesScoreShouldBe150() {
		g.setBowlingLine("5/5/5/5/5/5/5/5/5/5/5");
		assertEquals(150, g.score());
	}
	
	@Test
	public void perfectGameShouldBe300() {
		g.setBowlingLine("XXXXXXXXXXXX");
		assertEquals(300, g.score());
	}
	
	@Test
	public void acceptanceTestShouldBe167() {
		g.setBowlingLine("X7/9-X-88/-6XXX81");
		assertEquals(167, g.score());
	}
	
	//"Warm Fuzzy" Tests I felt would be interesting
	
	@Test
	public void gutterGame() {
		g.setBowlingLine("--------------------");
		assertEquals(0, g.score());
	}
	
	@Test
	public void allOnesShouldBe20() {
		g.setBowlingLine("11111111111111111111");
		assertEquals(20, g.score());
	}
	
	@Test
	public void allSpareTensShouldBe110() {
		g.setBowlingLine("-/-/-/-/-/-/-/-/-/-/");
		assertEquals(110, g.score());
	}
}
