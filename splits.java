public class splits {

	public static int calculate(String args) {
		String[] rolls = args.split("");
		int i;
		int score = 0;
		String regex = "[0-9]";
		for(i = 0; i < rolls.length; i+=2)
		{
			String rollOne =  rolls[i];
			
			if(rollOne.equals("X")){
				if(i+1 < rolls.length){
					String rollTwo = rolls[i+1];
					
					if(rollTwo.equals("X")){
						if(i+2 < rolls.length){
							String rollThree = rolls[i+2];
							if(rollThree.equals("X")){
								score += 30;
								--i;
							}
							else if(rollThree.matches(regex)){
								
								score += Integer.parseInt(rollThree) + 20;
							}
							else if (rollThree.equals("-")){
								score += 20;
							}
						}
					}
				
					else if(rollTwo.matches(regex)){
						if(i+2 < rolls.length){
							String rollThree = rolls[i+2];
							if(rollThree.matches(regex)){
								score += Integer.parseInt(rollThree) + 10 + Integer.parseInt(rollTwo);
							}
							else if (rollThree.equals("-")){
								score += Integer.parseInt(rollTwo)*2 + 10;
							}
							
							else if (rollThree.equals("/")){
								score += 20;
								
							}
						}
					}
					
					else if(rollTwo.equals("-")){
						if(i+2 < rolls.length){
							String rollThree = rolls[i+2];
							
							if(rollThree.matches(regex)){
								score += Integer.parseInt(rollThree)*2 + 10;
							}
							else if (rollThree.equals("-")){
								score += Integer.parseInt(rollThree)*2 + 10;
							}
							else if (rollThree.equals("/")){
								score += 20;
							}
						}
					}
				}
			}
			
			if(rollOne.matches(regex)){
				if(i+1 < rolls.length){
					String rollTwo = rolls[i+1];
					if(rollTwo.matches(regex)){
						score += Integer.parseInt(rollOne) + Integer.parseInt(rollTwo);
					}
					
					else if(rollTwo.equals("/")){
						if(i+2 < rolls.length){
							String rollThree = rolls[i+2];
							
							if(rollThree.matches(regex)){
								score += Integer.parseInt(rollThree) + 10;
							}

							else if (rollThree.equals("-")){
								score += 10;
							}
						}
					}
					
					if(rollTwo.equals("X")){
						if(i+2 < rolls.length){
							String rollThree = rolls[i+2];
							
							if(rollThree.equals("X")){
								score += 30;
							}

							else if(rollThree.matches(regex)){
								score += Integer.parseInt(rollThree) + 20;
							}
							
							else if (rollThree.equals("-")){
								score += 20;
							}
						}
					}
					
					else if(rollTwo.equals("-")){
						score += Integer.parseInt(rollOne);
					}
				}
			}

			if(rollOne.equals("-")){
				if(i+1 < rolls.length){
					String rollTwo = rolls[i+1];
				
					if(rollTwo.matches(regex)){
						score += Integer.parseInt(rollTwo);
					}
					
					else if(rollTwo.equals("/")){
						if(i+2 < rolls.length){
							String rollThree = rolls[i+2];

							if(rollThree.matches(regex)){
								score += Integer.parseInt(rollThree) + 10;
							}
						}
					}
				}
			}
			
			if(rollOne.equals("/")){
				if(i+1 < rolls.length){
					String rollTwo = rolls[i+1];
					if(rollTwo.equals("X")){
						score += 20;
					}
					else if(rollTwo.matches(regex)){
						score += Integer.parseInt(rollTwo) + 10;
						--i;
					}
					else if(rollTwo.equals("-")){
						score += 10;
					}
				}
				
			}
				
		}
		return score;
	}
}



