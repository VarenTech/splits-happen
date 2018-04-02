/**
 * 
 */
package bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * @author Jason Corekin
 *
 */
class Record {
	JSONObject jsonRecord;
	JSONParser jsonParser;
	private static final Lock lock = new ReentrantLock();
	Record(String orignalString){
		
		lock.lock();
		try {
			jsonParser = new JSONParser();
			this.jsonRecord = (JSONObject) jsonParser.parse(orignalString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	@Override
	public String toString(){
		List<PlayerScore> playersScores = new ArrayList<>();
		List<Frame> frames;
		String throw1, throw2;
		long finalGameScore;
		PlayerScore playerScore;
		JSONArray playersScoresJSONArray = (JSONArray) jsonRecord.get("gameArray");
		Iterator<JSONObject> iterator = playersScoresJSONArray.iterator();
		int playerNumber = 0;
		while (iterator.hasNext()) {
			JSONObject playersScoresObject = iterator.next();
			JSONArray playerScoresJSONArray = (JSONArray) playersScoresObject.get("scoresArray");
			finalGameScore = (Long) playersScoresObject.get("finalGameScore");
			Iterator<JSONObject> playerIterator = playerScoresJSONArray.iterator();
			frames = new ArrayList<>();
			while (playerIterator.hasNext()) {
				JSONObject frameObject = (JSONObject) playerIterator.next();
				throw1 = (String) frameObject.get("throw1");
				throw2 = (String) frameObject.get("throw2");
				frames.add(new Frame(throw1,throw2));
			}
			playerScore = new PlayerScore(++playerNumber, frames, finalGameScore);
			playersScores.add(playerScore);
        }
		
		Game game = new Game(playersScores);
		return game.toString();
	}
	

}
