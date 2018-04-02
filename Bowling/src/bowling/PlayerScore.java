/**
 * 
 */
package bowling;

import java.util.List;

/**
 * @author Jason
 *
 */
final class PlayerScore {
	private int playerNumber;
	private List<Frame> frames;
	private long finalGameScore;
	
	PlayerScore(int playerNumber,List<Frame> frames, long finalGameScore2){
		this.playerNumber = playerNumber;
		this.frames = frames;
		this.finalGameScore = finalGameScore2;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Player ").append(playerNumber).append("\t\t");
		for(Frame frame: frames){
			sb.append(frame);
		}
		sb.append("|\t\t\t\t").append(finalGameScore);
		sb.append("\t\t\t\t|\r\n");
		return sb.toString();
	}
	
}
