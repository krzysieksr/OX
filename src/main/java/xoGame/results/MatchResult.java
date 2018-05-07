package xoGame.results;

public enum MatchResult {
    X("Match won by X player."),
    O("Match won by O player."),
    DRAW("Draw in match.");

    private String message;

    MatchResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
