package com.dkward.bowling.analyzers;

import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.RegularFrame;

public class AnalyzeRegularFrame implements FrameAnalyzer{

			
	private static final String nothing = "-";

	
	
	public BowlingFrame checckTheFrame(String bowlingFrame) {
		
		// TODO Auto-generated method stub
		BowlingFrame result = null;
        if (isItAValidFrame(bowlingFrame)) {
            int theFirstRoll = getTheScore(bowlingFrame.charAt(0) + "");
            int theSecondRoll = getTheScore(bowlingFrame.charAt(1) + "");
            result = getFrame(theFirstRoll, theSecondRoll);
        }
        return result;
	}
	 
	private boolean isItAValidFrame(String frame) {
	        return frame.length() == 2;
	 }

	private BowlingFrame getFrame(int theFirstRoll, int theSecondRoll) {
	        return new RegularFrame(theFirstRoll, theSecondRoll);
	}

	    
	private int getTheScore(String partialFrame) {
	        int score = 0;
	        if (!nothing.equals(partialFrame)) {
	            score = Integer.parseInt(partialFrame);
	        }
	        return score;
	    }

		
}
