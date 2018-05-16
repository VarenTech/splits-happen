/**
 * Bowling score program
 * @author Greg Kogut
 * gregkgt@gmail.com
 */
package com.kogut;

public class BowlingScore {
	public static void main(String[] args) {
		if (!(args.length == 1)) {
			System.out.println("Usage: java com.kogut.BowlingScore <10 score sequence>");
			System.exit(1);
		}
		
		//skip input checking, just run with first command line arg
		System.out.println(computeScore(args[0]));
	}

	/**
	 * Given a valid sequence of rolls as input, this method will compute a bowling score.
	 * @param args passed in command line arguments
	 * @return total score as integer
	 */
	private static int computeScore(String arg) {
		char[] bowlingSequence = arg.toCharArray();
		int numFrames = 10;
		int currentFrame = 1;
		int currentIndex = 0;
		int totalScore = 0;
		char currentBowl;
		char secondBowl;
		
		//loop through 10 frames
		while (currentFrame <= numFrames) {
			currentBowl = bowlingSequence[currentIndex];
			
			if (currentBowl == 'X') { //strike
				totalScore += computeStrike(bowlingSequence, currentIndex, 1, 0);
				//next frame and next character since it's a strike
				currentFrame++;
				currentIndex++;
			} else {//not a strike, need at least two characters to compute the frame
				secondBowl = bowlingSequence[currentIndex + 1];
				
				if (secondBowl == '/') { //spare, 10 + first pin in next frame
					totalScore += 10 + determinePinValue(bowlingSequence[currentIndex + 2]);
				} else { //else just two numbers
					totalScore += determinePinValue(currentBowl) + determinePinValue(secondBowl);
				}
				//next frame and skip two characters
				currentIndex += 2;
				currentFrame++;
			}
		}
		return totalScore;
	}
	
	/**
	 * Determines the score from a strike.
	 * @param bowlingSequence bowling input sequence
	 * @param currentIndex our index in the bowlingSequence array
	 * @param bowlNumber either 1,2, or 3
	 * @param previousPinValue the score we got on the last bowl
	 * @return
	 */
	private static int computeStrike(char[] bowlingSequence, int currentIndex, int bowlNumber, int previousPinValue) {
		int currentPinValue;
		
		switch (bowlNumber) {
		case 3: //base case, return pin value
			return determinePinValue(bowlingSequence[currentIndex], previousPinValue);
		case 2: //get the pin value and recursively call function for third bowl
			currentPinValue = determinePinValue(bowlingSequence[currentIndex], previousPinValue);
			return currentPinValue + computeStrike(bowlingSequence, currentIndex+1, 3, currentPinValue);
		case 1: //get the pin value and recursively call function for second bowl
			currentPinValue = determinePinValue(bowlingSequence[currentIndex], previousPinValue);
			return currentPinValue + computeStrike(bowlingSequence, currentIndex+1, 2, currentPinValue);
		default:
			return 0;
		}
	}
	
	/**
	 * Determines the pin value of a character
	 * @param bowl character from input string
	 * @param previousPinValue previous pin value in case of a spare
	 * @return the pin value of the character
	 */
	private static int determinePinValue(char bowl, int previousPinValue) {
		switch (bowl) {
		case 'X':
			return 10;
		case '/':
			return 10 - previousPinValue;
		case '-':
			return 0;
		default:
			return Character.getNumericValue(bowl);
		}
	}
	
	/**
	 * Determines the pin value of a character. Overloaded in case we don't care about previous bowl.
	 * @param bowl character from input string
	 * @return the pin value of the character
	 */
	private static int determinePinValue(char bowl) {
		//give previous pin value a default of 0
		return determinePinValue(bowl, 0);
	}
}
