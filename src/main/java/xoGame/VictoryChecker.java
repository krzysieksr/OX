package xoGame;

import java.util.Optional;

import static xoGame.MatchResult.*;


public class VictoryChecker {

    int counter = 0;

    public Optional<MatchResult> doWeHaveAWinner(XOBoard board) {
        //TODO
        if (++counter >= 3) {
            counter = 0;
            return Optional.of(X);
        }
        return Optional.empty();

    }
}
