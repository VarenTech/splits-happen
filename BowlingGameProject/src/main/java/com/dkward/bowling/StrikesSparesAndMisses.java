package com.dkward.bowling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.dkward.bowling.analyzers.AnalyzeRegularFrame;
import com.dkward.bowling.analyzers.AnalyzeSpareFrame;
import com.dkward.bowling.analyzers.AnalyzeStrikeFrame;
import com.dkward.bowling.analyzers.AnalyzeZeroFrame;
import com.dkward.bowling.analyzers.FrameAnalyzer;
import com.dkward.bowling.analyzers.GameDataProcessor;
import com.dkward.bowling.frame.BowlingFrame;

public class StrikesSparesAndMisses {
	
	
	private GameDataProcessor dataProcessor = null;
	
	public StrikesSparesAndMisses() {
		
		startToReadTheData();
	}
		
	
	public int getScore(String rolls) {
        List<BowlingFrame> frames = dataProcessor.getTheInputData(rolls);
        return getScore(frames);
    }
	
	private void startToReadTheData() {
        List<FrameAnalyzer> analyzerList = new ArrayList<FrameAnalyzer>();
        analyzerList.add(new AnalyzeStrikeFrame());
        analyzerList.add(new AnalyzeSpareFrame());
        analyzerList.add(new AnalyzeRegularFrame());
        analyzerList.add(new AnalyzeZeroFrame());
        dataProcessor = new GameDataProcessor(analyzerList);
    }
	
	 
	private int getScore(final List<BowlingFrame> frames) {
		    StrikesSparesAndMissesGame bowlingGame = createBowlingGame(frames);
		        
		    return bowlingGame.getTheScore();
	}
	
	private StrikesSparesAndMissesGame createBowlingGame(List<BowlingFrame> frames) {
		StrikesSparesAndMissesGame bowlingGame = new StrikesSparesAndMissesGame();
        
		for (BowlingFrame frame : frames) {
            bowlingGame.addBowlingFrame(frame);
        }
        return bowlingGame;
	}
	
}
	