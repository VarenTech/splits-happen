package org.dimattia.splitsHappen;

/**
 * Class for launching the application. Separating this logic from the ScoreCalculator class allows ScoreCalculator to be easily reused.
 * 
 * @author Dustin
 *
 */
public class Launcher {
	/**
	 * The main method.
	 * 
	 * @param args The input string. Should consist of one argument representing a game of bowling.
	 */
	public static void main(String[] args) {
		String gameInput = args[0];
		
		ScoreCalculator calculator = new ScoreCalculator();
		int score = calculator.scoreGame(gameInput);
		
		System.out.println(score);
	}
}
