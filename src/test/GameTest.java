package test;

import com.bowling.Game;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {
    Game game;

    @Before
    public void setUp() {
        game = Game.getInstance();
    }

    @Test
    public void playerRolledGutterGame() {
        game.formatGameScores( "0000000000" );
        assertThat( game.score(), is(0) );
    }

    @Test
    public void mockFileInputPerfectGame() {
        game.formatGameScores( "XXXXXXXXXXXX" );
        assertThat( game.score(), is(300) );
    }

    @Test
    public void mockFileInputSecondLine() {
        game.formatGameScores( "9-9-9-9-9-9-9-9-9-9-" );
        assertThat( game.score(), is(90) );
    }

    @Test
    public void mockFileInputThirdLine() {
        game.formatGameScores( "5/5/5/5/5/5/5/5/5/5/5" );
        assertThat( game.score(), is(150) );
    }

    @Test
    public void mockFileInputForthLine() {
        game.formatGameScores( "X7/9-X-88/-6XXX81" );
        assertThat( game.score(), is(167) );
    }
}

