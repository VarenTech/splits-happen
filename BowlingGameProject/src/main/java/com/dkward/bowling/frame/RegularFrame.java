package com.dkward.bowling.frame;

public class RegularFrame implements BowlingFrame{
	
	private final int firstThrow;
    private final int secondThrow;

    public RegularFrame(int firstThrow, int secondThrow) {
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
    }

	public int getDupsIfAny() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTheFrameScore() {
		
		int score;
		// TODO Auto-generated method stub
		score = firstThrow + secondThrow;
		
		return score;
	}

	public boolean sameAsNextFrame() {
		// TODO Auto-generated method stub
		return false;
	}

    

}
