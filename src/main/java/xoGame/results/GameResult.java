package xoGame.results;

import xoGame.ScoreBoard;

public class GameResult {

    private ScoreBoard scoreBoard;

    public GameResult(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public Results getResult() {
        return Results.X;
    }


    @Override
    public String toString() {
        switch (getResult()) {
            case X:
                return "Player 'X' wins";
            case Y:
                return "YYYYYY wins";
            case DRAW:
                return "Fucking draw";
            default:
                return "bleee";
        }
    }
}
