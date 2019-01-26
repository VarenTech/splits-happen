import java.util.Arrays;
import java.util.List;

public class Bowling {


	public static void main(String[] args) {
		
		List<String> scores = Arrays.asList(
			"XXXXXXXXXXXX",
			"9-9-9-9-9-9-9-9-9-9-",
			"5/5/5/5/5/5/5/5/5/5/5",
			"X7/9-X-88/-6XXX81",
			"--------------------", 
			"8/549-XX5/53639/9/X",
			"7-25XX5318X42158/X", 
			"-/9/23456/32178/17X-/"
			);
		for(String score : scores)
			System.out.println("Input: " + score + ", Score: " + calculateScore(score));

	}
	
	public static int calculateScore(String scoringString) {
		
		int total = 0;
		int frameScore = 0;
		int frameCount = 1;
		int ballInFrame = 1;
		
		for(int i = 0; i < scoringString.length() && frameCount <= 10; i++) {
			
			//System.out.println("char: " + scoringString.charAt(i) + ", frameCount: " + (frameCount) + ", ballCount: " + ballInFrame );
			if (scoringString.charAt(i) == 'X') {
				frameScore=10;
				if (scoringString.charAt(i+2) == 'X') {
					frameScore+=20;
				}
				else if (scoringString.charAt(i+2) == '/') {
					frameScore+=10;
				}
				else if (scoringString.charAt(i+1) == 'X') {
					frameScore+=10;
					int next = Character.getNumericValue(scoringString.charAt(i+2));
					next = (next > 0) ? next : 0;
					frameScore+=next;
				}
				else {
					int next = Character.getNumericValue(scoringString.charAt(i+1));
					next = (next > 0) ? next : 0;
					int second = Character.getNumericValue(scoringString.charAt(i+2)); 
					second = (second > 0) ? second : 0;
					frameScore += (next + second);
				}
				
				frameCount++;
				total += frameScore;
				frameScore = 0;
				ballInFrame = 1;				
			}
			
			else if (scoringString.charAt(i) == '/') {
				frameScore=10;
				if (scoringString.charAt(i+1) == 'X') {
					frameScore+=10;
				}
				else {
					int next = Character.getNumericValue(scoringString.charAt(i+1));
					next = (next > 0) ? next : 0;
					frameScore += next;
				}
				frameCount++;
				total+= frameScore;
				frameScore = 0;
				ballInFrame = 1;
			}
			
			else {
				int current = Character.getNumericValue(scoringString.charAt(i));
				current = (current > 0) ? current : 0;
				frameScore+=current;
				if (ballInFrame++ == 2) {
					frameCount++;
					total += frameScore;
					frameScore = 0;
					ballInFrame = 1;
				}
			}
			//System.out.println("total: " + total);
		}
		return total;	
	}
}
