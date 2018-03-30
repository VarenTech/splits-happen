package scoreTest;

import org.junit.Test;
import score.Score;

import static org.junit.Assert.assertEquals;

public class ScoreTest {
    public Score test;

    @Test
    public void testStrikes() {
        String line = "XXXXXXXXXXXX";
        test = new Score(line);
        assertEquals(300,test.findFinalScore());
    }

    @Test
    public void testNine() {
        String line = "9-9-9-9-9-9-9-9-9-9-";
        test = new Score(line);
        assertEquals(90,test.findFinalScore());
    }

    @Test
    public void testSpareFive() {
        String line = "5/5/5/5/5/5/5/5/5/5/5";
        test = new Score(line);
        assertEquals(150,test.findFinalScore());
    }

    @Test
    public void testRandom() {
        String line = "X7/9-X-88/-6XXX81";
        test = new Score(line);
        assertEquals(167,test.findFinalScore());
    }

}