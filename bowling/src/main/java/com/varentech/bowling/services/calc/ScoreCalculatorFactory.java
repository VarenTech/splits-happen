/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling.services.calc;

import com.varentech.bowling.ScoreContext;

/**
 * Decoupling on the cheap. What's Java without factory methods? 
 * 
 * @author dcbyron
 */
public class ScoreCalculatorFactory {

    private static final char STRIKE = 'X';
    private static final char SPARE = '/';
    private static final char MISS = '-';

    private ScoreCalculatorFactory() {
        // No instantiation
    }
    
    public static ScoreCalculator createCalculator(ScoreContext context, char c) {
        switch(c) {
            case STRIKE: return new StrikeScoreCalculator(context);
            case SPARE: return new SpareScoreCalculator(context);
            case MISS: return new MissScoreCalculator(context);
            default:
                int pinsToppled = Character.getNumericValue(c);
                return new DefaultScoreCalculator(context, pinsToppled);
        }
    }
    
}
