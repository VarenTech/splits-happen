/**
 * 
 */
package bowling;

import java.util.List;

/**
 * @author Jason
 *
 */
final class Game {
	private List<PlayerScore> playersScores;
	
	Game(List<PlayerScore> playersScores){
		this.playersScores = playersScores;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Frame Number|\t\t1 \t\t|\t\t2 \t\t|\t\t3 \t\t|\t\t4 \t\t|\t\t5 \t\t|\t\t6 \t\t|\t\t7 \t\t|\t\t8 \t\t|\t\t9 \t\t|\t\t10\t\t|\t Bonus \t|\tFinal Game Score|\r\n");
		for(PlayerScore playerScore : playersScores){
			sb.append(playerScore.toString());
		}
		return sb.toString();
	}
}
