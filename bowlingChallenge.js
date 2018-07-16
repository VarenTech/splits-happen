//Task: Create a program which, given a valid sequence of rolls for one line of American 
//Ten-Pin Bowling, produces the total score for the game.
//No need to check for valid rolls. No need to check for correct number of rolls and frames.
//No need to provide scores for intermediate frames. 
//For program input, "X" indicates a strike, "/" indicates a spare, "-" indicates a miss, and 
//a number indicates the number of pins knocked down in the roll.
//Input: XXXXXXXXXXXX						
//Expected Output: 300					
//Input: X7/9-X-88/-6XXX81
//Expected Output: 167
//Input: 5/5/5/5/5/5/5/5/5/5/5
//Expected Output: 150
//Input: 9-9-9-9-9-9-9-9-9-9-
//Expected Output: 	90

var bowlingScore = function (bowlingLine) {

	// Use split method to take input string parameter and split up into array of substrings to seperate each roll and frame
	let frames = bowlingLine.split("");
	// console.log(frames[frames.length-3]);
	// console.log(frames.length);

	// Array being initialized for successfull roll scores
	let scores = [];

	// Loop through frames and determine successfull integer score entries for each roll
	// Pushing corresponding numerical value into scores array; 
	for(let i = 0; i < frames.length; i++) {
		if(frames[i] === 'X') {
			scores.push(10);
		} else if(frames[i] === '-') {
			scores.push(0);
		} else if(frames[i] === '/') {
			scores.push(10 - frames[i-1]);
		} else {
			scores.push(frames[i]*1);
		}
	}

	// console.log(scores);

	// Bonus rolls will have to be determined for later scoring total
	// Determine bonus rolls, and set bonusRolls to either 2 or 1 to determine scoring for  
	// Final frame
	let bonusRolls = 0;
	if((frames[frames.length-3]) === 'X') {
		bonusRolls = 2;
	} else if((frames[frames.length-2]) === '/') {
		bonusRolls = 1;
	} 

 	// Declare variables that holds score total and condition for final frame scoring  
	let runningScore = 0; 
	let gameFrameLength = frames.length - bonusRolls;

	// Score Logic
	// Add values from scores array with strike and spare conditions.
	for(let i = 0; i < gameFrameLength; i++){
		runningScore = runningScore + scores[i];
		// Condition for Strike.
		if(frames[i] === 'X') {
			runningScore += scores[i+1] + scores[i+2];
		}
		// Condition for Spare
		if(frames[i] === '/') {
			runningScore += scores[i+1];
		}
	}
	console.log("Total Score: " + runningScore);


}


//Program Input Test Cases

// bowlingScore("XXXXXXXXXXXX");
// bowlingScore("9-9-9-9-9-9-9-9-9-9-");
// bowlingScore("5/5/5/5/5/5/5/5/5/5/5");
// bowlingScore("X7/9-X-88/-6XXX81");


