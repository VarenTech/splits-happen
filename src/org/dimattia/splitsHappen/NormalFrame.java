package org.dimattia.splitsHappen;

public class NormalFrame extends Frame {
	private char firstRoll = '-';
	private char secondRoll = '-';
	
	public NormalFrame(String input, int frameNum, Frame nextFrame) {
		super(frameNum, nextFrame);
		if(input.length() == 2) secondRoll = input.charAt(1);
		firstRoll = input.charAt(0);
	}

	@Override
	public int sumFrame() {
		if(firstRoll == 'X') return 10 + nextFrame.strikeBonus();
		if(secondRoll == '/') return 10 + nextFrame.spareBonus();
		return numerate(firstRoll) + numerate(secondRoll);
	}

	@Override
	protected int spareBonus() {
		return numerate(firstRoll);
	}

	@Override
	protected int strikeBonus() {
		if(secondRoll == '/') return 10;
		if(firstRoll == 'X') return 10 + nextFrame.spareBonus();
		return numerate(firstRoll) + numerate(secondRoll);
	}

}
