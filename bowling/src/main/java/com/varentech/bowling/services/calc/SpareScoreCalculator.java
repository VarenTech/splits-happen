/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling.services.calc;

import com.varentech.bowling.ScoreContext;

/**
 * This class encapsulates the algorithm particular to spares.
 * 
 * @author dcbyron
 */
public class SpareScoreCalculator extends AbstractScoreCalculator {

    public SpareScoreCalculator(ScoreContext scoreContext) {
        super(scoreContext);
    }

    @Override
    public int processScore() {
        scoreContext.increaseBonusHorizon();
        int priorValue = scoreContext.getPriorValue();
        return multiplier * (10 - priorValue);
    }
    
}
