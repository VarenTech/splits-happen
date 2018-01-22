package app.bowling.models;

import java.util.Objects;
import java.util.Optional;

/**
 * Immutable object representing a Frame
 */
public final class Frame {
    /**
     * Private variables representing scores for each throw
     */
    private final Byte bytThrowOne;
    private final Byte bytThrowTwo;
    private final Byte bytThrowThree;

    private Frame(FrameBuilder objBuilder) {
        this.bytThrowOne = Objects.requireNonNull(objBuilder.bytThrowOne);
        this.bytThrowTwo = objBuilder.bytThrowTwo;
        this.bytThrowThree = objBuilder.bytThrowThree;
    }

    public Byte getFirstThrow() {
        return bytThrowOne;
    }

    public Optional<Byte> getSecondThrow() {
        return Optional.ofNullable(bytThrowTwo);
    }

    public Optional<Byte> getThirdThrow() {
        return Optional.ofNullable(bytThrowThree);
    }

    public static class FrameBuilder {
        private Byte bytThrowOne;
        private Byte bytThrowTwo;
        private Byte bytThrowThree;

        public FrameBuilder withFirstThrow(Byte bytThrow) {
            this.bytThrowOne = bytThrow;
            return this;
        }

        public FrameBuilder withSecondThrow(Byte bytThrow) {
            this.bytThrowTwo = bytThrow;
            return this;
        }

        public FrameBuilder withThirdThrow(Byte bytThrow) {
            this.bytThrowThree = bytThrow;
            return this;
        }

        public Frame build() {
            return new Frame(this);
        }
    }
}
