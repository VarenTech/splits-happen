import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BowlingTest {

  static private Game g;

  @BeforeEach
  void setUp() {
    g = new Game();
  }

  private void rollMany(int n, int pins) {
    for (int i = 0; i < n; i++) {
      g.roll(pins);
    }
  }

  @Test
  void testGutterGame() {
    rollMany(20, 0);
    assertEquals(0, g.getScore());
  }


  @Test
  void testAllOnes() {
    rollMany(20, 1);
    assertEquals(20, g.getScore());
  }

  private void rollStrike() {
    g.roll(10);
  }

  @Test
  void testPerfectGame() {
    rollMany(12, 10);
    assertEquals(300, g.getScore());
  }

  @Test
  void testOneSpare() {
    g.roll(5);
    g.roll(5); // spare
    g.roll(3);
    rollMany(17, 0);
    assertEquals(16, g.getScore());
  }

  @Test
  void testOneStrike() {
    rollStrike();
    g.roll(3);
    g.roll(4);
    rollMany(16, 0);
    assertEquals(24, g.getScore());
  }

  @Test
  void testSpareEveryOther() {
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
    assertEquals(150, g.getScore());
  }

  @Test
  void testMissEveryOther() {
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
    assertEquals(90, g.getScore());
  }

  @Test
  void testAverageGame() {
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
    assertEquals(167, g.getScore());
  }

  @Test
  void testSadness() {
    rollMany(11, 10);
    g.roll(9);
    assertEquals(299, g.getScore());
  }

  @Test
  void testTenthFrameSpare() {
    rollMany(9, 10);
    g.roll(9);
    g.roll(1); //spare
    g.roll(1);
    assertEquals(270, g.getScore());
  }
}
