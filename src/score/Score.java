package score;

import java.util.ArrayList;

public class Score {

    private String line;
    private ArrayList<Integer> bowls;

    public Score(String line) {
        this.line = line;
        bowls = new ArrayList<>();
    }

    private void parseLine() {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'X')
                bowls.add(10);
            else if (line.charAt(i) == '-')
                bowls.add(0);
            else if (line.charAt(i) == '/')
                bowls.add(10 - Character.getNumericValue(line.charAt(i - 1)));
            else
                bowls.add(Character.getNumericValue(line.charAt(i)));
        }
    }

    private boolean isStrike(int roll) {
        return roll == 10;
    }

    private boolean isSpare(int frame) {
        return (bowls.get(frame) + bowls.get(frame + 1)) == 10;
    }

    public int findFinalScore() {
        parseLine();
        int score = 0;
        int frame = 0;
        for (int i = 0; i < 10; i++) {
            if (isStrike(bowls.get(frame)))
                score += 10 + bowls.get(frame + 1) + bowls.get(frame + 2);
            else if (isSpare(frame)) {
                score += 10 + bowls.get(frame + 2);
                frame++;
            } else {
                score += bowls.get(frame) + bowls.get(frame + 1);
                frame++;
            }
            frame++;
        }
        return score;
    }

}