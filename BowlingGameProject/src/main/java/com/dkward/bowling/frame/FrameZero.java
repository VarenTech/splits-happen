package com.dkward.bowling.frame;

public class FrameZero implements BowlingFrame{
	 
	private static final int zero_frame_score = 0;

	    
	public int getTheScore() {
	    return zero_frame_score;
	}

	    
	public boolean sameAsNextFrame() {
	    return false;
	}

	public int getDupsIfAny() {
	    return 0;
	}


	public int getTheFrameScore() {
	// TODO Auto-generated method stub
		return 0;
	}
}
