package xoGame.results;

import xoGame.XOBoard;
import xoGame.xoGameExceptions.TooManyArgumentsException;

import java.util.Optional;

import static xoGame.results.MatchResult.*;


public class VictoryChecker {

    private int counter = 0;
    private int condition;
    private XOBoard xoBoard;

    private VictoryChecker(int victoryCondition) {
        this.condition = victoryCondition;
    }

    public Optional<MatchResult> doWeHaveAWinner(XOBoard board) {
//        //TODO
//        if (++counter >= 3) {
//            counter = 0;
//            return Optional.of(X);
//        }
//        return Optional.empty();

        //TODO
        this.xoBoard = board;
        if (board.isBoardFull()) {
            return Optional.of(DRAW);
        }
        int recentMove = board.getRecentTypedCoor();
        if (checkVictory(recentMove, board.getCharAtIndex(recentMove))) {
            if (board.getCharAtIndex(recentMove) == 'X') {
                return Optional.of(X);
            }
            return Optional.of(O);
        }

        return Optional.empty();

    }

    public static VictoryChecker parse(String winningCondition) throws TooManyArgumentsException {
        String[] parts = winningCondition.split(" ");
        if (parts.length > 1) {
            throw new TooManyArgumentsException(winningCondition);
        }
        int victoryCondition = Integer.parseInt(parts[0]);
        if (victoryCondition <= 0) {
            throw new IllegalArgumentException();
        }
        return new VictoryChecker(victoryCondition);
    }

    public boolean checkVictory(int lastMove, Character character) {
        int inRow = countInRow(lastMove, character);
        return inRow == condition;
    }

    private int countInRow(int lastMove, Character character) {
        return 1 + countLeft(lastMove, character) + countRight(lastMove, character);
    }

    private int countLeft(int lastMove, Character character) {
        return xoBoard.getCharAtIndex(lastMove - 1) == (character) ? 1 + countLeft(lastMove - 1, character) : 0;
    }

    private int countRight(int lastMove, Character character) {
        return xoBoard.getCharAtIndex(lastMove + 1) == (character) ? 1 + countRight(lastMove + 1, character) : 0;
    }
}
