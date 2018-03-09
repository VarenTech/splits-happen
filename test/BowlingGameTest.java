import junit.framework.TestCase;

public class BowlingGameTest extends TestCase{

    private Game g;

    @Override
    protected void setUp(){
        g = new Game();
    }

    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++)
            g.roll(pins);
    }

    public void testGutterGame(){
        rollMany(20, 0);
        assertEquals(0, g.score());
    }


    public void testAllOnes() {
        rollMany(20, 1);
        assertEquals(20, g.score());
    }

    private void rollStrike() {
        g.roll(10);
    }

    public void testPerfectGame()  {
        rollMany(12, 10);
        assertEquals(300, g.score());
    }

    public void testOneSpare()  {
        g.roll(5);
        g.roll(5); // spare
        g.roll(3);
        rollMany(17, 0);
        assertEquals(16, g.score());
    }

    public void testOneStrike() {
        rollStrike();
        g.roll(3);
        g.roll(4);
        rollMany(16, 0);
        assertEquals(24, g.score());
    }

    public void testSpareEveryOther() {
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      g.roll(5); //spare
      g.roll(5);
      assertEquals(150, g.score());
    }

    public void testMissEveryOther() {
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      g.roll(9);
      g.roll(0);
      assertEquals(90, g.score());
    }

    public void testAverageGame() {
      rollStrike();
      g.roll(7);
      g.roll(3); //spare
      g.roll(9);
      g.roll(0);
      rollStrike();
      g.roll(0);
      g.roll(8);
      g.roll(8);
      g.roll(2);
      g.roll(0);
      g.roll(6);
      rollStrike();
      rollStrike();
      rollStrike();
      g.roll(8);
      g.roll(1);
      assertEquals(167, g.score());
    }

    public void testSadness() {
      rollMany(11, 10);
      g.roll(9);
      assertEquals(299, g.score());
    }

    public void testTenthFrameSpare() {
      rollMany(9, 10);
      g.roll(9);
      g.roll(1); //spare
      g.roll(1);
      assertEquals(270, g.score());
    }

}
