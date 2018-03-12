import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dkward.bowling.analyzers.AnalyzeZeroFrame;
import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.FrameZero;

public class ZeroFrameAnalyzerTest {

	
    private static final String bad_frame = "4-";
    private static final String good_frame = "--";

   AnalyzeZeroFrame zeroFrameParser = null;
   
    @Before
    public void setUp() {
    	  zeroFrameParser = new AnalyzeZeroFrame(); 
    }

    @Test
    public void shouldReturnStrikeFrame() {
        BowlingFrame result = zeroFrameParser.checckTheFrame(good_frame);

        assertTrue(result instanceof FrameZero);
    }

    @Test
    public void shouldReturnNullFrame() {
    	BowlingFrame result = zeroFrameParser.checckTheFrame(bad_frame);

        assertNull(result);
    }
}
