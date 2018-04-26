package xoGame;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameInProgress implements GameState {
    private Player currentPlayer;
    private XOBoard board;
    private VictoryChecker victoryChecker;
    private ScoreBoard scoreBoard;
    int roundCounter;
    private final int expectedRoundAmount = 3;

    public GameInProgress(Player currentPlayer, XOBoard board,
                          VictoryChecker victoryChecker, ScoreBoard scoreBoard) {
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.victoryChecker = victoryChecker;
        this.scoreBoard = scoreBoard;
        this.roundCounter = 0;
    }

    @Override
    public void printTo(Consumer<String> output) {
        board.printTo(output);
        output.accept("Player " + currentPlayer + ", make your move");
    }

    @Override
    public GameState moveToNextState(String userInput) {
        board.applyMove(Coordinates.parse(userInput), currentPlayer);
        Optional<MatchResult> potentialWinner = victoryChecker.doWeHaveAWinner(board);

        if (potentialWinner.isPresent()) {
            scoreBoard.addPointsForPlayer(potentialWinner.get());
            roundCounter++;
        }
        if (roundCounter == expectedRoundAmount) {
            GameResult gameResult = new GameResult(scoreBoard);
            return new EndOfTheGame(gameResult);
        } else {
            currentPlayer = currentPlayer.getOppositePlayer();
            return this;
        }
    }
}