/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling.services.calc;

import com.varentech.bowling.ScoreContext;

/**
 * This class encapsulates the algorithm particular to misses.
 *
 * @author dcbyron
 */
public class MissScoreCalculator extends AbstractScoreCalculator {

    private static final int POINTS = 0;

    public MissScoreCalculator(ScoreContext scoreContext) {
        super(scoreContext);
    }

    @Override
    public int processScore() {
        scoreContext.setPriorValue(POINTS);
        return POINTS;
    }
    
}
