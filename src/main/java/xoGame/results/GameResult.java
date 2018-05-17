package xoGame.results;

import xoGame.components.ScoreBoard;

import java.util.Properties;

public class GameResult {

    private ScoreBoard scoreBoard;
    private final Properties language;

    public GameResult(ScoreBoard scoreBoard, Properties language) {
        this.scoreBoard = scoreBoard;
        this.language = language;
    }

    public Results getResult() {
        return scoreBoard.getCurrentGameResult();
    }


    @Override
    public String toString() {
        switch (getResult()) {
            case X:
                return language.getProperty("playerXwonGame","Player X has won the game!");
            case O:
                return language.getProperty("playerOwonGame","Player O has won the game!");
            case DRAW:
                return language.getProperty("drawInGame","Draw in the game");
            default:
                return "Something went wrong";
        }
    }
}
