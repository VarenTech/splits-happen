import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.dkward.bowling.StrikesSparesAndMissesGame;
import com.dkward.bowling.frame.FrameSpare;
import com.dkward.bowling.frame.FrameStrike;
import com.dkward.bowling.frame.FrameZero;
import com.dkward.bowling.frame.RegularFrame;

public class StrikesSparesAndMissesTest {

	private StrikesSparesAndMissesGame bowlingGame;
	
	
	@Before
	public void setUp() {
		bowlingGame = new StrikesSparesAndMissesGame();
	}
	
	
    @Test
    public void shouldReturnZeroScore() {
        for (int i = 0; i < 10; i++) {
            FrameZero zeroFrame =  new FrameZero();;
            bowlingGame.addBowlingFrame(zeroFrame);
        }

        assertEquals(0, bowlingGame.getTheScore());
    }


   /* @Test
    public void shouldReturnSumOfNormalFramesAsScore() {
        for (int i = 0; i < 10; i++) {
            RegularFrame regularFrame = new RegularFrame(i, 0);
            bowlingGame.addBowlingFrame(regularFrame);
        }

        assertEquals(45, bowlingGame.getTheScore());
    }


    @Test
    public void shouldReturnPerfectScore() {
        for (int i = 0; i < 12; i++) {
            FrameStrike strikeFrame = new FrameStrike();
            bowlingGame.addBowlingFrame(strikeFrame);
        }

        assertEquals(300, bowlingGame.getTheScore());
    }

    @Test
    public void souldReturnPerfectSpareScore() {
        for (int i = 0; i < 11; i++) {
            FrameSpare spareFrame = new FrameSpare();
            bowlingGame.addBowlingFrame(spareFrame);
        }

        assertEquals(200, bowlingGame.getTheScore());
    }*/


   
 
}
