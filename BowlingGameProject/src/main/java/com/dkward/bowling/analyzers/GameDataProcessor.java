package com.dkward.bowling.analyzers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.dkward.bowling.frame.BowlingFrame;

public class GameDataProcessor {
	

	    public static final char strike_frame = 'X';

	    private final Collection<FrameAnalyzer> parsers;
	    private int parsingIndex;

	    public GameDataProcessor(List<FrameAnalyzer> parsers) {
	        this.parsers = parsers;
	    }

	    
	    public List<BowlingFrame> getTheInputData(final String line) {
	        List<BowlingFrame> frames = new LinkedList<BowlingFrame>();
	        for (parsingIndex = 0; parsingIndex < line.length(); parsingIndex++) {
	        	BowlingFrame frame = getTheBowlingFrame(line);
	            frames.add(frame);
	        }
	        return frames;
	    }
	    
	    
	    private BowlingFrame getTheBowlingFrame(String line) {
	        String frameToAnalyze;
	        if (isOneCharFrame(line, parsingIndex)) {
	            frameToAnalyze = line.charAt(parsingIndex) + "";
	        } else {
	            frameToAnalyze = line.substring(parsingIndex, parsingIndex + 2);
	            parsingIndex++;
	        }
	        return getTheFrame(frameToAnalyze);
	    }


	   

	    private boolean isOneCharFrame(String line, int i) {
	        return line.charAt(i) == strike_frame;
	    }

	   
	    private BowlingFrame getTheFrame(String frameToAnalyze) {
	    	BowlingFrame result = null;
	        for (FrameAnalyzer frameParser : parsers) {
	        	BowlingFrame frame = frameParser.checckTheFrame(frameToAnalyze);
	            if (frame != null) {
	                result = frame;
	                break;
	            }
	        }
	        return result;
	    }

	}
