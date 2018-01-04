<?php
/*	Author: Andrew Baucom (abaucom@beezle.com)
	Assuming no input errors

	Output:

	Game: XXXXXXXXXXXX                   Score: 300
	Game: 9-9-9-9-9-9-9-9-9-9-           Score: 90
	Game: 5/5/5/5/5/5/5/5/5/5/5          Score: 150
	Game: X7/9-X-88/-6XXX81              Score: 167
*/

$games = array();
$games[] = 'XXXXXXXXXXXX';
$games[] = '9-9-9-9-9-9-9-9-9-9-';
$games[] = '5/5/5/5/5/5/5/5/5/5/5';
$games[] = 'X7/9-X-88/-6XXX81';

// PLAY EACH GAME IN LIST
foreach ($games as $game) {
	// SPLIT INPUT STRING TO ARRAY FOR EASIER ACCESS
	$rolls = str_split($game);

	$scoreTotal = 0;
	while (($scoreFrame = playFrame($rolls)) != null) {
		$scoreTotal += $scoreFrame;
	}
	echo sprintf("Game: %-30s Score: %d\n", $game, $scoreTotal);
}


/******************************************************************************/
function score($roll, $prevRoll=0) {
	// CONVERT SPECIAL INPUT CHARACTERS TO ACTUAL SCORES
	// 0-9 == 0-9
	// - == 0
	// X == 10
	// / == (10 - previous roll)

	if ($roll == '/')
		return 10 - $prevRoll; 

	return (($roll == '-') ? 0 : (($roll == 'X') ? 10 : $roll));
}

/******************************************************************************/
function playFrame(&$rolls) {
	// GET FIRST ROLL OF FRAME
	if (($roll1 = array_shift($rolls)) == null)
		return null; // NO MORE FRAMES

	// A FRAME IS TWO ROLLS UNLESS A STRIKE

	if ($roll1 == 'X') {
		// STRIKE ROLLED, SCORE FOR THIS FRAME IS 10 PLUS NEXT TWO ROLLS

		$score1 = score($rolls[0]);
		$score2 = score($rolls[1], $score1);
		$score = 10 + $score1 + $score2;

		// IF WE ARE IN LAST FRAME (JUST CLEAR BONUS ROLLS TO FORCE END OF GAME)
		if (count($rolls) < 3)
			$rolls = array();

	} else {
		// NOT A STRIKE, GET SECOND ROLL

		if (($roll2 = array_shift($rolls)) == null)
			return null; // NO MORE FRAMES OR BONUS ROLLS OR ERROR

		if ($roll2 == '/') {
			// SPARE ROLLED, SCORE FOR THIS FRAME IS 10 PLUS NEXT ROLL

			$score = 10 + score($rolls[0]);

			// IF WE ARE IN LAST FRAME (JUST CLEAR BONUS ROLLS TO FORCE END OF GAME)
			if (count($rolls) < 2)
				$rolls = array();

		} else {
			// NO STRIKE OR SPARE SCORE IS TOTAL OF BOTH ROLLS

			$score = score($roll1) + score($roll2);
		}
	}

	return $score;
}

