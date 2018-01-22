package app.bowling;

import app.bowling.models.Frame;
import app.bowling.models.Game;
import app.bowling.utility.FrameGenerator;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println(String.format("Game String entered: %s", args[0])); //NOSONAR - Will be used in console.  Console output desired.

        Game objGame = startGameWithString(args[0]);

        System.out.println(String.format("Final Score: %d", objGame.getScore())); // NOSONAR - Will be used in console.  Console output desired.

    }

    public static Game startGameWithString(String strGameThrows) {
        List<Frame> listFrames = FrameGenerator.generateFrames(strGameThrows);
        Game.GameBuilder objGameBuilder = new Game.GameBuilder();
        return objGameBuilder
                .withFrames(listFrames)
                .build();
    }
}
