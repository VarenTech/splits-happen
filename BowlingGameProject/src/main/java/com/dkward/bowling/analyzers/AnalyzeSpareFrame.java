package com.dkward.bowling.analyzers;

import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.FrameSpare;

public class AnalyzeSpareFrame implements FrameAnalyzer{

	private static final String a_spare = "/";


    private boolean isValidFrame(String frame) {
        return a_spare.equals(frame.charAt(1) + "");
    }

	public BowlingFrame checckTheFrame(String bowlingFrame) {
		
		// TODO Auto-generated method stub
		BowlingFrame result = null;
        if (isValidFrame(bowlingFrame)) {
            result = new FrameSpare();
        }
        return result;
	}

}
