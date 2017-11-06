package bowling;

/**
 * Enumeration of possible roll outcomes
 * 
 * @author suhas
 *
 */
public enum Roll {
	ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), STRIKE(
			"X"), SPARE("/"), MISS("-");

	private String value;

	/**
	 * @param value
	 */
	private Roll(String value) {
		this.value = value;
	}

	/**
	 * Gets the roll value
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	public static Roll find(String value) {
		Roll rollFound = null;
		for (Roll roll : Roll.values()) {
			if (roll.getValue().equalsIgnoreCase(value)) {
				rollFound = roll;
				break;
			}
		}
		return rollFound;
	}

}
