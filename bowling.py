"""This script analyzes a bowling score.  The bowling attempts
   are passed in as a string.  The user can see the score by calling
   show_score"""

import sys
import re

MAXPINS = 10
MAXFRAMES = 10


class Bowling:
    ''' declaration of class Bowling'''
    __input_score = ""
    __score = 0

    def __init__(self, initScore):
        self.__input_score = list(initScore)
        self.bowling()

    def bowling(self):
        ''' main method to analyze bowling score.  Always iterate to
            maximum number of frames. '''

        for i in range(MAXFRAMES):
            if i == int(MAXFRAMES - 1):
                self.last_frame()
                continue
            first = self.show_next_bowl()
            if first == MAXPINS:
                self.handle_strike()
                continue
            second = self.show_next_bowl()
            if second == MAXPINS:
                self.handle_spare()
                continue
            self.add_score(first + second)

    @classmethod
    def analyze_bowl(cls, ana):
        ''' returns bowl attempt analysis.  this method requires a
        numeric value returned.  NOTE the "-" is not analyzed.  This
        can be any equivalent to a non-numermic character outside of X/
        and all default to 0'''

        if ana == "X" or ana == "/":
            return MAXPINS
        if re.match("^\\d$", ana):
            return int(ana)
        return 0

    def handle_strike(self):
        ''' If user has a strike, look ahead (not pop) two attempts to
		return score.  Default to zero.'''

        try:
            first = self.analyze_bowl(self.__input_score[0])
        except IndexError:
            first = 0

        try:
            second = self.analyze_bowl(self.__input_score[1])
        except IndexError:
            second = 0

        if second == MAXPINS and first != MAXPINS:
            self.add_score(MAXPINS + MAXPINS)
        else:
            self.add_score(MAXPINS + first + second)

    def handle_spare(self):
        ''' if user has a spare, look ahead one attempt to return score.
        Default to zero.'''

        try:
            first = self.analyze_bowl(self.__input_score[0])
        except IndexError:
            first = 0
        self.add_score(MAXPINS + first)

    def add_score(self, value):
        ''' add value to score '''

        self.__score += value

    def show_next_bowl(self):
        ''' This pops the head value in the input string and passed
		popped value into analysis.  Returns what is returned from
		analyze_bowl, or 0 '''

        try:
            return self.analyze_bowl(self.__input_score.pop(0))
        except IndexError:
            return 0

    def show_score(self):
        ''' Show score '''

        return self.__score

    def last_frame(self):
        ''' Analyze last frame.  '''

        first = self.show_next_bowl()
        second = self.show_next_bowl()
        third = 0
        if second == MAXPINS and first < MAXPINS:
            first = 0
        if first == MAXPINS or second == MAXPINS:
            third = self.show_next_bowl()
            if third == MAXPINS and second < MAXPINS:
                second = 0
        self.add_score(first + second + third)


if __name__ == "__main__":
    try: 
        STARTBOWLING = Bowling(str(sys.argv[1]))
        print(STARTBOWLING.show_score())
    except:
        print("python3 bowling.py <string> is required")
