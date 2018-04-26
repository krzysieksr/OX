package xoGame;

import java.util.Optional;
import java.util.function.Consumer;

public class GameInProgress implements GameState {
    private Player currentPlayer;
    private XOBoard xoBoard;
    private VictoryChecker victoryChecker;
    private ScoreBoard scoreBoard;
    private int roundCounter;
    private final int expectedRoundAmount = 3;

    public GameInProgress(Player currentPlayer, XOBoard xoBoard,
                          VictoryChecker victoryChecker, ScoreBoard scoreBoard) {
        this.currentPlayer = currentPlayer;
        this.xoBoard = xoBoard;
        this.victoryChecker = victoryChecker;
        this.scoreBoard = scoreBoard;
        this.roundCounter = 0;
    }

    @Override
    public void printTo(Consumer<String> output) {
        xoBoard.printTo(output);
        output.accept("Player " + currentPlayer + ", make your move:");
    }

    @Override
    public GameState moveToNextState(String userInput) {
        xoBoard.applyMove(Coordinates.parse(userInput), currentPlayer);
        Optional<MatchResult> potentialWinner = victoryChecker.doWeHaveAWinner(xoBoard);

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