package org.dimattia.splitsHappen;

/**
 * Abstract class representing a frame of bowling.
 * Implemented as a NormalFrame, or a FinalFrame.
 * @author Dustin
 *
 */
public abstract class Frame {
	// The next frame of the game.
	protected Frame nextFrame;
	// The number of the frame in the game. Used for debugging purposed, could be removed.
	protected int frameNum;
	
	public Frame(int frameNum, Frame nextFrame) {
		this.frameNum = frameNum;
		this.nextFrame = nextFrame;
	}
	
	/**
	 * Calculate the score for the Frame.
	 * @return An integer representing the score of the frame.
	 */
	public abstract int sumFrame();

	/**
	 * Calculates the spare bonus awarded to the previous Frame.
	 * @return The bonus score that should be added to the previous Frame.
	 */
	protected abstract int spareBonus();
	/**
	 * Calculates the strike bonus awarded to the previous Frame.
	 * @return The bonus score that should be added to the previous Frame.
	 */
	protected abstract int strikeBonus();
	
	/**
	 * Advances to the next frame of the game.
	 * @return The next frame of the game, or null if the current frame is the FinalFrame.
	 */
	public Frame nextFrame() {
		return this.nextFrame;
	}
	
	/**
	 * Interprets the roll character as a numeric value.
	 * @param roll A character representing one roll of bowling.
	 * @return The numeric value of the provided character.
	 */
	protected int numberfy(char roll) {
		if(roll == 'X') return 10;
		if(roll == '-') return 0;
		return Character.getNumericValue(roll);
	}

}
