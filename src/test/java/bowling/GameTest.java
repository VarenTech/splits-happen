package bowling;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetRollSequence_AllMisses() {
		Game game = new Game("--------------------");
		LinkedList<BowlingFrame> frames = game.getFrames();
		assertEquals(10, frames.size());
		for (BowlingFrame frame : frames) {
			List<Roll> rolls = frame.getRolls();
			assertEquals(2, rolls.size());
			assertEquals(Roll.MISS, rolls.get(0));
			assertEquals(Roll.MISS, rolls.get(1));
		}
	}

	@Test
	public void testSetRollSequence_AllStrikes() {
		Game game = new Game("XXXXXXXXXXXX");
		LinkedList<BowlingFrame> frames = game.getFrames();
		assertEquals(10, frames.size());
		for (int count = 0; count < frames.size() - 1; count++) {
			List<Roll> rolls = frames.get(0).getRolls();
			assertEquals(1, rolls.size());
			assertEquals(Roll.STRIKE, rolls.get(0));
		}
		assertEquals(frames.getLast().getRolls().size(), 3);
		assertEquals(Roll.STRIKE, frames.getLast().getRolls().get(0));
		assertEquals(Roll.STRIKE, frames.getLast().getRolls().get(1));
		assertEquals(Roll.STRIKE, frames.getLast().getRolls().get(2));
	}

	@Test
	public void testSetRollSequence_MaxRolls() {
		Game game = new Game("5/5/5/5/5/5/5/5/5/5/5");
		LinkedList<BowlingFrame> frames = game.getFrames();
		assertEquals(10, frames.size());
		for (int count = 0; count < frames.size() - 1; count++) {
			List<Roll> rolls = frames.get(0).getRolls();
			assertEquals(2, rolls.size());
			assertEquals(Roll.FIVE, rolls.get(0));
			assertEquals(Roll.SPARE, rolls.get(1));
		}
		assertEquals(frames.getLast().getRolls().size(), 3);
		assertEquals(Roll.FIVE, frames.getLast().getRolls().get(0));
		assertEquals(Roll.SPARE, frames.getLast().getRolls().get(1));
		assertEquals(Roll.FIVE, frames.getLast().getRolls().get(2));
	}

	@Test
	public void testSetRollSequence_ProvidedSequence() {
		Game game = new Game("X7/9-X-88/-6XXX81");
		LinkedList<BowlingFrame> frames = game.getFrames();
		assertEquals(10, frames.size());

		List<Roll> rolls_1 = frames.get(0).getRolls();
		assertEquals(1, rolls_1.size());
		assertEquals(Roll.STRIKE, rolls_1.get(0));

		List<Roll> rolls_2 = frames.get(1).getRolls();
		assertEquals(2, rolls_2.size());
		assertEquals(Roll.SEVEN, rolls_2.get(0));
		assertEquals(Roll.SPARE, rolls_2.get(1));

		List<Roll> rolls_3 = frames.get(2).getRolls();
		assertEquals(2, rolls_3.size());
		assertEquals(Roll.NINE, rolls_3.get(0));
		assertEquals(Roll.MISS, rolls_3.get(1));

		List<Roll> rolls_4 = frames.get(3).getRolls();
		assertEquals(1, rolls_4.size());
		assertEquals(Roll.STRIKE, rolls_4.get(0));

		List<Roll> rolls_5 = frames.get(4).getRolls();
		assertEquals(2, rolls_5.size());
		assertEquals(Roll.MISS, rolls_5.get(0));
		assertEquals(Roll.EIGHT, rolls_5.get(1));

		List<Roll> rolls_6 = frames.get(5).getRolls();
		assertEquals(2, rolls_6.size());
		assertEquals(Roll.EIGHT, rolls_6.get(0));
		assertEquals(Roll.SPARE, rolls_6.get(1));

		List<Roll> rolls_7 = frames.get(6).getRolls();
		assertEquals(2, rolls_7.size());
		assertEquals(Roll.MISS, rolls_7.get(0));
		assertEquals(Roll.SIX, rolls_7.get(1));

		List<Roll> rolls_8 = frames.get(7).getRolls();
		assertEquals(1, rolls_8.size());
		assertEquals(Roll.STRIKE, rolls_8.get(0));

		List<Roll> rolls_9 = frames.get(8).getRolls();
		assertEquals(1, rolls_9.size());
		assertEquals(Roll.STRIKE, rolls_9.get(0));

		List<Roll> rolls_10 = frames.get(9).getRolls();
		assertEquals(3, rolls_10.size());
		assertEquals(Roll.STRIKE, rolls_10.get(0));
		assertEquals(Roll.EIGHT, rolls_10.get(1));
		assertEquals(Roll.ONE, rolls_10.get(2));
	}

	@Test
	public void testGetScore_AllMisses() {
		Game game = new Game("--------------------");
		assertEquals(0, game.getScore());
	}

	public void testGetScore_AllMisses_ButLastThreeStrikes() {
		Game game = new Game("------------------XXX");
		assertEquals(30, game.getScore());
	}

	public void testGetScore_AllMisses_ButLastThreeStrikeAndSpare() {
		Game game = new Game("------------------X1/");
		assertEquals(20, game.getScore());
	}

	public void testGetScore_AllMisses_ButLastThreeSpareAndStrike() {
		Game game = new Game("------------------1/X");
		assertEquals(20, game.getScore());
	}

	public void testGetScore_AllMisses_ButLastThreeSpareAnd2() {
		Game game = new Game("------------------1/2");
		assertEquals(12, game.getScore());
	}

	public void testGetScore_AllMisses_ButLastTwo1And2() {
		Game game = new Game("------------------12");
		assertEquals(3, game.getScore());
	}

	public void testGetScore_AllMisses_ButLastThreeSpare1And2() {
		Game game = new Game("-----------------/12");
		assertEquals(14, game.getScore());
	}

	public void testGetScore_AllMisses_ButLastThreeStrike1And2() {
		Game game = new Game("----------------X12");
		assertEquals(16, game.getScore());
	}

	public void testGetScore_All9AndMisses() {
		Game game = new Game("9-9-9-9-9-9-9-9-9-9-");
		assertEquals(90, game.getScore());
	}

	public void testGetScore_ProvidedSequence() {
		Game game = new Game("X7/9-X-88/-6XXX81");
		assertEquals(167, game.getScore());
	}

	@Test
	public void testGetScore_AllStrikes() {
		Game game = new Game("XXXXXXXXXXXX");
		assertEquals(300, game.getScore());
	}

	@Test
	public void testGetScore_AllSparesWith5Bonus() {
		Game game = new Game("5/5/5/5/5/5/5/5/5/5/5");
		assertEquals(105, game.getScore());
	}

	@Test
	public void testGetRollScoreForFrame_STRIKE_NOT_LAST_FRAME() {
		BowlingFrame frame = new BowlingFrame();
		frame.addRoll(Roll.STRIKE);
		assertEquals(10, getRollScoreForFrame(frame));
	}

	@Test
	public void testGetRollScoreForFrame_STRIKE_LAST_FRAME() {
		BowlingFrame frame = new BowlingFrame();
		frame.addRoll(Roll.STRIKE);
		frame.addRoll(Roll.EIGHT);
		frame.addRoll(Roll.ONE);
		assertEquals(10, getRollScoreForFrame(frame));
	}

	@Test
	public void testGetRollScoreForFrame_SPARE_STRIKE_LAST_FRAME() {
		BowlingFrame frame = new BowlingFrame();
		frame.addRoll(Roll.EIGHT);
		frame.addRoll(Roll.SPARE);
		frame.addRoll(Roll.STRIKE);
		assertEquals(10, getRollScoreForFrame(frame));
	}

	@Test
	public void testGetRollScoreForFrame_SPARE() {
		BowlingFrame frame = new BowlingFrame();
		frame.addRoll(Roll.ONE);
		frame.addRoll(Roll.SPARE);
		assertEquals(10, getRollScoreForFrame(frame));
	}

	@Test
	public void testGetRollScoreForFrame_NEITHER_STRIKE_NOR_SPARE() {
		BowlingFrame frame = new BowlingFrame();
		frame.addRoll(Roll.ONE);
		frame.addRoll(Roll.TWO);
		assertEquals(3, getRollScoreForFrame(frame));
	}

	private int getRollScoreForFrame(BowlingFrame frame) {
		int rollScore = 0;
		List<Roll> rolls = frame.getRolls();
		if (rolls.contains(Roll.STRIKE) || rolls.contains(Roll.SPARE))
			rollScore = getScorePerRoll(Roll.STRIKE);
		else
			for (Roll roll : rolls)
				rollScore += getScorePerRoll(roll);
		return rollScore;
	}

	private int getScorePerRoll(Roll roll) {
		switch (roll) {
		case ONE:
			return 1;
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case STRIKE:
		case SPARE:
			return 10;
		case MISS:
			return 0;
		}
		return 0;
	}
}