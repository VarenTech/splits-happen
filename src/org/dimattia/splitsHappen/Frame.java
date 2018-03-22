package org.dimattia.splitsHappen;

public abstract class Frame {
	protected Frame nextFrame;
	protected int frameNum;
	
	public Frame(int frameNum, Frame nextFrame) {
		this.frameNum = frameNum;
		this.nextFrame = nextFrame;
	}

	public abstract int sumFrame();

	protected abstract int spareBonus();
	protected abstract int strikeBonus();
	
	public Frame nextFrame() {
		return this.nextFrame;
	}
	
	protected int numerate(char roll) {
		if(roll == 'X') return 10;
		if(roll == '-') return 0;
		return Character.getNumericValue(roll);
	}

}
