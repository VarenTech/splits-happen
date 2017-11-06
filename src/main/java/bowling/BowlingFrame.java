package bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * A frame in a bowling game
 * 
 * @author suhas
 *
 */
public class BowlingFrame {
	private List<Roll> rolls;
	private BowlingFrame next;

	/**
	 * @return the rolls
	 */
	public List<Roll> getRolls() {
		return rolls;
	}

	public void addRoll(Roll roll) {
		if (getRolls() == null) {
			rolls = new ArrayList<Roll>();
		}
		rolls.add(roll);
	}

	/**
	 * @return the next
	 */
	public BowlingFrame getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(BowlingFrame next) {
		this.next = next;
	}
}