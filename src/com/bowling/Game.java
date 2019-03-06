package com.bowling;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game game = new Game();

    private int score;
    private int[] rolls = new int[21];

    public List<String> games = new ArrayList<>();

    private Game() { }

    public static Game getInstance( ) {
        return game;
    }

    public void loadBowlingGamesFromFile( String[] args ) {
        if (args == null || args.length == 0) {
            System.out.println("Please specify a filename.");
            System.exit(1);
        }

        File file = new File(args[0]);

        if ( !file.exists() ) {
            System.out.println("File does not exist, please specify a filename.");
            System.exit(2);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;

            int index = 0;
            while ( (line = br.readLine()) != null ) {
                games.add( line );
            }
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void formatGameScores(String game) {
        String[] results;
        results = game.split("(?!^)");

        for (int i = 0; i < results.length; i++) {
            try {
                if (  results[i].equalsIgnoreCase("X") ) {
                    rolls[i] = 10;
                }
                else if ( results[i].equals("/") ) {
                    rolls[i] =  10 - Integer.parseInt( results[i - 1] );
                }
                else if ( results[i].equals("-") ) {
                    rolls[i] =  0;
                }
                else {
                    rolls[i] = Integer.parseInt(results[i]);
                }
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            };
        }
    }

    public int score() {
        score = 0;
        int rollIndex = 0;
        for ( int frame = 0; frame < 10; frame++ ) {
            if ( isStrike(rollIndex) ) {
                score += 10 + rolls[rollIndex + 1] + rolls[rollIndex + 2];
                rollIndex++;
            }
            else if ( isSpare(rollIndex) ) {
                score += 10 + rolls[rollIndex + 2];
                rollIndex += 2;
            }
            else {
                score += rolls[rollIndex] + rolls[rollIndex + 1];
                rollIndex += 2;
            }
        }
        rolls = new int[21];
        return score;
    }

    private boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    private boolean isSpare(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex+1] == 10;
    }
}

