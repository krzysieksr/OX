package xoGame.gameStates;

import xoGame.GameLanguage;
import xoGame.components.Player;
import xoGame.components.PlayerParser;
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

    private final static String DEFAULT_X_PLAYER_NAME = "X";
    private final static String DEFAULT_O_PLAYER_NAME = "O";

    @Override
    public void printTo(Consumer<String> output) {
        this.output = output;
        output.accept("Welcome to XO game:)");
    }


    @Override
    public GameState moveToNextState(Supplier<String> userInputProvider) {
        this.userInputProvider = userInputProvider;
        initializeGame();

        output.accept(language.getProperty("matchNumber1", "Match number 1:"));
        return new GameInProgress(
                startingPlayer,
                xoBoard,
                victoryChecker,
                new ScoreBoard(),
                language);
    }


    private void initializeGame() {
        if (changeDefaultSettings()) {
            changeLanguage();
            createXOBoard();
            createVictoryChecker();
            selectPlayer();
        } else {
            language = new GameLanguage(output).getGameLang("en");
            startingPlayer = Player.X;
            startingPlayer.setPlayerName(DEFAULT_X_PLAYER_NAME);
            startingPlayer.getOppositePlayer().setPlayerName(DEFAULT_O_PLAYER_NAME);
            xoBoard = XOBoard.parse("3 3");
            try {
                victoryChecker = VictoryChecker.parse("3", xoBoard);
            } catch (TooManyArgumentsException e) {
                output.accept(language.getProperty("tooManyArguments", "Too many arguments: ") + e.getArguments());
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


    private void selectPlayer() {
        PlayerParser playerParser = new PlayerParser(userInputProvider, output, language);
        startingPlayer = playerParser.parse();
    }

    private void createXOBoard() {
        try {
            output.accept(language.getProperty("demandBoardDimension", "Give board dimensions:"));
            xoBoard = XOBoard.parse(userInputProvider.get());
        } catch (NumberFormatException | NegativeArraySizeException | ArrayIndexOutOfBoundsException e) {
            output.accept(language.getProperty("wrongBoardDimension", "Wrong board dimensions!"));
            createXOBoard();
        }
    }

    private void createVictoryChecker() {
        try {
            output.accept(language.getProperty("demandWinCond", "Give winning condition:"));
            victoryChecker = VictoryChecker.parse(userInputProvider.get(), xoBoard);
        } catch (IllegalArgumentException e) {
            output.accept(language.getProperty("wrongWinningCond", "Wrong winning conditions!"));
            createVictoryChecker();
        } catch (TooManyArgumentsException e) {
            output.accept(language.getProperty("tooManyArguments", "Too many arguments: ") + e.getArguments());
            createVictoryChecker();
        }
    }
}

