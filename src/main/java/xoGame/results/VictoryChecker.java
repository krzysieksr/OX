package xoGame.results;

import xoGame.XOBoard;

import java.util.Optional;

import static xoGame.results.MatchResult.*;


public class VictoryChecker {

    private int counter = 0;
    private int condition;

    private VictoryChecker(int victoryCondition) {
        this.condition = victoryCondition;
    }

    public Optional<MatchResult> doWeHaveAWinner(XOBoard board) {
        //TODO
        if (++counter >= 3) {
            counter = 0;
            return Optional.of(X);
        }
        return Optional.empty();

    }

    public static VictoryChecker parse(String winningCondition) {
        String[] parts = winningCondition.split(" ");
        int victoryCondition = Integer.parseInt(parts[0]);
        if (victoryCondition <= 0) {
            throw new IllegalArgumentException();
        }
        return new VictoryChecker(victoryCondition);
    }
}
