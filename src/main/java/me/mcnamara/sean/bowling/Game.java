package me.mcnamara.sean.bowling;

public class Game {

	private String line;
	
	public void setBowlingLine(String line) {
		this.line = line;
	}
	
	private int[] charsToRolls(char[] chars) {
		int[] retval = new int[chars.length];
		
		for(int i = 0; i < retval.length; i++) {
			if(chars[i] == '/') {
				retval[i] = 10 - Character.getNumericValue(chars[i-1]);
			}
			else if(chars[i] == 'X') {
				retval[i] = 10;
			}
			else if(chars[i] == '-') {
				retval[i] = 0;
			}
			else {
				retval[i] = Character.getNumericValue(chars[i]);
			}
		}
		
		return retval;
	}
	
	private boolean isSpare(int index, int[] rolls) {
		return index < rolls.length - 2 && rolls[index] < 10 && rolls[index] + rolls[index+1] == 10;
	}
	
	private boolean isStrike(int index, int[] rolls) {
		return index < rolls.length - 3 && rolls[index] == 10;
	}

	public int score() {
		char[] chars = line.toCharArray();
		int[] rolls = charsToRolls(chars);
		
		int accum = 0;
		
		//Calculate score for an entire frame on each iteration
		for(int index = 0; index < Math.min(20, rolls.length); index++) {
			if(isSpare(index, rolls)) {
				accum += 10 + rolls[index+2];
				index++;
			}
			else if(isStrike(index, rolls)) {
				accum += 10 + rolls[index+1] + rolls[index+2];
			}
			else {
				accum += rolls[index] + (index+1 < rolls.length ? rolls[index+1] : 0);
				index++;
			}
		}
		
		return accum;
	}

}
