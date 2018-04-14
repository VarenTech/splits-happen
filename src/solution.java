/* Name: Neil Pasricha
 * Program: Splits-Happen (Design and Development Challenge - Bowling Score)
 * Date: 04/14/2018
 * Email: nvp5119@psu.edu
 */
import java.util.*;
public class solution {

	String line;
	
	//If there is a strike, add 10 plus the next two bowls
	public int strike(int nextOne, int nextTwo) {
		//Base total of 10
		int total=10;
		//Add the next two moves to 10 for the total
		total+=nextOne+nextTwo;
		return total;
	}
	
	//If there is a spare, add 10 plus the next bowl
	public int spare(int nextOne) {
		//Base total of 10 (if it's a spare the spare val will be 10-prev)
		int total=10;
		//Add 10 to the next move
		total+=nextOne;
		return total;
	}
	
	//Convert the chars to their values, if it is a spare it will be dealt with differently
	public int reType(char x) {
		int val=0;
		//If X it's val is 10
		if(x=='X') {
			val=10;
		}
		//If its - it's val is 0
		else if(x=='-') {
			val=0;
		}
		//If not X, -, or / get the numeric val
		else if(x!='/'){
			val=Character.getNumericValue(x);
		}
		
		return val;
	}
	//Cycles through the input of char array, where each index
	//represents a bowl move, and adds the total of the score to tot.
	
	public int bowl(char[] lineAr) {
		//Running total
		int tot=0;
		//Handlers for strike and spare
		boolean spare=false,strike=false;
		
		for(int i=0;i<lineAr.length;i++) {
			if(lineAr[i]=='X') {
				strike=true;
				//Handle it for the extra frames
				if(i+2<lineAr.length) {
				if(lineAr[i+2]=='/') {
					tot+=strike(reType(lineAr[i+1]), 10-reType(lineAr[i+1]));
				}else {
				tot+=strike(reType(lineAr[i+1]), reType(lineAr[i+2]));
				}
				}
				
				strike=false;
			}
			//If spare
			else if(lineAr[i]=='/') {
				spare=true;
				tot+=spare(reType(lineAr[i+1]));
				spare=false;
			}
			//If not a spare or strike
			else if(!spare&&!strike){
				if(i+1<lineAr.length) {
					
				if(lineAr[i+1]=='/'||lineAr[i+1]=='X'&&!(lineAr[i-1]=='-')) {
				}else {

					if((i+2)<lineAr.length) {
					tot+=reType(lineAr[i]);
					}
					else if(lineAr[i-1]=='-') {
						tot+=reType(lineAr[i]);
					}
				}
			}
			}
		}
		
		
		
		return tot;
	}
	
	public static void main(String[] args) {
		//Initialize solution
		solution sn = new solution();
		//Initialize the scanner for user input
		Scanner scan = new Scanner(System.in);
		//Take the line in as a String
		String line = scan.nextLine();
		//Split it by each move into a char array
		char[] lineAr = line.toCharArray();
		//Close the Scanner
		scan.close();
		
		//Grab the output from bowl()
		int output=sn.bowl(lineAr);
		//Print the output
		System.out.println(output);
		
		
	}
	
}
