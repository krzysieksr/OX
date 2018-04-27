package xoGame.gameStates;

import xoGame.*;
import xoGame.results.GameResult;
import xoGame.results.MatchResult;
import xoGame.results.VictoryChecker;
import xoGame.xoGameExceptions.CellBusyException;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameInProgress implements GameState {
    private Player currentPlayer;
    private XOBoard xoBoard;
    private VictoryChecker victoryChecker;
    private ScoreBoard scoreBoard;
    private Supplier<String> userInputProvider;
    private Consumer<String> output;
    private int roundCounter;
    private final int expectedRoundAmount = 3;
    private final String boardDimensionsAsString;

    public GameInProgress(Player currentPlayer, XOBoard xoBoard,
                          VictoryChecker victoryChecker, ScoreBoard scoreBoard) {
        this.currentPlayer = currentPlayer;
        this.xoBoard = xoBoard;
        boardDimensionsAsString = boardDimensionToString(xoBoard);
        this.victoryChecker = victoryChecker;
        this.scoreBoard = scoreBoard;
        this.roundCounter = 0;
    }

    @Override
    public void printTo(Consumer<String> output) {
        this.output = output;
        xoBoard.printTo(output);
        output.accept("Player " + currentPlayer + ", make your move:");
    }

    @Override
    public GameState moveToNextState(Supplier<String> userInputProvider) {
        this.userInputProvider = userInputProvider;
        makeMove();
        Optional<MatchResult> potentialWinner = victoryChecker.doWeHaveAWinner(xoBoard);

        if (potentialWinner.isPresent()) {
            scoreBoard.addPointsForPlayer(potentialWinner.get());
            xoBoard = XOBoard.parse(boardDimensionsAsString);
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

    private void makeMove() {
        try {
            xoBoard.applyMove(Coordinates.parse(userInputProvider.get()), currentPlayer);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            xoBoard.printTo(output);
            output.accept("Wrong coordinates! Player " + currentPlayer + ", try again:");
            makeMove();
        } catch (CellBusyException e) {
            output.accept("Cell " + e.toString() + " is already busy. Player " + currentPlayer + ", try again:");
            makeMove();
        }
    }

    private String boardDimensionToString(XOBoard xoBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = stringBuilder.append(xoBoard.getX()).append(" ").append(xoBoard.getY());
        return stringBuilder.toString();
    }
}