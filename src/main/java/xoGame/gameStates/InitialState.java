package xoGame.gameStates;

import xoGame.GameLanguage;
import xoGame.components.Player;
import xoGame.components.ScoreBoard;
import xoGame.results.VictoryChecker;
import xoGame.components.XOBoard;
import xoGame.xoGameExceptions.TooManyArgumentsException;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {
    private Consumer<String> output;
    private Supplier<String> userInputProvider;
    private XOBoard xoBoard;
    private VictoryChecker victoryChecker;
    private Player startingPlayer;
    private Properties language;

    @Override
    public void printTo(Consumer<String> output) {
        this.output = output;
        output.accept("Welcome to XO game:)");
    }


    @Override
    public GameState moveToNextState(Supplier<String> userInputProvider) {
        this.userInputProvider = userInputProvider;
        initializeGame();

        output.accept("Match number 1:");
        return new GameInProgress(
                startingPlayer,
                xoBoard,
                victoryChecker,
                new ScoreBoard());
    }


    private void initializeGame() {
        if (changeDefaultSettings()) {
            changeLanguage();
            startingPlayer = selectPlayer();
            createXOBoard();
            createVictoryChecker();
        } else {
            language = new GameLanguage(output).getGameLang("en");
            startingPlayer = Player.X;
            xoBoard = XOBoard.parse("3 3");
            try {
                victoryChecker = VictoryChecker.parse("3", xoBoard);
            } catch (TooManyArgumentsException e) {
                output.accept("Too many arguments: " + e.getArguments());
            }
        }

    }

    private void changeLanguage() {
        GameLanguage gameLanguage = new GameLanguage(output);
        try {
            output.accept("Chose language (en, pl):");
            language = gameLanguage.getGameLang(userInputProvider.get());
        } catch (IllegalArgumentException e) {
            output.accept("Chosen language doesn't exist.");

            changeLanguage();
        }
    }

    private boolean changeDefaultSettings() {
        output.accept("Would you like to change game default settings (Y/N)?");
        String yesOrNo = userInputProvider.get().toUpperCase();
        return yesOrNo.equals("Y") || (!yesOrNo.equals("N") && changeDefaultSettings());
    }


    private Player selectPlayer() {
        Player startingPlayer;
        try {
            output.accept(language.getProperty("whoStarts", "Who shall start (X or O) ?"));
            startingPlayer = Player.valueOf(userInputProvider.get().toUpperCase());
        } catch (IllegalArgumentException e) {
            output.accept("Wrong, type X or O.");
            return selectPlayer();
        }
        return startingPlayer;
    }

    private void createXOBoard() {
        try {
            output.accept(language.getProperty("demandBoardDimension", "Give board dimensions:"));
            xoBoard = XOBoard.parse(userInputProvider.get());
        } catch (NumberFormatException | NegativeArraySizeException | ArrayIndexOutOfBoundsException e) {
            output.accept("Wrong board dimensions!");
            createXOBoard();
        }
    }

    private void createVictoryChecker() {
        try {
            output.accept(language.getProperty("demandWinCond", "Give winning condition:"));
            victoryChecker = VictoryChecker.parse(userInputProvider.get(), xoBoard);
        } catch (IllegalArgumentException e) {
            output.accept("Wrong winning conditions!");
            createVictoryChecker();
        } catch (TooManyArgumentsException e) {
            output.accept("Too many arguments: " + e.getArguments());
            createVictoryChecker();
        }
    }
}

