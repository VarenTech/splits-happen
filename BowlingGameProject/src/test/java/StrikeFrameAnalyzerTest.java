import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dkward.bowling.analyzers.AnalyzeStrikeFrame;
import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.FrameStrike;

public class StrikeFrameAnalyzerTest {

	private static final String VALID_STRIKE_FRAME = "X";
    private static final String INVALID_STRIKE_FRAME = "7";

    private AnalyzeStrikeFrame strikeFrameParser;

    @Before
    public void setUp() {
        initializeStrikeFrameParser();
    }

    @Test
    public void shouldReturnStrikeFrame() {
        BowlingFrame result = strikeFrameParser.checckTheFrame(VALID_STRIKE_FRAME);

        assertTrue(result instanceof FrameStrike);
    }

    @Test
    public void shouldReturnNullFrame() {
    	BowlingFrame result = strikeFrameParser.checckTheFrame(INVALID_STRIKE_FRAME);

        assertNull(result);
    }

    private void initializeStrikeFrameParser() {
        strikeFrameParser = new AnalyzeStrikeFrame();
    }

}
