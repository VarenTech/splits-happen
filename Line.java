package bowling;

import java.util.ArrayList;

public class Line {

	int lineScore;
	int frameHalves;
	int[] allRolls;
	int numRolls;

	// markers for which rolls have strikes or spares
	boolean[] strikes;
	boolean[] spares;

	public Line() {

		lineScore = 0;
		frameHalves = 0;
		allRolls = new int[21]; // can have up to 21 rolls
		strikes = new boolean[21];
		spares = new boolean[21];

		for (int i = 0; i < 21; i++) {
			allRolls[i] = 0;
			strikes[i] = false;
			spares[i] = false;
		}

	}

	public void rollAll(ArrayList<String> rollResults) {

		numRolls = rollResults.size();

		for (int i = 0; i < rollResults.size(); i++) {

			if (rollResults.get(i) == "X") {
				allRolls[i] = 10;
				strikes[i] = true;
				frameHalves++;
			}

			else if (rollResults.get(i) == "-") {
				allRolls[i] = 0;
			}

			else if (rollResults.get(i) == "/") {
				allRolls[i] = (10 - allRolls[i - 1]);
				spares[i] = true;
			}

			else {
				allRolls[i] = Integer.parseInt(rollResults.get(i));
			}
			frameHalves++;
		}

	}

	public int score() {

		int loopLimit = 0;

		if (strikes[numRolls - 1] == true) {
			loopLimit = numRolls - 2;
		}

		else if (spares[numRolls - 2] == true) {
			loopLimit = numRolls - 1;
		}

		else {
			loopLimit = numRolls;
		}

		for (int i = 0; i < loopLimit; i++) {

			if (strikes[i] == true) {
				lineScore += (allRolls[i] + allRolls[i + 1] + allRolls[i + 2]);
			} else if (spares[i] == true) {
				lineScore += (allRolls[i] + allRolls[i + 1]);
			} else {
				lineScore += allRolls[i];
			}

		}
		if (frameHalves == 22) {
			lineScore -= (allRolls[numRolls - 2] + allRolls[numRolls - 1]);
		}

		return lineScore;
	}

}
