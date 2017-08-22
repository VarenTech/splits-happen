// line must be a valid bowling score line
module.exports = (line) => {
    // used to track each string/number in the line with a point value before calculating
    class Point {
        constructor(type, amount) {
            this.type = type;
            this.amount = amount;
        }
        getAmount() {
            return this.amount;
        }
        getType() {
            return this.type;
        }
    }
    // convert the line string into an array of strings
    const rolls = line.split('');
    // push Points into here
    const points = [];
    // 2 rolls per round unless strike or spare
    let numRolls = 20;
    // the score
    let total = 0;
    // first go through each item from the submitted line
    // create a Point and push it into the points array
    rolls.forEach(roll => {
        if (isNaN(roll)) {
            switch(roll) {
                case 'X':
                    // if Strike, register 10 points
                    points.push(new Point('X', 10));
                    break;
                case '-':
                    // still push a Point with 0 amount for misses
                    points.push(new Point(null, 0));
                    break;
                case '/':
                    // if Spare, register total of both rolls to create spare
                    // ex. 6 & 4
                    // have to go back into points array to get the last roll
                    const last = points.length?points[points.length -1].getAmount():0;
                    points.push(new Point('/', 10 - last));
                    break;
                default:
                    // catch all - don't need for this exercise
                    this.points.push(new Point(null, 0));
            }
        } else {
            // push a Point with amount = number of knocked down
            points.push(new Point(null, parseInt(roll)));
        }
    });
    // then we loop through each Point in points
    // calculate the total depending on the Point.type (strike, spare, number)
    for (let i = 0; i < points.length; i++) {
        // only looping for 20 rolls although we count all the rolls
        if(numRolls <= 0) break;
        const currPoint = points[i];
        // this is for not spare or strike
        if(!currPoint.getType()) {
            total += currPoint.getAmount();
            numRolls--;
        } else {
            switch(currPoint.getType()) {
                case 'X':
                    // its a strike, so count ahead two Point in points
                    // if they exists
                    total += currPoint.getAmount();
                    if ((i+1) < points.length) {
                        total += points[i+1].getAmount();
                    }
                    if ((i+2) < points.length) {
                        total += points[i+2].getAmount();
                    }
                    numRolls -= 2;
                    break;
                case '/':
                    // its a spare, so count ahead one Point in points
                    // it it exist
                    total += currPoint.getAmount();
                    if ((i+1) < points.length) {
                        total += points[i+1].getAmount();
                    }
                    numRolls--;
                    break;
            }
        }
    }

    return total
}
