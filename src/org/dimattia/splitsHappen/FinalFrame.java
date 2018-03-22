package org.dimattia.splitsHappen;

public class FinalFrame extends Frame {
	private char firstRoll = '-';
	private char secondRoll = '-';
	private char lastRoll = '-';

	public FinalFrame(String input, int frameNum, Frame nextFrame) {
		super(frameNum, nextFrame);
		
		int length = input.length();
		if(length > 2) this.lastRoll = input.charAt(2);
		if(length > 1) this.secondRoll = input.charAt(1);
		this.firstRoll = input.charAt(0);
		
		if(lastRoll == '/') lastRoll = Character.forDigit(10 - numerate(secondRoll), 10);
		if(secondRoll == '/') secondRoll = Character.forDigit(10 - numerate(firstRoll), 10);
	}

	@Override
	public int sumFrame() {
		return numerate(firstRoll) + numerate(secondRoll) + numerate(lastRoll);
	}

	@Override
	protected int spareBonus() {
		return numerate(firstRoll);
	}

	@Override
	protected int strikeBonus() {
		return numerate(firstRoll) + numerate(secondRoll);
	}

}
