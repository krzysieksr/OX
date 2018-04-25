package xoGame;

import java.util.Optional;
import java.util.function.Consumer;

public class GameInProgress implements GameState {
    private Player currentPlayer;
    private final XOBoard board;
    private VictoryChecker victoryChecker;

    public GameInProgress(Player currentPlayer, XOBoard board, VictoryChecker victoryChecker) {
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.victoryChecker = victoryChecker;
    }

    @Override
    public void printTo(Consumer<String> output) {
        board.printTo(output);
        output.accept("Player " + currentPlayer + ", make your move");
    }

    @Override
    public GameState moveToNextState(String userInput) {
        board.applyMove(Coordinates.parse(userInput), currentPlayer);
        Optional<Player> potentialWinner = victoryChecker.doWeHaveAWinner(board);

        if (potentialWinner.isPresent()) {
            return new Victory(potentialWinner.get());
        } else {
            currentPlayer = currentPlayer.getOppositePlayer();
            return this;
        }
    }
}