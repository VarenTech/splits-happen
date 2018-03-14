package com.dkward.bowling.analyzers;

import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.FrameStrike;

public class AnalyzeStrikeFrame implements FrameAnalyzer{

	
	private static final String a_strike = "X";
	
	public BowlingFrame checckTheFrame(String frame) {
		BowlingFrame result = null;
        if (a_strike.equals(frame)) {
            result = new FrameStrike();
        }
        return result;
    }
}
