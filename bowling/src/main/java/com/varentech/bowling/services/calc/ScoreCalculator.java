/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling.services.calc;

/**
 * Although this functional interface would potentially support a streamed rather than iterative
 * strategy in the main class, the statefulness of the algorithm -- with its look-ahead character 
 * and frame-dependent calculations -- make it ineligible for parallelism and awkward for streams.
 * 
 * @author dcbyron
 */
public interface ScoreCalculator {
 
    int calculateScore();
    
}
