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
	
	@Test
	public void gutterGame() {
		g.setBowlingLine("----------");
		
		assertEquals(g.score(), 0);
	}
	
	@Test
	public void allFramesNineScoreShouldBe90() {
		g.setBowlingLine("9-9-9-9-9-9-9-9-9-9-");
		assertEquals(g.score(), 90);
	}
	
	@Test
	public void allSparesScoreShouldBe150() {
		g.setBowlingLine("5/5/5/5/5/5/5/5/5/5/5");
		assertEquals(g.score(), 150);
	}
}
