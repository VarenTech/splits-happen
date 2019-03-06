package com.bowling;

public class GameDemo {
    public static void main(String[] args) {
        Game gameDemo = Game.getInstance();

        gameDemo.loadBowlingGamesFromFile( args );
        for( String game : gameDemo.games ) {
            System.out.println("Bowling Game Input : " + game );
            gameDemo.formatGameScores( game );
            System.out.println("Bowling Game Score : " + gameDemo.score() + "\n");
        }
    }
}
