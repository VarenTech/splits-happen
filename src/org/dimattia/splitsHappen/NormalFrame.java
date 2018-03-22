package org.dimattia.splitsHappen;

/**
 * Represents all frames of the game except for the last frame.
 * @author Dustin
 *
 */
public class NormalFrame extends Frame {
	private char firstRoll = '-';
	private char secondRoll = '-';
	
	/**
	 * Invokes the super constructor to set frameNum and nextFrame.
	 * Sets the frames rolls based on the input.
	 * @param input A String representing the rolls for this frame.
	 * @param frameNum An integer representing the place of the Frame in the game.
	 * @param nextFrame The next Frame of the game.
	 */
	public NormalFrame(String input, int frameNum, Frame nextFrame) {
		super(frameNum, nextFrame);
		// There will not be a second roll if the frame is a strike.
		if(input.length() == 2) secondRoll = input.charAt(1);
		firstRoll = input.charAt(0);
	}

	
	@Override
	public int sumFrame() {
		// On a spare or a strike, score is 10 plus the bonus.
		if(firstRoll == 'X') return 10 + nextFrame.strikeBonus();
		if(secondRoll == '/') return 10 + nextFrame.spareBonus();
		// Otherwise, the score is the sum of the two rolls.
		return numberfy(firstRoll) + numberfy(secondRoll);
	}

	@Override
	protected int spareBonus() {
		// The spare bonus is the value of the first roll.
		return numberfy(firstRoll);
	}

	@Override
	protected int strikeBonus() {
		// If the frame is a spare, the strike bonus is 10.
		if(secondRoll == '/') return 10;
		// If the frame is a strike, the bonus is 10 plus the spare bonus of the next frame.
		if(firstRoll == 'X') return 10 + nextFrame.spareBonus();
		//Otherwise the bonus is the sum of this frame.
		return numberfy(firstRoll) + numberfy(secondRoll);
	}

}
