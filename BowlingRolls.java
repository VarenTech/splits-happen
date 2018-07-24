package sample;

import java.util.*;

public class BowlingRolls {
    String gameRolls;
    int[] rollsScore;
//    int frame;

    //Bowling Rolls Constructor
    public BowlingRolls(String rolls) {
        //Initializes the BowlingRolls object
        this.gameRolls = rolls;
        this.rollsScore = new int[rolls.length()];
//        this.frame = 1;
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
        System.out.println("\nValues entered convert to:");
        printArray(rollsScore);
        System.out.println();
        System.out.print("Score: \n");
    }


    //Score Calculator
    public int getTrueScore() {
        drawOutValues();
        //Raw Score variable will be the actual score
        int rawScore = 0;
        int numFrames = 10;
        int currentFrame = 1;
        int currentIndex = 0;

        while (currentFrame <= numFrames) {
            //a "strike". His turn is over, and his score for the frame is ten plus
            //the simple total of the pins knocked down in his next two rolls.
            if (rollsScore[currentIndex] == 10) {
                rawScore += 10 + rollsScore[currentIndex + 1] + rollsScore[currentIndex + 2];
                currentFrame++;
                currentIndex++;

            } else {
                //a 'spare" and his score for the frame is ten plus the number of pins

                // knocked down on his next throw (in his next turn).
                if ((rollsScore[currentIndex] + rollsScore[currentIndex + 1]) == 10) {
                    rawScore += 10 + rollsScore[currentIndex + 2];

                } else {
                    rawScore += rollsScore[currentIndex] + rollsScore[currentIndex + 1];

                }
                currentFrame++;
                currentIndex += 2;
            }

        }
        return rawScore;
    }


    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}