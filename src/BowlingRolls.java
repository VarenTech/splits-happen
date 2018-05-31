import java.util.*;

public class BowlingRolls {

    String gameRolls;
    int[] rollsScore;

    //Bowling Rolls Constructor
    public BowlingRolls(String rolls) {
        //Initializes the BowlingRolls object
        this.gameRolls = rolls;
        this.rollsScore = new int[rolls.length()];
    }

    //This Private Method will convert the character values into the integer values necessary
    private void drawOutValues() {
        //For Loop used to iterate through the array.
        for (int i = 0; i < gameRolls.length(); i++) {
            if (gameRolls.charAt(i) == 'X')
                rollsScore[i] = 10;
            else if (gameRolls.charAt(i) == '/')
                rollsScore[i] = (10 - Character.getNumericValue(gameRolls.charAt(i - 1)));
            else if (gameRolls.charAt(i) == '-')
                rollsScore[i] = 0;
            else
                rollsScore[i] = (Character.getNumericValue(gameRolls.charAt(i)));
        }
    }


    //Score Calculator
    public int getTrueScore() {
        drawOutValues();
        //Raw Score variable will be the actual score
        int rawScore = 0;
        for (int i = 0; i < 10; i++) {
            //a "strike". His turn is over, and his score for the frame is ten plus
            //the simple total of the pins knocked down in his next two rolls.
            if (rollsScore[i] == 10)
                rawScore += 10 + rollsScore[i + 1] + rollsScore[i + 2];

            //a 'spare" and his score for the frame is ten plus the number of pins
                // knocked down on his next throw (in his next turn).
            else if ((rollsScore[i] + rollsScore[i + 1]) == 10) {
                rawScore += 10 + rollsScore[i + 2];
            }
            else {
                rawScore += rollsScore[i] + rollsScore[i + 1];
            }
        }
        return rawScore;
    }

}
