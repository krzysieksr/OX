package xoGame.gameStates;

import xoGame.results.GameResult;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EndOfTheGame implements GameState {
    private GameResult gameResult;
    private final Properties language;
    private Consumer<String> output;
    private Supplier<String> userInputProvider;


    EndOfTheGame(GameResult gameResult, Properties language) {
        this.gameResult = gameResult;
        this.language = language;
    }

    @Override
    public void printTo(Consumer<String> output) {
        this.output = output;
        output.accept(gameResult.toString());
    }

    @Override
    public GameState moveToNextState(Supplier<String> userInputProvider) {
        this.userInputProvider = userInputProvider;
        if (shouldWeStartAgain()) {
            return new InitialState();
        }
        System.exit(0);
        return this;
    }

    private boolean shouldWeStartAgain() {
        output.accept(language.getProperty("wouldYouLikePlayAgain", "Would you like to play again (Y/N)?"));
        String yesOrNo = userInputProvider.get().toUpperCase();
        return yesOrNo.equals("Y") || (!yesOrNo.equals("N") && shouldWeStartAgain());
    }
}
