package app.bowling.utility;

/**
 * Class containing constant values used in app
 */
public class Rules {

    /**
     * Constructor
     */
    private Rules() {
        throw new IllegalStateException("Utility class does not need to be instantiated");
    }

    public static final int STRIKE_THROWS_TO_ADD = 2;
    public static final int SPARE_THROWS_TO_ADD = 1;

    public static final byte ALL_PINS_BASE_SCORE = 10;
}
