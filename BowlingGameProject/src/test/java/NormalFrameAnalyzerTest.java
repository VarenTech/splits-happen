import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dkward.bowling.analyzers.AnalyzeRegularFrame;
import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.RegularFrame;

public class NormalFrameAnalyzerTest {

	 	
	    private static final String bad_frame = "X";
	    private static final String good_frame = "81";

	    private AnalyzeRegularFrame regularFrameAnalyzer = null;

	    @Before
	    public void setUp() {
	    	regularFrameAnalyzer = new AnalyzeRegularFrame();
	    }

	    @Test
	    public void shouldReturnStrikeFrame() {
	        BowlingFrame result = regularFrameAnalyzer.checckTheFrame(good_frame);

	        assertTrue(result instanceof RegularFrame);
	    }

	    @Test
	    public void shouldReturnNullFrame() {
	        BowlingFrame result = regularFrameAnalyzer.checckTheFrame(bad_frame);

	        assertNull(result);
	    }
}
