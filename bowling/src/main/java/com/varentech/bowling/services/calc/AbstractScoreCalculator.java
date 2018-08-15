/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling.services.calc;

import com.varentech.bowling.ScoreContext;
import java.util.Objects;

/**
 *
 * @author dcbyron
 */
public abstract class AbstractScoreCalculator implements ScoreCalculator {

    protected ScoreContext scoreContext;
    protected int multiplier;
    
    public AbstractScoreCalculator(ScoreContext scoreContext) {
        ScoreContext context = Objects.requireNonNull(scoreContext);
        this.scoreContext = context;
    }

    public abstract int processScore();
    
    /**
     * When no bonus is due, the calculated value is multiplies by 1.
     * When one or two bonuses are due (i.e., 0 &lt; bonusHorizon &lt; 3), the value is doubled.
     * In cases where bonuses due for adjacent strikes or spares overlap, the multiplier can rise as
     * high as 3.
     */
    protected void preprocessContext() {
        int bonusHorizon = scoreContext.getBonusHorizon();
        multiplier = bonusHorizon == 0 
                      ? 1
                      : (bonusHorizon < 3 
                          ? 2 
                          : 3);
        scoreContext.addRoll();
    }
    
    /**
     * We expend bonuses once their multiplying effect has been used, and we ensure that the sliding
     * window is never wider than 3-- the maximum reach of overlapping bonuses under the rules.
     */
    protected void postprocessContext() {
        if (multiplier >= 2) {
            scoreContext.decreaseBonusHorizon();
        }
        scoreContext.limitBonusHorizon();
    }
    
    /**
     * This is the interface method consumed by clients. All the general prep and cleanup is done
     * by this base class. The concrete children need only implement processScore(), which is not
     * declared in the interface (shielding clients from error) but is enforced by the abstract base.
     * @return the subscore for just this roll
     */
    @Override
    public int calculateScore() {
        int score;
        preprocessContext();
        score = processScore();
        postprocessContext();
        return score;
    }

    /**
     * After ten full frames (20 rolls) have occurred, the multiplier rules change because the final
     * one or two rolls have no independent existence and count only as a bonus. Note that some edge 
     * cases allow for a multiplier of 2 on the penultimate roll. 
     */
    protected void modMultiplierForFrame() {
        int rollsCompleted = scoreContext.getRollsCompleted();
        switch (rollsCompleted) {
            case 24:
            case 23: multiplier = 1; break;
            case 22:
            case 21: multiplier--; break;
            default: break;
        }
    }
    
}
