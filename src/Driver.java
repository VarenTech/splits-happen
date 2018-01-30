import java.util.HashMap;
import java.util.Scanner;

public class Driver {

    /**
     * Create a Map that maps characters to an integer value
     * @return a HashMap with appropriate keys->values
     */
    private static HashMap<Character, Integer> setUp() {
        HashMap<Character, Integer> values = new HashMap();
        values.put('1',1);
        values.put('2', 2);
        values.put('3', 3);
        values.put('4', 4);
        values.put('5', 5);
        values.put('6', 6);
        values.put('7', 7);
        values.put('8', 8);
        values.put('9', 9);
        values.put('X', 10);
        values.put('-', 0);
        return values;
    }

    /**
     * Get keyboard input and map it to the corresponding values
     * @return an integer array with bowling scores
     */
    private static int[] getGameInput() {
        Scanner sc = new Scanner(System.in);
        //just in case strikes are lower case x
        String input = sc.nextLine().toUpperCase();

        char[] inputArray = input.toCharArray();
        HashMap values = setUp();

        int[] gameInput = new int[inputArray.length];

        for (int i=0; i < inputArray.length; i++){
            if (inputArray[i] == '/'){
                //replace the / with remainder pins
                gameInput[i] = (10 - (int) values.get(inputArray[i-1]));
            } else {
                //convert all chars to integers
                gameInput[i] = (int) values.get(inputArray[i]);
            }
        }
        return gameInput;
    }


    public static void main(String ... args) {
        int[] gameInput = getGameInput();

        Game g = new Game(gameInput);
        System.out.println("Final Score: " + g.score());
    }
}
