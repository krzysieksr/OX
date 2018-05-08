package xoGame.gameStates;

import xoGame.WhoStartedRecently;
import xoGame.components.Player;
import xoGame.components.XOBoard;
import xoGame.coordinates.Cell;
import xoGame.results.GameResult;
import xoGame.results.MatchResult;
import xoGame.components.ScoreBoard;
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
    private int matchCounter;
    private final int expectedRoundAmount = 3;
    private final String boardDimensionsAsString;
    WhoStartedRecently whoStartedRecently;

    public GameInProgress(Player currentPlayer, XOBoard xoBoard,
                          VictoryChecker victoryChecker, ScoreBoard scoreBoard) {
        this.currentPlayer = currentPlayer;
        this.xoBoard = xoBoard;
        boardDimensionsAsString = boardDimensionToString(xoBoard);
        this.victoryChecker = victoryChecker;
        this.scoreBoard = scoreBoard;
        this.matchCounter = 0;
        whoStartedRecently = new WhoStartedRecently(currentPlayer);
    }

    @Override
    public void printTo(Consumer<String> output) {
        this.output = output;
        xoBoard.printTo(output);
        output.accept("Player " + currentPlayer.getPlayerName() + ", make your move:");
    }

    @Override
    public GameState moveToNextState(Supplier<String> userInputProvider) {
        this.userInputProvider = userInputProvider;
        makeMove();
        checkTourResult();

        if (matchCounter == expectedRoundAmount) {
            GameResult gameResult = new GameResult(scoreBoard);
            return new EndOfTheGame(gameResult);
        } else {
            currentPlayer = currentPlayer.getOppositePlayer();
            return this;
        }
    }

    private void makeMove() {
        try {
            xoBoard.applyMove(Cell.parse(userInputProvider.get()), currentPlayer);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            xoBoard.printTo(output);
            output.accept("Wrong coordinates! Player " + currentPlayer.getPlayerName() + ", try again:");
            makeMove();
        } catch (CellBusyException e) {
            output.accept("Cell [" + e.getCellIndex() + "] is already busy. Player " + currentPlayer + ", try again:");
            makeMove();
        }
    }

    private String boardDimensionToString(XOBoard xoBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = stringBuilder.append(xoBoard.getX()).append(" ").append(xoBoard.getY());
        return stringBuilder.toString();
    }

    private void checkTourResult() {
        Optional<MatchResult> potentialWinner = victoryChecker.tourResult(xoBoard);
        if (potentialWinner.isPresent()) {
            MatchResult matchResult = potentialWinner.get();
            scoreBoard.addPointsForPlayer(matchResult);
            xoBoard = XOBoard.parse(boardDimensionsAsString);
            output.accept(matchResult.getMessage());
            output.accept("X: " + String.valueOf(scoreBoard.getPlayerPoints(Player.X)) + " O: " + String.valueOf(scoreBoard.getPlayerPoints(Player.O)));
            currentPlayer = whoStartedRecently.getWhoStartedRecently(currentPlayer);
            matchCounter++;
            if (matchCounter < expectedRoundAmount) {
                output.accept("Match number " + (matchCounter + 1) + ":");
            }
        }
    }
}