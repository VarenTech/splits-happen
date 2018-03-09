public class Game {

    //the maximum number of rolls per game
    private int rolls[] = new int[21];
    //index that
    private int currentRoll = 0;

    //default constructor used for unit tests
    Game() {}

    /**
     * Constructor that initializes the instance variable rolls
     * @param scores an array of int used for the score of the
     */
    Game(int[] scores) {
        rolls = scores;
    }

    /**
     * Changes the value of the current role to how many pins were knocked down
     * @param pins how many pins were knocked down
     */
    public void roll(int pins){
        rolls[currentRoll++] = pins;
    }

    /**
     * Loops through the number of frames in a line and calculates the score of the game
     * @return an int representing the score of the game
     */
    public int score() {
        int score = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++){
            if (isStrike(frameIndex)) {
                //add the strike bonus and 10 additional pins
                score += 10 + strikeBonus(frameIndex);
                //only one ball was thrown for strike
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                //add the spare bonus and 10 additional pins
                score += 10 + spareBonus(frameIndex);
                //two balls were thrown for spare
                frameIndex += 2;
            } else {
                //add the number of pins knocked down in two throws
                score += sumOfBallsInFrame(frameIndex);
                //two balls were thrown in this turn
                frameIndex += 2;
            }
        }
        return score;
    }

    /**
     * How many pins were knocked down in this frame
     * @param frameIndex the state of the game
     * @return number of pins knocked down after two throws
     */
    private int sumOfBallsInFrame(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex+1];
    }

    /**
     * Checks to see if a spare was thrown in this frame
     * @param frameIndex the state of the game
     * @return boolean if spare was rolled
     */
    private boolean isSpare(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex+1] == 10;
    }

    /**
     * Checks to see if a strike was thrown in this frame
     * @param frameIndex the state of the game
     * @return boolean if strike was rolled
     */
    private boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == 10;
    }

    /**
     * Adds the spare bonus of the next frame
     * @param frameIndex the state of the game
     * @return the number of pins knocked down in the next frame
     */
    private int spareBonus(int frameIndex) {
        return rolls[frameIndex + 2];
    }

    /**
     * Adds the strike bonus of the next two frames
     * @param frameIndex the state of the game
     * @return the number of pins knocked down in the next two frames
     */
    private int strikeBonus(int frameIndex) {
        return rolls[frameIndex+1] + rolls[frameIndex+2];
    }
}
