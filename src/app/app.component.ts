import {Component, OnInit} from '@angular/core';
import {forEach} from '@angular/router/src/utils/collection';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  gameCtrl = new FormControl();
  title = 'bowling score generator';
  TESTS: string[] = [
    'XXXXXXXXXXXX',
    '9-9-9-9-9-9-9-9-9-9-',
    '5/5/5/5/5/5/5/5/5/5/5',
    'X7/9-X-88/-6XXX81'
  ];

  scores: any[] = [];

  constructor() {
  }

  ngOnInit(): void {
    // testing scores
   /* for (const game of this.TESTS) {
      const score = this.scoreGame(game);
      this.scores.push({game: game, score: score});
    }*/
  }

  submit(): void {
    const score = this.scoreGame(this.gameCtrl.value);
    this.scores.push({game: this.gameCtrl.value, score: score});
  }

  clear(): void {
    this.scores = [];
  }
  /*
  * Thus function converts the string into an array of array. Each array is a frame.
  * it also handles the final frame and bonus rolls
  * */
  parseGame(game: string): string[] {
    const parsedArr: any[] = [];
    const gameArr = Array.from(game.replace(/-/g, '0')); // convert '-' to 0
    while (gameArr.length > 0) {
      const temp = gameArr.shift();
      if (temp === 'X') {
        // there could be strikes or spares here
        let last: any = gameArr[1];
        // convert spare
        if (last === '/') {
          last = 10 - +gameArr[0]; // if the last one is a spare, then the middle one is definitely an integer
        }
        // for the last 2 extra rolls, add them to the last frame
        if (parsedArr.length >= 9) {
          parsedArr.push([temp, gameArr.shift(), gameArr.shift()]);
        } else {
          parsedArr.push([temp, gameArr[0], last]);
        }
      } else {
        // this should work for all other cases, if a bowler scores a number, the next frame has to be a number or spare
        const roll = gameArr.shift();
        if (roll === '/') {
          const diff = 10 - +temp;
          // this converts the '/' character to an integer (probably not necessary), and copies the next roll, not frame
          // for the last 2 extra rolls, add rolls as a separate frame
          if (parsedArr.length >= 9) { // last roll is a spare-- gets 1 extra roll
            parsedArr.push([temp, diff, gameArr.shift()]); // add the bonus roll for a spare
          } else {
            parsedArr.push([temp, diff, gameArr[0]]);
          }
        } else {
          if (parsedArr.length >= 9) {
            // if bonus roll is an integer, not sure if it was from a spare or strike, so put in separate frames
            parsedArr.push([temp]);
          } else {
            parsedArr.push([+temp, +roll]);
          }
        }
      }
    }
    return parsedArr;
  }

  // convert 'X' character to 10
  adjustStrikes(game: any[]): any[] {
    game.forEach((frame, index) => {
      game[index] = frame.map((roll) => roll === 'X' ? roll = 10 : roll = +roll);
    });
    // flatten array
    return game;
  }

  // takingthis out og ngInit makes it easier to test
  scoreGame(game: string): number {
    let score = 0;
    const gameArr = this.parseGame(game);
    const scoreableArr = [].concat(...this.adjustStrikes(gameArr)); // flatten the array
    scoreableArr.forEach((val) => {
      score += val;
    });
    return score;
  }
}
