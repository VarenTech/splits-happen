package app.bowling.models;

import app.bowling.utility.ScoreCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Immutable object representing a Game
 */
public final class Game {
    /**
     * Private variables containing game information
     */
    private final short sGameScore;
    private final List<Frame> listFrames;

    /**
     * Constructor
     * @param objGameBuilder The builder used to make immutable Game object
     */
    private Game(GameBuilder objGameBuilder) {
        this.sGameScore = objGameBuilder.sGameScore;
        this.listFrames = objGameBuilder.listFrames;
    }

    /**
     * Retrieves game's score
     * @return The game's score
     */
    public short getScore() {
        return sGameScore;
    }

    public Stream<Frame> getFrames() {
        return this.listFrames.stream();
    }

    /**
     * Builder class used to create immutable Game objects
     */
    public static class GameBuilder {
        /**
         * Private variables containing information used to build Game object
         */
        private short sGameScore;
        private final List<Frame> listFrames = new ArrayList<>();

        /**
         * Adds a frame to be part of the Game object
         * @param objFrame Frame to add to the Game
         * @return Updated GameBuilder object
         */
        public GameBuilder withFrame(Frame objFrame) {
            listFrames.add(objFrame);
            return this;
        }

        /**
         * Adds multiple frames to be a part of the Game object
         * @param listFrames Frames to add to the Game
         * @return Updated GameBuilder object
         */
        public GameBuilder withFrames(List<Frame> listFrames) {
            this.listFrames.addAll(listFrames);
            return this;
        }

        /**
         * Builds Game object
         * @return The Game object
         */
        public Game build() {
            this.sGameScore = ScoreCalculator.getScore(listFrames);
            return new Game(this);
        }
    }
}
