import java.util.*;
import java.io.*;

public class Bowl
{
  // declare constants
  public static final char STRIKE = 'X';
  public static final char SPARE  = '/';
  public static final char MISS   = '-';
  public static final int STRIKE_VALUE = 10, SPARE_VALUE = 10;
  public static final int LAST_FRAME = 10;  // number of frames in a game
  public static final int FILE_LENGTH = 4;  // number of lines in file
  
  
  public static void main (String [] args)
  {
  
  int currentScore = 0;     // running score of bowling

  
  // read data from file into array of balls thrown

   
  //set up for input
      File infile = new File("bowling.txt");
      Scanner scan = null;
      
      // declare string to hold balls thrown
      String ballsThrown = "";
            
     
      try {
         scan = new Scanner(infile);
      }
      catch (IOException e) {
         System.out.println(" no file found");
         System.exit(0);
      } 
      for (int i = 0; i< FILE_LENGTH; i++)
     
      {
         // reinitialize score before processing next line of file
         currentScore = 0;    
         // read a line from file into a string
         ballsThrown = scan.nextLine();   
	     
	      // get score for current game
         currentScore = processFrames(ballsThrown);
           
       // output the bowling record  
       displayThrownBalls(ballsThrown);
       
       displayScore(currentScore);
      
     } // end for
	  
	  
  } // end main
  
  
  ///////////////////////////////////////////////////////////////////
  // Function name : displayThrownBalls
  // Input : string containing record of all balls thrown in a game
  // Output : String displayed on screen
  ///////////////////////////////////////////////////////////////////

  public static void displayThrownBalls(String displayString)
  {
   System.out.println("These were the balls thrown");
   System.out.println(displayString);
  }
  
  ///////////////////////////////////////////////////////////////////
  // Function name : displayScore
  // Input : Score of game in integer format
  // Output : Game score displayed on screen
  ///////////////////////////////////////////////////////////////////
  public static void displayScore(int score)
  {
   System.out.println(String.format("The final score is %d ", score));
   System.out.println();
  }
  
   
  ///////////////////////////////////////////////////////////////////
  // Function name : processFrames
  // Input : string containing the balls bowled
  // Output : updated score
  // This function will calculate the score for an entire game
  ///////////////////////////////////////////////////////////////////

  public static int processFrames(String ballArray)
  {
	  int score = 0;
     char firstBall;      // holds value of first ball thrown in a frame
     char secondBall;     // holds value of second ball for a frame
     int ballIndex = 0;   // current location within the ballArray

     
      for (int j = 1; j <= LAST_FRAME; j++)
         {
           firstBall = ballArray.charAt(ballIndex);
           
           // when first ball of frame is a strike, call processStrike for scoring updates
           if (firstBall == STRIKE) 
             {
             score = processStrike(score,ballArray,ballIndex);
             ballIndex++;
             }
          else 
          { 
            // if a spare is bowled, call processSpare for scoring updates
            secondBall = ballArray.charAt(ballIndex+1);
            if (secondBall == SPARE) 
               score = processSpare(score,ballArray,ballIndex+1);
               
            // else not a strike or spare -- just add the score from the 2 balls thrown
            else 
            {
              
               score = addNextBall(score,ballArray,ballIndex);
    
               score = addNextBall(score,ballArray,ballIndex+1);
            
             } // end else
           ballIndex = ballIndex+2;
           } // end else   
                  
         } // end for
	 
     // return game score     
	  return score;  
	  
  } // end processFrames

  ///////////////////////////////////////////////////////////////////
  // Function name : processStrike
  // Input :  integer containing current score
  //          string containing the balls bowled
  //          integer ballIndex containing the index to the Strike being processed
  // Output : updated score
  ///////////////////////////////////////////////////////////////////
    public static int processStrike(int scoreIn, String ballArray, int ballIndex)
    {
    int score = scoreIn;
    int secondBall;
    
    // add strike value and next 2 ball values
    score = score + STRIKE_VALUE;
    
    secondBall = ballArray.charAt(ballIndex+2);
    
    // if spare follows strike, add value of spare        
    if (secondBall == SPARE) 
    {
      score = score + SPARE_VALUE;
    }
    
    // else just add contents of next 2 balls to score
    else 
    {
      score = addNextBall(score,ballArray,ballIndex+1);
    
      score = addNextBall(score,ballArray,ballIndex+2);   
    }
    
    return(score);
    
    } // end processStrike
    
  ///////////////////////////////////////////////////////////////////
  // Function name : processSpare
  // Input :  integer containing current score
  //          string containing the balls bowled
  //          integer ballIndex containing the index to the spare being processed
  // Output : updated score
  ///////////////////////////////////////////////////////////////////  
    public static int processSpare(int scoreIn, String ballArray, int ballIndex)
    {
    int score = scoreIn;
    
    // add spare value and next 1 ball values
    score = score + SPARE_VALUE;
    
    score = addNextBall(score,ballArray,ballIndex+1);
    
    return score;
    } // end processSpare
   
   
  ///////////////////////////////////////////////////////////////////
  // Function name : addNextBall
  // Input :  integer containing current score
  //          string containing the balls bowled
  //          integer ballIndex containing the index to the ball being processed
  // Output : updated score
  ///////////////////////////////////////////////////////////////////     
   public static int addNextBall(int scoreIn, String ballArray, int ballIndex)
  {
      int score = scoreIn;
      
      char newBall = ballArray.charAt(ballIndex);
      
      // if next ball is strike, add appropriate value
      if ( newBall == STRIKE )
      
         score = score + STRIKE_VALUE;
      
      // else if next ball is not a miss (miss has no score), add value of fallen pins   
      else if (newBall != MISS)
         score = score + Character.getNumericValue(newBall);
         
      return score;  // end addNextBall
  }
}