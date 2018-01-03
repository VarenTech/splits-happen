package me.mcnamara.sean.bowling;

public class Game {

	private String line;
	
	public void setBowlingLine(String line) {
		this.line = line;
	}

	public int score() {
		char[] chars = line.toCharArray();
		int idx = 0;
		int accum = 0;
		
		for(int frame = 0; frame < 10; frame++) {
			char curr = chars[idx];
			int numVal = Character.getNumericValue(curr);
			
			if(numVal > 0) {
				accum += numVal;
			}
		}
		
		return accum;
	}

}
