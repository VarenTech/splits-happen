package app.bowling.utility;

import app.bowling.models.Frame;

import java.util.ArrayList;
import java.util.List;

import static app.bowling.utility.Rules.ALL_PINS_BASE_SCORE;

/**
 * Utility class containing logic related to generating Frames
 */
public class FrameGenerator {

    /**
     * Constructor
     */
    private FrameGenerator() {
        throw new IllegalStateException("Utility class does not need to be instantiated");
    }

    /**
     * Generates a list of frames represented by the given string of throws
     * @param strThrows The string showing value for each throw
     * @return List of Frame objects
     */
    public static List<Frame> generateFrames(String strThrows) {
        List<Frame> listFrames = new ArrayList<>();

        char[] arrThrows = strThrows.toUpperCase().toCharArray();
        Frame.FrameBuilder objFrameBuilder = new Frame.FrameBuilder();
        byte bytThrowValue;
        byte bytThrowNumber = 1;
        byte bytLastThrow = 0;
        boolean bIsThreeThrowFrame;
        boolean bFrameComplete = false;

        for (int index = 0; index < arrThrows.length; ++index) {
            // Signifies we are on 10th frame.  Three throws possible.
            bIsThreeThrowFrame = listFrames.size() == 9;

            bytThrowValue = getThrowValue(arrThrows[index], bytLastThrow);

            switch (bytThrowNumber) {
                case 1:
                    objFrameBuilder = objFrameBuilder.withFirstThrow(bytThrowValue);
                    if (bytThrowValue == ALL_PINS_BASE_SCORE && !bIsThreeThrowFrame) {
                        bFrameComplete = true;
                    }
                    break;
                case 2:
                    objFrameBuilder = objFrameBuilder.withSecondThrow(bytThrowValue);
                    if (!(bIsThreeThrowFrame && bytThrowValue + bytLastThrow >= ALL_PINS_BASE_SCORE)) {
                        bFrameComplete = true;
                    }
                    break;
                case 3:
                    objFrameBuilder = objFrameBuilder.withThirdThrow(bytThrowValue);
                    bFrameComplete = true;
                    break;
                default:
                    throw new IllegalStateException("Attempting throw beyond 3rd throw of frame.  This should never occur.");
            }

            if (bFrameComplete) {
                listFrames.add(objFrameBuilder.build());
                objFrameBuilder = new Frame.FrameBuilder();
                bytThrowValue = 0;
                bytThrowNumber = 0;
                bFrameComplete = false;
            }

            ++bytThrowNumber;
            bytLastThrow = bytThrowValue;
        }

        return listFrames;
    }

    /**
     * Gets numerical value for a given throw
     * @param cThrow The char representing the throw value
     * @param bytLastThrow Value fo the last throw (Needed to determine value of a spare
     * @return Byte primitive representing value of throw
     */
    private static byte getThrowValue(char cThrow, byte bytLastThrow) {
        byte bytThrowValue;

        switch (cThrow) {
            case 'X':
                bytThrowValue = ALL_PINS_BASE_SCORE;
                break;
            case '/':
                bytThrowValue = (byte) (ALL_PINS_BASE_SCORE - bytLastThrow);
                break;
            case '-':
                bytThrowValue = (byte) 0;
                break;
            default:
                bytThrowValue = (byte) Character.getNumericValue(cThrow);
                break;
        }

        return bytThrowValue;
    }
}
