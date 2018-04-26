package xoGame;

public class GameResult {


    private ScoreBoard scoreBoard;

    GameResult(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    private Results getResult() {
        return Results.X;
    }


    private enum Results {
        X,
        Y,
        DRAW
    }

    @Override
    public String toString() {
        switch (getResult()) {
            case X:
                return "XXXXXXXX wins";
            case Y:
                return "YYYYYY wins";
            case DRAW:
                return "Fucking draw";
            default:
                return "bleee";
        }
    }
}
