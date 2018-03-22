package org.dimattia.splitsHappen;

public class Launcher {
	public static void main(String[] args) {
		String gameInput = args[0];
		
		ScoreCalculator calculator = new ScoreCalculator();
		int score = calculator.scoreGame(gameInput);
		
		System.out.println(score);
	}
}
