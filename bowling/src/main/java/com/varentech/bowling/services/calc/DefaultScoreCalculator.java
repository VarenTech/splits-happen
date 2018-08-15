/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling.services.calc;

import com.varentech.bowling.ScoreContext;

/**
 * This class encapsulates the algorithm particular to non-strike, non-spare, non-miss rolls.
 *
 * @author dcbyron
 */
public class DefaultScoreCalculator extends AbstractScoreCalculator {

    private final int pinsToppled;

    public DefaultScoreCalculator(ScoreContext scoreContext, int pinsToppled) {
        super(scoreContext);
        this.pinsToppled = pinsToppled;
    }

    @Override
    public int processScore() {
        modMultiplierForFrame();
        scoreContext.setPriorValue(pinsToppled);
        return multiplier * pinsToppled;
    }
    
}
