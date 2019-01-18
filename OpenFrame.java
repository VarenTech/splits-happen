/* Represents an open frame where not all pins are hit */
public class OpenFrame extends Frame {

    private int firstRoll;
    private int secondRoll;

    public OpenFrame(int firstRoll, int secondRoll){

        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    //Score for an open frame is the total number of pins hit in the two rolls
    public int getScore(){

        return firstRoll + secondRoll;
    }
}
