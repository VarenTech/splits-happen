package com.dkward.bowling.analyzers;

import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.FrameZero;

public class AnalyzeZeroFrame implements FrameAnalyzer{
	
	 private static final String zero = "--";

	    
	    public BowlingFrame checckTheFrame(String frame) {
	        BowlingFrame result = null;
	        if (isItAValidFrame(frame)) {
	            result = new FrameZero();
	        }
	        return result;
	    }

	    private boolean isItAValidFrame(String frame) {
	        return zero.equals(frame);
	    }
	

}
