"use strict"

function pinsKnockedDown(numOfPinsDown) {
  let frames = readScores();
  refreshFrames(frames, numOfPinsDown);
  refreshPins(frames);
  writeScore(frames);
}

function refreshFrames(frames, numOfPinsDown) {
  if (frames.length == 0) {
    let newFrame = createFrame(1, [numOfPinsDown], null);
    frames.push(newFrame);
    return;
  }

  let frame = frames[frames.length - 1];
  if (frame.isCompleted()) {
    let newFrame = createFrame(frame.frameNum + 1, [numOfPinsDown], null);
    frames.push(newFrame);
  } else {
    frame.rolls[frame.getSlot()] = numOfPinsDown;
  }
}

function refreshPins(frames) {
  let frame = frames[frames.length - 1];
  let numOfPinsDown = frame.getPinsDown();
  let bDisabled = true;

  if (numOfPinsDown == 0) {
    numOfPinsDown = 11; // Reset 0th pin too.
    bDisabled = false;
  }

  for (let i = 10; i > (10 - numOfPinsDown); i--) {
    document.getElementById('pin' + i).disabled = bDisabled;
  }
}

/**
 * This function is called on every event since we're dealing with a stateless
 * app for demonstration purpose only.
 */
function readScores() {
  let frames = [];

  for (let i = 1; i <= 10; i++) {
    let frameStr = `Frame${i}`;
    let firstRoll = document.getElementById(frameStr + '_1').innerHTML;
    let secondRoll = document.getElementById(frameStr + '_2').innerHTML;
    let runningTotal = document.getElementById(frameStr + '_score').innerHTML;

    if (!firstRoll && !secondRoll) {
      break;
    }

    let rolls = [];

    // Tenth frame scoring needs special attention.
    if (i == 10) {
      firstRoll = (firstRoll === 'X') ? 10 : Number(firstRoll);
      rolls.push(firstRoll);
      secondRoll = (secondRoll === 'X') ? 10 : (secondRoll === '/') ? 10 - firstRoll : secondRoll;
      if (isInt(secondRoll)) {
        rolls.push(Number(secondRoll));
      }
    } else {
      if (secondRoll === 'X') {
        rolls.push(10);
      } else if (secondRoll == '/') {
        firstRoll = Number(firstRoll);
        secondRoll = 10 - firstRoll;
        rolls.push(firstRoll);
        rolls.push(secondRoll);
      } else {
        if (isInt(firstRoll)) {
          rolls.push(Number(firstRoll));
        }
        if (isInt(secondRoll)) {
          rolls.push(Number(secondRoll));
        }
      }
    }

    let frame = createFrame(i, rolls, runningTotal);
    frames.push(frame);
  }

  return frames;
}

function writeScore(frames) {
  let frame = frames[frames.length - 1];

  // First, write the most recent bowl score.
  let currentFrameName = `Frame${frame.frameNum}_${frame.getSlot()}`;

  if (frame.isStrike()) {
    document.getElementById(currentFrameName).innerHTML = 'X';
  } else if (frame.isSpare()) {
    document.getElementById(currentFrameName).innerHTML = '/';
  } else {
    document.getElementById(currentFrameName).innerHTML = frame.rolls[frame.rolls.length - 1];
  }

  // Update frame scores.
  let scoreStack = [];
  updateRunningTotal(frames, scoreStack);
}

/**
 * Recursively goes into previous frames to calculate strikes and spares.
 */
function updateRunningTotal(frames, scoreStack) {
  let frame = frames.pop();

  if (!frame) {
    return 0;
  } else if (frame.hasRunningTotal()) {
    return Number(frame.runningTotal);
  }

  for (let i = frame.rolls.length - 1; i >= 0; i--) {
    scoreStack.push(Number(frame.rolls[i]));
  }

  let runningTotal = updateRunningTotal(frames, scoreStack);

  for (let i = 0; i < frame.rolls.length; i++) {
    scoreStack.pop();
  }

  if (frame.frameNum == 10 && frame.isCompleted()) {
    let frameScore = 0;
    for (let i = 0; i < frame.rolls.length; i++) {
      frameScore += frame.rolls[i];
    }
    runningTotal = runningTotal + frameScore;
    document.getElementById(`Frame${frame.frameNum}_score`).innerHTML = runningTotal;
  } else if (frame.isSpare() && scoreStack.length >= 1) {
    let nextThrowScore = scoreStack[scoreStack.length - 1];
    runningTotal = runningTotal + 10 + nextThrowScore;
    document.getElementById(`Frame${frame.frameNum}_score`).innerHTML = runningTotal;
  } else if (frame.isStrike() && scoreStack.length >= 2) {
    let nextThrowScore = scoreStack[scoreStack.length - 1];
    let nextNextThrowScore = scoreStack[scoreStack.length - 2];
    runningTotal = runningTotal + 10 + nextThrowScore + nextNextThrowScore;
    document.getElementById(`Frame${frame.frameNum}_score`).innerHTML = runningTotal;
  } else if (frame.isCompleted() && !frame.isStrike() && !frame.isSpare()) {
    runningTotal = runningTotal + Number(frame.rolls[0]) + Number(frame.rolls[1]);
    document.getElementById(`Frame${frame.frameNum}_score`).innerHTML = runningTotal;
  }

  return runningTotal;
}

/***** Bowling frames *****/
function Frame(frameNum, rolls, runningTotal) {
  this.frameNum = frameNum;
  this.rolls = rolls;
  this.runningTotal = runningTotal;
}

Frame.prototype.isStrike = function() {
  return Number(this.rolls[0]) == 10;
};

Frame.prototype.isCompleted = function() {
  return isInt(this.rolls[1]) || Number(this.rolls[0]) == 10;
};

Frame.prototype.isSpare = function() {
  return isInt(this.rolls[1]) && (Number(this.rolls[0]) + Number(this.rolls[1])) == 10;
};

Frame.prototype.hasRunningTotal = function() {
  return isInt(this.runningTotal);
};

Frame.prototype.getSlot = function() {
  return (this.isCompleted() ? 2 : 1);
};

Frame.prototype.getPinsDown = function() {
  return (this.isCompleted() ? 0 : this.rolls[0]);
};

// TenthFrame subclasses generic Frame class.
function TenthFrame(frameNum, rolls, runningTotal) {
  Frame.call(this, frameNum, rolls, runningTotal);
}
TenthFrame.prototype = Object.create(Frame.prototype);
TenthFrame.prototype.constructor = TenthFrame;

TenthFrame.prototype.isCompleted = function() {
  if (this.rolls.length == 3 ||
      (isInt(this.rolls[0]) && isInt(this.rolls[1]) &&
       (Number(this.rolls[0]) + Number(this.rolls[1]) < 10))) {
    return true;
  }
  return false;
};

TenthFrame.prototype.isStrike = function() {
  return Number(this.rolls[this.rolls.length - 1]) == 10;
};

TenthFrame.prototype.getSlot = function() {
  return this.rolls.length;
};

TenthFrame.prototype.getPinsDown = function() {
  if (this.isCompleted()) {
    return 11; // Disable 0th pin too.
  } else if (this.isStrike() || this.isSpare()) {
    return 0;
  } else {
    return this.rolls[this.getSlot() - 1];
  }
};

function createFrame(frameNum, rolls, runningTotal) {
  if (frameNum == 10) {
    return new TenthFrame(frameNum, rolls, runningTotal);
  } else {
    return new Frame(frameNum, rolls, runningTotal);
  }
}

/***** Utility functions *****/

/**
 * Clear scoresheet and reset pins.
 */
function reset() {
  for (let i = 1; i <= 10; i++) {
    for (let j = 1; j <= 2; j++) {
      document.getElementById(`Frame${i}_${j}`).innerHTML = null;
    }
  }
  for (let i = 1; i <= 10; i++) {
    document.getElementById(`Frame${i}_score`).innerHTML = null;
  }

  document.getElementById('Frame10_3').innerHTML = null;

  let frames = [];
  frames.push(createFrame(1, [0], null));
  refreshPins(frames);
}

function isInt(value) {
  let x;
  if (isNaN(value)) {
    return false;
  }
  x = parseFloat(value);
  return (x | 0) === x;
}
