package org.dimattia.splitsHappen;

/**
 * The FinalFrame of a game of bowling.
 * Computes scores slightly differently than NormalFrames
 * @author Dustin
 *
 */
public class FinalFrame extends Frame {
	// The FinalFrame may have a third roll, if a spare or strike is scored.
	private char firstRoll = '-';
	private char secondRoll = '-';
	private char lastRoll = '-';

	/**
	 * Invokes the super constructor to set frameNum and nextFrame.
	 * Sets the frames rolls based on the input.
	 * @param input A String representing the rolls for this frame.
	 * @param frameNum An integer representing the place of the Frame in the game.
	 * @param nextFrame The next Frame of the game.
	 */
	public FinalFrame(String input, int frameNum, Frame nextFrame) {
		super(frameNum, nextFrame);
		
		// Only set rolls for which there are values.
		int length = input.length();
		if(length > 2) this.lastRoll = input.charAt(2);
		if(length > 1) this.secondRoll = input.charAt(1);
		this.firstRoll = input.charAt(0);
		
		/*
		 * The spare symbol need not be retained, as the spare bonus is built into the third roll.
		 * The symbol should be replace with the value of the roll, 10 minus the previous roll.
		 */
		if(lastRoll == '/') lastRoll = Character.forDigit(10 - numberfy(secondRoll), 10);
		if(secondRoll == '/') secondRoll = Character.forDigit(10 - numberfy(firstRoll), 10);
	}

	@Override
	// The FinalFrame does not need to get bonus values from other frames. The three roll values are enough.
	public int sumFrame() {
		return numberfy(firstRoll) + numberfy(secondRoll) + numberfy(lastRoll);
	}

	@Override
	protected int spareBonus() {
		// The spare bonus is the value of the first roll.
		return numberfy(firstRoll);
	}

	@Override
	protected int strikeBonus() {
		// The strike bonus is the value of the first and second rolls.
		return numberfy(firstRoll) + numberfy(secondRoll);
	}

}
