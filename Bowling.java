import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Main class: Reads all input from input.txt and writes all scores from input into output.txt */
public class Bowling {

    public static void main(String[] args){

        Scanner scanner = null;
        List<String> output = new ArrayList<>();

        try {

            scanner = new Scanner(new File("input.txt"));

            //Read all input from input.txt and calculates the score for each scoresheet (line of input)
            while(scanner.hasNextLine()){

                output.add(String.valueOf(new ScoreSheet(scanner.nextLine().trim()).getTotalScore()));
            }

        }catch(FileNotFoundException e){

            e.printStackTrace();
        }

        FileWriter writer = null;
        try {

            //Writes scores to output.txt
            writer = new FileWriter("output.txt");
            writer.write(String.join("\n", output));
            writer.close();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

}

