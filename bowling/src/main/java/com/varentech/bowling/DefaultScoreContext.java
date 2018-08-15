/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling;

/**
 * Mainly an accumulator for rolls completed, but also a storage place for prior values used in 
 * calculating the spare and for the factor used to deduce the multiplier for bonus points, this
 * class encapsulates the application's mutable state (other than score).
 * 
 * @author dcbyron
 */
public class DefaultScoreContext implements ScoreContext {
    
    private int bonusHorizon;
    private int priorValue;
    private int rollsCompleted;

    public DefaultScoreContext() {
        priorValue = 0;
        bonusHorizon = 0;
        rollsCompleted = 0;
    }
    
    @Override
    public int getBonusHorizon() {
        return bonusHorizon;
    }
    
    @Override
    public void increaseBonusHorizon() {
        bonusHorizon++;
    }
    
    @Override
    public void decreaseBonusHorizon() {
        bonusHorizon--;
    }
    
    @Override
    public void limitBonusHorizon() {
        bonusHorizon = (bonusHorizon > 3) 
                ? 3 
                : bonusHorizon;
    }

    @Override
    public void setPriorValue(int priorValue) {
        this.priorValue = priorValue;
    }
    
    @Override
    public int getPriorValue() {
        return priorValue;
    }

    @Override
    public void addRoll() {
        rollsCompleted++;
    }
    
    @Override
    public int getRollsCompleted() {
        return rollsCompleted;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BonusHorizon = ").append(bonusHorizon).append("\n")
                .append("Prior Value = ").append(priorValue).append("\n")
                .append("Rolls Completed = ").append(rollsCompleted).append("\n");
        return sb.toString();
    }
}