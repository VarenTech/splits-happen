import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* Represents the scoresheet for a particular bowling game */
public class ScoreSheet {

    private char[] rolls;
    private static final char STRIKE_SYMBOL = 'x';
    private static final char SPARE_SYMBOL = '/';
    private static final char MISS_SYMBOL = '-';
    protected static final int PINS_PER_FRAME = 10;
    protected static final int NUMBER_FRAMES = 10;

    public ScoreSheet(String data){

        this.rolls = data.toLowerCase().toCharArray();
    }

    //Helper function: returns the number of pins hit at a particular roll in the game
    private int getPins(int index){

        if(rolls[index] == STRIKE_SYMBOL){

            return PINS_PER_FRAME;
        }
        else if(rolls[index] == SPARE_SYMBOL){

            return PINS_PER_FRAME - Character.getNumericValue(rolls[index - 1]);
        }
        else if(Character.isDigit(rolls[index])){

            return Character.getNumericValue(rolls[index]);
        }
        else if(rolls[index] == MISS_SYMBOL){

            return 0;
        }

        throw new IllegalArgumentException();
    }

    //Returns an iterator for the current scoresheet
    public ScoreSheetIterator iterator(){

        return new ScoreSheetIterator();
    }

    //Returns all frames in scoresheet as list
    public List<Frame> getFrames(){

        ScoreSheetIterator iterator = iterator();
        List<Frame> frames = new ArrayList<>();
        while(iterator.hasNext()){

            frames.add(iterator.next());
        }


        return frames;
    }

    //Returns the total score of this scoresheet
    public int getTotalScore(){

        return getFrames().stream().mapToInt(Frame::getScore).sum();
    }

    //Iterator for scoresheet
    class ScoreSheetIterator implements Iterator<Frame> {

        private int frameIndex;
        private int index;

        public ScoreSheetIterator(){

            this.frameIndex = 0;
            this.index = 0;
        }

        public boolean hasNext(){

            return this.frameIndex < NUMBER_FRAMES;
        }

        //Returns the next frame in the iterator
        public Frame next(){

            frameIndex++;
            //If current frame is a strike, returns a strike frame with next two rolls as bonus points
            if(rolls[index] == STRIKE_SYMBOL){

                index++;
                return new StrikeFrame(getPins(index), getPins(index + 1));
            }
            //Else if current frame is a spare, returns a spare frame with next roll as a bonus
            else if(rolls[index + 1] == SPARE_SYMBOL){

                index += 2;
                return new SpareFrame(getPins(index - 2), getPins(index - 1), getPins(index));
            }
            //Else, returns an unfinished frame with the number of pins hit on the first and second roll
            else{

                index += 2;
                return new OpenFrame(getPins(index - 2), getPins(index - 1));
            }

        }

    }

}
