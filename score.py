#! /usr/bin/env python3

# This file is the code to complete the coding challenge
#	The code is written in Python3
#	Usage: 	python3 score.py <game-line>
#		OR 	python3 score.py [And enter line when prompted]

import sys

def main():
	# Verify that we have enough arguments (otherwise get input from command line)
	if len(sys.argv) < 2:
		# Nothing found on command line, prompt for input
		game = "X7/9-X-88/-6XXX81"
		#game = input("Please input the game line:")
	else:
		# Receive the input from the command line
		game = sys.argv[1]

	# Now we have the game line as a string in the variable game

	# Score accumulation variable
	score = 0

	# Rather than loop through the characters of the string, we loop through 10 frames
	#	We also maintain an index of where we are in the string
	#	We assume, as per the instructions, that the input is well formed

	index = 0
	for frame in range(1, 11):
		# If a strike was thrown in the current frame
		if(game[index] == "X"):
			# Increment the score accordingly for this frame
			score += 10
			# If a spare was thrown next, only add an additional 10 points
			if(game[index+2] == "/"):
				score += 10
			# Otherwise, we can safely add the values of the next 2 balls
			else:
				score += (point_total(game[index+1]) + point_total(game[index+2]))
			# We have scored this frame, and we only used 1 character, so update index
			index += 1
		# If the current frame is not a strike but a spare
		elif(game[index+1] == "/"):
			# Increment the score accordingly for this frame
			score += 10
			# We know that the next throw has a value, so just add it in
			score += point_total(game[index+2])
			# We are done scoring the frame, move on (2 characters)
			index += 2
		# Neither a strike nor a spare
		else:
			# Increment score for both throws
			score += (point_total(game[index]) + point_total(game[index+1]))
			# Increment index since we are done (2 characters)
			index += 2

	# Print out the final result
	print(score)

# This is a pretty basic helper function to get the value of a specific throw
#	NOTE: This doesn't include spares by design (since they don't have an explicit
#			value per throw, instead it is variable)
def point_total(c):
	return "-123456789X".index(c)

main()