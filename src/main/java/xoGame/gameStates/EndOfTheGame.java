package xoGame.gameStates;

import xoGame.results.GameResult;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class EndOfTheGame implements GameState {
    private GameResult gameResult;

    public EndOfTheGame(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public void printTo(Consumer<String> output) {
        output.accept(gameResult.toString());
    }

    @Override
    public GameState moveToNextState(Supplier<String> userInputProvider) {
        return new InitialState();
    }
}
