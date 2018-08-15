/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling.services.calc;

import com.varentech.bowling.ScoreContext;

/**
 * This class encapsulates the algorithm particular to strikes.
 *
 * @author dcbyron
 */
public class StrikeScoreCalculator extends AbstractScoreCalculator {

    private static final int POINTS = 10;
    
    public StrikeScoreCalculator(ScoreContext scoreContext) {
        super(scoreContext);
    }

    @Override
    public int processScore() {
        scoreContext.increaseBonusHorizon();
        scoreContext.increaseBonusHorizon();
        scoreContext.addRoll();
        modMultiplierForFrame();
        return multiplier * POINTS;
    }
    
}
