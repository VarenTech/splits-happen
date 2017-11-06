package bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author suhas
 *
 */
public class Game {
	private String rollSequence;
	private LinkedList<BowlingFrame> frames = new LinkedList<BowlingFrame>();
	private int score;

	/**
	 * @param rollSequence
	 */
	public Game(String rollSequence) {
		super();
		setRollSequence(rollSequence);
	}

	/**
	 * @return the rollSequence
	 */
	public String getRollSequence() {
		return rollSequence;
	}

	/**
	 * @param rollSequence
	 *            the rollSequence to set
	 */
	public void setRollSequence(String rollSequence) {
		this.rollSequence = rollSequence;
		frames.clear();
		setFrames(rollSequence);
		score = -1;
	}

	/**
	 * @return the frames
	 */
	public LinkedList<BowlingFrame> getFrames() {
		return frames;
	}

	private void setFrames(String rollSequence) {
		String[] rolls = rollSequence.split("");
		for (String rollString : rolls) {
			Roll roll = Roll.find(rollString);
			BowlingFrame frame = null;
			if (frames.isEmpty()) {
				frame = new BowlingFrame();
				frame.addRoll(roll);
				frames.addLast(frame);
			} else {
				frame = frames.getLast();
				if ((isThirdRoll(frame) || isFirstRollStrike(frame)) && !isLastFrame(frame)) {
					BowlingFrame newLastFrame = new BowlingFrame();
					newLastFrame.addRoll(roll);
					frame.setNext(newLastFrame);
					frames.addLast(newLastFrame);
				} else {
					frame.addRoll(roll);
				}
			}
		}
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		if (score < 0) {
			score = calculateScore();
		}

		return score;
	}

	private boolean isLastFrame(BowlingFrame frame) {
		// A bowing game has a maximum of 10 frames
		if (frames.indexOf(frame) == 9)
			return true;
		else
			return false;
	}

	private boolean isFirstRollStrike(BowlingFrame frame) {
		List<Roll> rolls = frame.getRolls();
		if (rolls.size() == 1 && rolls.get(0).equals(Roll.STRIKE))
			return true;
		else
			return false;
	}

	private boolean isThirdRoll(BowlingFrame frame) {
		if (frame.getRolls().size() == 2)
			return true;
		else
			return false;
	}

	private int calculateScore() {
		int runningScore = 0;
		ListIterator<BowlingFrame> iter = frames.listIterator();
		while (iter.hasNext()) {
			BowlingFrame frame = iter.next();
			runningScore += calculateScoreForFrame(frame);
		}
		return runningScore;
	}

	private int calculateScoreForFrame(BowlingFrame frame) {
		int frameScore = getRollScoreForFrame(frame) + getBonusForFrame(frame);
		return frameScore;
	}

	private int getRollScoreForFrame(BowlingFrame frame) {
		int rollScore = 0;
		List<Roll> rolls = frame.getRolls();
		if (rolls.contains(Roll.STRIKE) || rolls.contains(Roll.SPARE))
			rollScore = getScorePerRoll(Roll.STRIKE);
		else
			for (Roll roll : rolls)
				rollScore += getScorePerRoll(roll);
		return rollScore;
	}

	private int getBonusForFrame(BowlingFrame frame) {
		int bonus = 0;
		List<Roll> rolls = frame.getRolls();
		// SPARE BONUS = Score for immediate next roll only
		if (rolls.contains(Roll.SPARE)) {
			if (isLastFrame(frame))
				// bonus applies only if the second roll is a SPARE
				if (rolls.indexOf(Roll.SPARE) == 1)
					bonus = getScorePerRoll(rolls.get(rolls.size() - 1)); // get last roll
				else
					bonus = getScorePerRoll(frame.getNext().getRolls().get(0)); // get first roll of next frame
		}
		// STRIKE BONUS on first roll = Score for immediate next two rolls
		else if (rolls.get(0).equals(Roll.STRIKE)) {
			if (isLastFrame(frame)) {
				List<Roll> bonusRolls = frame.getRolls().subList(1, 3);
				if (bonusRolls.contains(Roll.SPARE))
					bonus = getScorePerRoll(Roll.SPARE);
				else
					for (Roll roll : bonusRolls)
						bonus += getScorePerRoll(roll);
			} else {
				BowlingFrame next1 = frame.getNext();
				if (next1.getRolls().size() > 1) {
					List<Roll> bonusRolls = next1.getRolls().subList(0, 2);
					if (bonusRolls.contains(Roll.SPARE))
						bonus = getScorePerRoll(Roll.SPARE);
					else
						for (Roll roll : bonusRolls)
							bonus += getScorePerRoll(roll);
				} else {
					bonus += getScorePerRoll(next1.getRolls().get(0));
					BowlingFrame next2 = next1.getNext();
					bonus += getScorePerRoll(next2.getRolls().get(0));
				}
			}
		}

		return bonus;
	}

	private int getScorePerRoll(Roll roll) {
		switch (roll) {
		case ONE:
			return 1;
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case STRIKE:
		case SPARE:
			return 10;
		case MISS:
			return 0;
		}
		return 0;
	}
}