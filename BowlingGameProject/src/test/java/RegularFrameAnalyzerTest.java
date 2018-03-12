import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dkward.bowling.analyzers.AnalyzeRegularFrame;
import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.RegularFrame;

public class RegularFrameAnalyzerTest {

	    
	    private static final String bad_frame = "X";
	    private static final String good_frame = "81";

	    private AnalyzeRegularFrame normalFrameAnalyzer;

	    @Before
	    public void setUp() {
	    	normalFrameAnalyzer = new AnalyzeRegularFrame();
	    }

	    @Test
	    public void shouldReturnStrikeFrame() {
	        BowlingFrame result = normalFrameAnalyzer.checckTheFrame(good_frame);

	        assertTrue(result instanceof RegularFrame);
	    }

	    @Test
	    public void shouldReturnNullFrame() {
	        BowlingFrame result = normalFrameAnalyzer.checckTheFrame(bad_frame);

	        assertNull(result);
	    }
	}
