import java.util.*;

public class BowlingScore {

    public static void main(String [] args){
        //Initlization of Example Arrays
        String rolls1 = "XXXXXXXXXXXX";
        String rolls2 = "9-9-9-9-9-9-9-9-9-9-";
        String rolls3 = "5/5/5/5/5/5/5/5/5/5/5";
        String rolls4 = "X7/9-X-88/-6XXX81";

        //Arrays passed into the Bowling Rolls Object
        BowlingRolls r1 = new BowlingRolls(rolls1);
        BowlingRolls r2 = new BowlingRolls(rolls2);
        BowlingRolls r3 = new BowlingRolls(rolls3);
        BowlingRolls r4 = new BowlingRolls(rolls4);

        //Char Characters converted to Integer values that are used to get/print Raw Score
        System.out.println(r1.getTrueScore());
        System.out.println(r2.getTrueScore());
        System.out.println(r3.getTrueScore());
        System.out.println(r4.getTrueScore());
    }
}
