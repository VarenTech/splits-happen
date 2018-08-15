/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling;

/**
 * Although this application includes, and needs, only a default implementation, extracting the 
 * interface is intended as a show of awareness that decoupling, combined for example with injection
 * of alternate implementations, would support a variety of rule adjustments and tweaking.
 * 
 * @author dcbyron
 */
public interface ScoreContext {
    
    public int getBonusHorizon();
    
    public void increaseBonusHorizon();
    
    public void decreaseBonusHorizon();
    
    public void limitBonusHorizon();
    
    public void setPriorValue(int priorValue);
    
    public int getPriorValue();
    
    public void addRoll();

    public int getRollsCompleted();
    
}
