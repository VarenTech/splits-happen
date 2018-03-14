package com.dkward.bowling;

import java.util.ArrayList;
import java.util.List;

import com.dkward.bowling.frame.BowlingFrame;

public class StrikesSparesAndMissesGame {
	
	private static final int number_of_frames = 10;

    private List<BowlingFrame> bowlingFrames = new ArrayList<BowlingFrame>();

    
    public int getTheScore() {
    	
        int theScore = 0;
        
        for (int mostCurrentFrame = 0; mostCurrentFrame < number_of_frames; mostCurrentFrame++) {
        	
            BowlingFrame frame = bowlingFrames.get( mostCurrentFrame );
            theScore = bumpTheScore( theScore, frame );
            if ( nextScores ( frame ) ) {
            	theScore = duplicateNextScores( theScore, mostCurrentFrame, frame );
            }
        }
        return theScore;
    }
    
    private int duplicateNextScores(int score, int currentFrame, BowlingFrame frame) {
        final int topNextFrame = currentFrame + 1;
        int nextFrame = topNextFrame;
        while (nextFrame < (topNextFrame + frame.getDupsIfAny())) {
            score = bumpTheScore(score, bowlingFrames.get(nextFrame));
            if (bowlingFrames.get(nextFrame).getDupsIfAny() == 1 && frame.getDupsIfAny() != 1) {
                nextFrame++;
            }
            nextFrame++;
        }
        return score;
    }
    
    
    public void addBowlingFrame(BowlingFrame frame) {
    		bowlingFrames.add(frame);
    }

    private int bumpTheScore(int score, BowlingFrame frame) {
        return score + frame.getTheFrameScore();
    }

    private boolean nextScores(BowlingFrame frame) {
        return frame.sameAsNextFrame() && frame.getDupsIfAny() > 0;
    }

    

}
