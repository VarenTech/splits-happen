/*
    Fork of VarenTech splits happen

    Author: mleopard
*/

function generateScore() {
    var scoreString = document.getElementById("scoreCard").value;
    var value = calculateScore(scoreString);
    document.getElementById("scores").innerHTML = value;
}

function calculateScore(scores) {
    var newArray = [];
    for (var x = 0; x < scores.length; x++) {
        var val = scores[x];
        if (val === "/") {
            var remainder = 10 - newArray[newArray.length - 1];
            remove(newArray, x);
            if (scores[x+2]) {
                newArray.push(remainder + calculateScore([scores[x+1]]));
            } else if (scores.length < 4) {
                newArray.push(remainder);
            } else if (scores[x+1]) {
                newArray.push(remainder + calculateScore([scores[x+1]]));
            } 
        } else if (val === "X") {
            if (scores[x+1] && scores[x+2] && scores[x+3]) {
                var arr = [scores[x+1], scores[x+2]];
                if (arr[1] === "/") {
                    arr.push(scores[x+3]);
                }
                newArray.push(10 + calculateScore(arr));
            } else {
                newArray.push(10);
            }
        } else if (val === "-") {
            newArray.push(0);
        } else if (parseInt(val)) {
            newArray.push(parseInt(val));
        }
    }
    var calculatedArray = 0;
    if (newArray.length > 0) {
        calculatedArray = newArray.reduce(function (total, num) {
            return total + num;
        });
    }
    return calculatedArray;
}

function remove(array, element) {
    const index = array.indexOf(element);
    if (index !== -1) {
        array.splice(index, 1);
    }
}