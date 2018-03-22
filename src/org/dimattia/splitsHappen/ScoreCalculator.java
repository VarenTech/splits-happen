package org.dimattia.splitsHappen;

public class ScoreCalculator {
	private Frame firstFrame;
	
	public int scoreGame(String input) {
		firstFrame = generateFrame(input, 1);
		return calculateScore(firstFrame);
	}
	
	private int calculateScore(Frame frame) {
		int score = 0;
		while(frame != null) {
			score += frame.sumFrame();
			frame = frame.nextFrame();
		}
		return score;
	}
	
	private Frame generateFrame(String input, int frameNum) {
		Frame frame;
		
		if(frameNum == 10) {
			frame = new FinalFrame(input, frameNum, null);
		} else {
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
