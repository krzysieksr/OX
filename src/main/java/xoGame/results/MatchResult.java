package xoGame.results;

public enum MatchResult {
    X(0),
    O(1),
    DRAW(3);

    private int result;


    MatchResult(int result) {
        this.result = result;
    }
}
