package app.bowling.test;

import app.bowling.Main;
import app.bowling.models.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * JUnit tests used to test all test cases provided
 */
public class AppTests {

    @Test
    public void testScoreAllStrikes() {
        String strGameThrows = "XXXXXXXXXXXX";

        Game objGame = Main.startGameWithString(strGameThrows);

        assertEquals(300, objGame.getScore());
    }

    @Test
    public void testScoreNoStrikesOrSpares() {
        String strGameThrows = "9-9-9-9-9-9-9-9-9-9-";

        Game objGame = Main.startGameWithString(strGameThrows);

        assertEquals(90, objGame.getScore());
    }

    @Test
    public void testScoreAllSpares() {
        String strGameThrows = "5/5/5/5/5/5/5/5/5/5/5";

        Game objGame = Main.startGameWithString(strGameThrows);

        assertEquals(150, objGame.getScore());
    }

    @Test
    public void testScoreMixedThrows() {
        String strGameThrows = "X7/9-X-88/-6XXX81";

        Game objGame = Main.startGameWithString(strGameThrows);

        assertEquals(167, objGame.getScore());
    }
}
