package me.mcnamara.sean.bowling.tests;

import org.junit.Before;
import org.junit.Test;

import me.mcnamara.sean.bowling.Game;

public class GameTest {

	private Game g;
	
	@Before
	public void setUp() {
		g = new Game();
	}
	
	@Test
	public void gutterGame() {
		for(int i = 0; i < 10; i++) {
			g.roll(0);
		}
	}
}
