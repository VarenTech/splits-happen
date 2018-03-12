import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dkward.bowling.analyzers.AnalyzeStrikeFrame;
import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.FrameStrike;

public class StrikeFrameAnalyzerTest {

	private static final String good_frame = "X";
    private static final String bad_frame = "7";

    private AnalyzeStrikeFrame strikeFrameParser;

    @Before
    public void setUp() {
        initializeStrikeFrameParser();
    }

    @Test
    public void shouldReturnStrikeFrame() {
        BowlingFrame result = strikeFrameParser.checckTheFrame(good_frame);

        assertTrue(result instanceof FrameStrike);
    }

    @Test
    public void shouldReturnNullFrame() {
    	BowlingFrame result = strikeFrameParser.checckTheFrame(bad_frame);

        assertNull(result);
    }

    private void initializeStrikeFrameParser() {
        strikeFrameParser = new AnalyzeStrikeFrame();
    }

}
