package com.dkward.bowling.frame;

public class FrameMiss implements BowlingFrame{
	
	private static final int missScore = 0;

	public int getTheFrameScore() {
		// TODO Auto-generated method stub
		return missScore;
	}

	public int getDupsIfAny() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean sameAsNextFrame() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
