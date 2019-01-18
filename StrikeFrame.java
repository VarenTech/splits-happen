/* Represents a strike frame. Stores the number of pins hit in the next two rolls after the strike */
public class StrikeFrame extends Frame {

    private int firstBonusRoll;
    private int secondBonusRoll;

    public StrikeFrame(int firstBonusRoll, int secondBonusRoll){

        this.firstBonusRoll = firstBonusRoll;
        this.secondBonusRoll = secondBonusRoll;
    }

    //Score for a strike frame is the total number of pins per frame + the sum of the next two rolls
    public int getScore(){

        return ScoreSheet.PINS_PER_FRAME + firstBonusRoll + secondBonusRoll;
    }

}
