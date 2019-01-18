/* Represents a spare: stores first and second roll and the next roll (in the next frame) as a bonus */
public class SpareFrame extends Frame {

    private int firstRoll;
    private int secondRoll;
    private int bonusRoll;

    public SpareFrame(int firstRoll, int secondRoll, int bonusRoll){

        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
        this.bonusRoll = bonusRoll;
    }

    /* Score for a spare frame is the number of pins hit (firstRoll + secondRoll) + number of pins hit in next roll
    (bonusRoll)
     */
    public int getScore(){

        return firstRoll + secondRoll + bonusRoll;
    }
}
