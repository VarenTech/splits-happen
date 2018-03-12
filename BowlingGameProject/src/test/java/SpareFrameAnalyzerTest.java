import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dkward.bowling.analyzers.AnalyzeSpareFrame;
import com.dkward.bowling.frame.BowlingFrame;
import com.dkward.bowling.frame.FrameSpare;

public class SpareFrameAnalyzerTest {

	
	    private static final String VALID_SPARE_FRAME = "4/";
	    private static final String INVALID_SPARE_FRAME = "7-";

	    private AnalyzeSpareFrame spareFrameParser;

	    @Before
	    public void setUp() {
	        initializeStrikeFrameParser();
	    }

	    @Test
	    public void shouldReturnStrikeFrame() {
	    	BowlingFrame result = spareFrameParser.checckTheFrame(VALID_SPARE_FRAME);

	        assertTrue(result instanceof FrameSpare);
	    }

	    @Test
	    public void shouldReturnNullFrame() {
	        BowlingFrame result = spareFrameParser.checckTheFrame(INVALID_SPARE_FRAME);

	        assertNull(result);
	    }

	    private void initializeStrikeFrameParser() {
	        spareFrameParser = new AnalyzeSpareFrame();
	    }

	}

