package xoGame.results;

import xoGame.components.ScoreBoard;

public class GameResult {

    private ScoreBoard scoreBoard;

    public GameResult(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public Results getResult() {
        return scoreBoard.getCurrentGameResult();
    }


    @Override
    public String toString() {
        switch (getResult()) {
            case X:
                return "Player X has won the game!";
            case Y:
                return "Player X has won the game!";
            case DRAW:
                return "Draw in the game";
            default:
                return "Something went wrong";
        }
    }
}
