package xoGame;

public class GameResult {

    private ScoreBoard scoreBoard;

    GameResult(ScoreBoard scoreBoard) {
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
