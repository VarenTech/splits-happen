package com.dkward.bowling.frame;

public class FrameStrike implements BowlingFrame{


    private static final int DUPS = 2;
    private static final int strike_score = 10;

	public int getTheFrameScore() {
		// TODO Auto-generated method stub
		return strike_score;
	}

	public int getDupsIfAny() {
		// TODO Auto-generated method stub
		return DUPS;
	}

	public boolean sameAsNextFrame() {
		// TODO Auto-generated method stub
		return true;
	}

}
