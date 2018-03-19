package bowling;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BowlingTests {

	@Test
	public void testScoreOf93() {

		Line line = new Line();
		ArrayList<String> myRolls = new ArrayList<String>();

		myRolls.add("1");
		myRolls.add("/");
		myRolls.add("X");
		myRolls.add("2");
		myRolls.add("3");
		myRolls.add("3");
		myRolls.add("/");
		myRolls.add("4");
		myRolls.add("5");
		myRolls.add("7");
		myRolls.add("-");
		myRolls.add("-");
		myRolls.add("-");
		myRolls.add("X");
		myRolls.add("1");
		myRolls.add("1");
		myRolls.add("8");
		myRolls.add("1");

		line.rollAll(myRolls);
		int myScore = line.score();

		assertEquals(myScore, 93);

	}

	@Test
	public void testScoreOf90endsInDash() {

		// 9- 9- 9- 9- 9- 9- 9- 9- 9- 9-

		Line line = new Line();
		ArrayList<String> myRolls = new ArrayList<String>();

		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("9");
		myRolls.add("-");

		line.rollAll(myRolls);
		int myScore = line.score();

		assertEquals(myScore, 90);

	}

	@Test
	public void testScoreOf150endsInNum() {

		// 5 / 5 / 5 / 5 / 5 / 5 / 5 / 5 / 5 / 5 / 5
		// 11x5
		// 10xSlash

		Line line = new Line();
		ArrayList<String> myRolls = new ArrayList<String>();

		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");
		myRolls.add("/");
		myRolls.add("5");

		line.rollAll(myRolls);
		int myScore = line.score();

		assertEquals(myScore, 150);

	}

	@Test
	public void testScoreOf300endsInSpare() {

		Line line = new Line();
		ArrayList<String> myRolls = new ArrayList<String>();

		// X X X X X X X X X X X X
		// 12 strikes
		myRolls.add("X");
		myRolls.add("X");
		myRolls.add("X");

		myRolls.add("X");
		myRolls.add("X");
		myRolls.add("X");

		myRolls.add("X");
		myRolls.add("X");
		myRolls.add("X");

		myRolls.add("X");
		myRolls.add("X");
		myRolls.add("X");

		line.rollAll(myRolls);
		int myScore = line.score();

		assertEquals(myScore, 300);
	}

	@Test
	public void testScoreOf167endsInNum() {

		Line line = new Line();
		ArrayList<String> myRolls = new ArrayList<String>();

		// X7/9-X-88/-6XXX81
		myRolls.add("X");
		myRolls.add("7");
		myRolls.add("/");
		myRolls.add("9");
		myRolls.add("-");
		myRolls.add("X");
		myRolls.add("-");
		myRolls.add("8");
		myRolls.add("8");
		myRolls.add("/");
		myRolls.add("-");
		myRolls.add("6");
		myRolls.add("X");
		myRolls.add("X");
		myRolls.add("X");
		myRolls.add("8");
		myRolls.add("1");

		line.rollAll(myRolls);
		int myScore = line.score();

		assertEquals(myScore, 167);
	}
  
}
