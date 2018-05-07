package xoGame.results;

import xoGame.components.Player;
import xoGame.components.XOBoard;
import xoGame.xoGameExceptions.TooManyArgumentsException;

import java.util.Optional;

import static xoGame.results.MatchResult.*;


public class VictoryChecker {

    private int winCondition;
    private XOBoard xoBoard;

    private VictoryChecker(int victoryCondition) {
        this.winCondition = victoryCondition;
    }

    public static VictoryChecker parse(String winningCondition, XOBoard xoBoard) throws TooManyArgumentsException {
        String[] parts = winningCondition.split(" ");
        if (parts.length > 1) {
            throw new TooManyArgumentsException(winningCondition);
        }
        int victoryCondition = Integer.parseInt(parts[0]);
        if (victoryCondition <= 0 || victoryCondition > xoBoard.getShorterDimension()) {
            throw new IllegalArgumentException();
        }
        return new VictoryChecker(victoryCondition);
    }

    public Optional<MatchResult> tourResult(XOBoard board) {
        this.xoBoard = board;
        CheckWinner checkWinner = new CheckWinner(winCondition, xoBoard);
        if (checkWinner.doWeHaveAWinner()) {
            int recentMove = board.getRecentTypedIndex();

            if (board.getCharAtIndex(recentMove).equals(Player.X)) {
                return Optional.of(X);
            }
            return Optional.of(O);
        }
        if (xoBoard.isBoardFull()) {
            return Optional.of(DRAW);
        }

        return Optional.empty();
    }
}
