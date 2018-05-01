package xoGame.gameStates;

import xoGame.Player;
import xoGame.results.ScoreBoard;
import xoGame.results.VictoryChecker;
import xoGame.XOBoard;
import xoGame.xoGameExceptions.TooManyArgumentsException;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {
    private Consumer<String> output;
    private Supplier<String> userInputProvider;
    private XOBoard xoBoard;
    private VictoryChecker victoryChecker;

    @Override
    public void printTo(Consumer<String> output) {
        this.output = output;
        output.accept("Welcome to XO game, who shall start (X or O) ?");
    }


    @Override
    public GameState moveToNextState(Supplier<String> userInputProvider) {
        this.userInputProvider = userInputProvider;
        Player startingPlayer = selectPlayer();
        createXOBoard();
        createVictoryChecker();
        output.accept("Match number 1:");
        return new GameInProgress(
                startingPlayer,
                xoBoard,
                victoryChecker,
                new ScoreBoard());
    }

    private Player selectPlayer() {
        Player startingPlayer;
        try {
            startingPlayer = Player.valueOf(userInputProvider.get().toUpperCase());
        } catch (IllegalArgumentException e) {
            output.accept("Wrong, type X or O:");
            return selectPlayer();
        }
        return startingPlayer;
    }

    private void createXOBoard() {
        try {
            output.accept("Give board dimensions:");
            xoBoard = XOBoard.parse(userInputProvider.get());
        } catch (NumberFormatException | NegativeArraySizeException | ArrayIndexOutOfBoundsException e) {
            output.accept("Wrong board dimensions!");
            createXOBoard();
        }
    }

    private void createVictoryChecker() {
        try {
            output.accept("Give winning condition:");
            victoryChecker = VictoryChecker.parse(userInputProvider.get(),xoBoard);
        } catch (IllegalArgumentException e) {
            output.accept("Wrong winning conditions!");
            createVictoryChecker();
        } catch (TooManyArgumentsException e) {
            output.accept("Too many arguments: " + e.getArguments());
            createVictoryChecker();
        }
    }
}

