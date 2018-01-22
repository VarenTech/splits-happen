package app.bowling.utility;

import app.bowling.models.Frame;

import java.util.List;

/**
 * Utility class containing logic related to calculating score
 */
public class ScoreCalculator {
    /**
     * Constructor
     */
    private ScoreCalculator() {
        throw new IllegalStateException("Utility class does not need to be instantiated");
    }

    /**
     * Retrieves the score for the given frames of a game
     * @param listFrames The frames of the game
     * @return A byte value representing the score of the game
     */
    public static short getScore(List<Frame> listFrames) {

        short sScore = 0;

        for (int index = listFrames.size() - 1; index > -1; --index) {
            sScore += getFrameScore(listFrames, index);
        }

        return sScore;
    }

    /**
     * Retrieves the score for a given frame in the frame list
     * @param listFrames The frames of the game
     * @param iIndex The frame to get a score for
     * @return A byte value representing the score of the given frame
     */
    private static byte getFrameScore(List<Frame> listFrames, int iIndex) {
        Frame objFrame = listFrames.get(iIndex);

        byte bFrameScore = objFrame.getFirstThrow();

        // strike condition
        if (bFrameScore == Rules.ALL_PINS_BASE_SCORE && listFrames.size() > iIndex + 1) {
            Frame objNextFrame = listFrames.get(iIndex + 1);
            bFrameScore += objNextFrame.getFirstThrow();
            if (objNextFrame.getSecondThrow().isPresent()) {
                bFrameScore += objNextFrame.getSecondThrow().get(); // NOSONAR - Incorrectly thinks isPresent is not being called first
            } else if (listFrames.size() > iIndex + 2) {
                bFrameScore += listFrames.get(iIndex + 2).getFirstThrow();
            }
        }

        bFrameScore += objFrame.getSecondThrow().orElse((byte) 0);

        // spare condition
        if (bFrameScore == Rules.ALL_PINS_BASE_SCORE && listFrames.size() > iIndex + 1) {
            bFrameScore += listFrames.get(iIndex + 1).getFirstThrow();
        }

        bFrameScore += objFrame.getThirdThrow().orElse((byte) 0);

        return bFrameScore;
    }

    /**
     * Retrieves score for spacified number of throws after the start frame.
     * If that number of throws does not exist after the start frame, a 0 value is returned for the throw(s)
     * @param listFrames The frames of the game
     * @param iStartIndex The index of the frame to get throw values for
     * @param iThrowsRemaining The number of throws to get scores for.
     * @return A byte value representing the score for the throws requested.
     */
    private static byte getNextThrowsTotal(List<Frame> listFrames, int iStartIndex, int iThrowsRemaining) {
        byte bTotal = 0;

        if (iStartIndex < listFrames.size()) {
            Frame objFrame = listFrames.get(iStartIndex);

            bTotal += objFrame.getFirstThrow();

            // still need another throw's value
            if (iThrowsRemaining > 1) {
                bTotal += objFrame.getSecondThrow().orElse(
                        getNextThrowsTotal(listFrames, iStartIndex + 1, iThrowsRemaining - 1));
            }
        }

        return bTotal;
    }
}
