package org.dimattia.splitsHappen;

/**
 * Class for calculating the score of a game of bowling.
 * Parsed the input string into 10 frames, then sums the score of each frame.
 * @author Dustin
 *
 */
public class ScoreCalculator {
	
	/**
	 * Scores a game of bowling.
	 * @param input A String representing a game of bowling.
	 * @return An integer representing the score of the game.
	 */
	public int scoreGame(String input) {
		// The first frame of the game.
		Frame frame = generateFrame(input, 1);
		
		int score = 0;
		// Advances through the frames, adding their sums to the total score.
		while(frame != null) {
			score += frame.sumFrame();
			frame = frame.nextFrame();
		}
		return score;
	}
	
	/**
	 * Recursively parses the input String into 10 frames.
	 * @param input A String representing a part of a game of bowling.
	 * @param frameNum The number of the frame in the game, 1-10.
	 * @return A Frame
	 */
	private Frame generateFrame(String input, int frameNum) {
		Frame frame;
		
		if(frameNum == 10) {
			// Final frames calculate score slightly differently.
			frame = new FinalFrame(input, frameNum, null);
		} else {
			//A frame with a strike will only pull off one character of input. All other frames take two characters.
			if(input.charAt(0) == 'X') {
				Frame nextFrame = generateFrame(input.substring(1), frameNum+1);
				frame = new NormalFrame(input.substring(0,1), frameNum, nextFrame);
			} else {
				Frame nextFrame = generateFrame(input.substring(2), frameNum+1);
				frame = new NormalFrame(input.substring(0,2), frameNum, nextFrame);
			}
		}
		
		return frame;
	}
}
