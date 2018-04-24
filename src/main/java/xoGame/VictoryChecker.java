package xoGame;
import java.util.Optional;

import static xoGame.Player.X;


public class VictoryChecker {

    int counter =0;

    public Optional<Player> doWeHaveAWinner(XOBoard board) {
        //TODO
        if(++counter >10) return Optional.of(X);
        return Optional.empty();

    }
}
