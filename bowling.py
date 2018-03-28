#! /usr/bin/env python2
"""
Create a program which, given a valid sequence of rolls for one line of American Ten-Pin Bowling, produces the total score for the game. Fork this repository, build your program in the language of your choice, then submit a pull request with your code.
"""
input = raw_input('Enter the bowling score: ')
line = list(''.join(input.split()))
score = 0

for i in range(len(line)):
    if line[i] == 'X':
        line[i] = 10
    elif line[i] == '-':
        line[i] = 0
    elif line[i] == '/':
        continue
    else:
        line[i] = int(line[i])

for x in range(len(line)):
    ball = line[x]
    if len(line) - 3 <= x:
        if ball == '/':
            score += (10 - line[x-1])
        else:
            score += ball
        continue
    elif ball == 10:
        score += ball
        score += line[x+1]
        if line[x+2] == '/':
            score += (10 - line[x+1])
        else:
            score += line[x+2]
    elif ball == '/':
        score += (10 - line[x-1])
        score += line[x+1]
    else:
        score += ball

print score
