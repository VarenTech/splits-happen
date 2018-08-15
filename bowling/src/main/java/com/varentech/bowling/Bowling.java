/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling;

import com.varentech.bowling.services.calc.ScoreCalculator;
import com.varentech.bowling.services.calc.ScoreCalculatorFactory;
import com.varentech.bowling.view.DisplayRouter;

/**
 * The Bowling class consumes a String representing bowling rolls in scorecard notation and 
 * generates a total score. In keeping with the instructions, the class performs no validation on
 * the input, whether for legal characters or frame/game completeness. Likewise, the intermediate
 * or running score is not shown.
 * 
 * The class is programmed to interfaces -- ScoreContext and ScoreCalculator -- for which the 
 * implementations cover the required cases. A value in the context object tracks and adjusts the
 * doubling and tripling of future frames on account of bonuses (which may stack up to a point).
 * 
 * The class writes to stdout or the console via a simple pass-through router in its own view
 * package. While fairly redundant for a toy app, this organization is a nod to the separation of 
 * concerns that would ordinarily motivate the structure of an MVC application addressing multiple
 * presentation technologies.
 * 
 * @author dcbyron
 */
public class Bowling {
    
    private static final String INSTRUCTIONS = "Please provide a sequence of rolls!";
    
    /**
     * The Bowling application's entry point.
     * @param args A sequence of scorecard values
     */
    public static void main(String[] args) {
        Bowling bowling = new Bowling();
        if (args.length == 0) {
            DisplayRouter.writeToConsole(INSTRUCTIONS);
        } else {
            DisplayRouter.writeToConsole(bowling.playGame(args));
        }
    }

    /**
     * This fairly abstract machine relies on the concrete implementations of ScoreCalculator and,
     * especially, their abstract base class, to do the case-specific heavy lifting. Strategy
     * pattern and polymorphic dispatch give this method its power and simplicity.
     * 
     * @param args A sequence of scorecard values
     * @return A string representation of the final score
     */
    protected String playGame(String[] args) {
        int score = 0;
        char[] scoreCardEntries = String.join("", args).toCharArray();
        ScoreContext context = new DefaultScoreContext();
        for (char c : scoreCardEntries) {
            ScoreCalculator calculator = ScoreCalculatorFactory.createCalculator(context, c);
            score += calculator.calculateScore();
        }
        return Integer.toString(score);
    }
    
}
