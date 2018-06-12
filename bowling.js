function submitScores(){
    //Get scores from user input
    var scoresInput = document.getElementById("scores").value;
    //Split each score out from the input string
    var rolls = scoresInput.split("");
    //Call the function to calculate the total score
    var totalScore = calculateScore(rolls);
    //Insert the total score into the HTML
    document.getElementById("score").innerHTML = totalScore;
}


function calculateScore(input) {
    var scoresArr = [];
    //Figure out the base score
    for (var i=0; i<input.length; i++) {
        if (input[i] == ("X"||"x")) {
            //base value of a strike
            scoresArr.push(10); 
        } else if (input[i] == "/") {
            //the value of the remaining pins knocked down by the spare
            scoresArr.push(10 - input[i-1]); 
        } else if (input[i] == "-") {
            scoresArr.push(0);
        //if it can be parsed as an int, it's a number
        } else if (parseInt(input[i])) {
            scoresArr.push(parseInt(input[i]));
        }
    }

    //Calculate how many bonus rolls were made
    var bonusRolls;
    if (input[input.length-3] == ("X"||"x")) {
        bonusRolls = 2;
    } else if (input[input.length-2] == "/") {
        bonusRolls = 1;
    } else {
        bonusRolls = 0;
    }
    
    //Add the bonus points for strikes and spares
    var score = 0;
    //Minus the number of bonusrolls so they only get counted once, as part of the X or / bonus
    for (i=0; i<input.length - bonusRolls; i++) {
        //Add base scores
        score += scoresArr[i];
        //Add bonus for strike
        if (input[i] == ("X"||"x")) {
            score += (scoresArr[i+1] + scoresArr[i+2]);
        //Add bonus for spare
        } else if (input[i] == "/") {
            score += (scoresArr[i+1]);
        }
    }
    
    return score;
}
