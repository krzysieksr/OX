package xoGame.gameStates;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameStateDecorator implements GameState {

    private final Consumer<Exception> exceptionHandler;
    private GameState decorated;

    public static GameState decorator(GameState initialState, Consumer<Exception> exceptionHandler) {
        return new GameStateDecorator(exceptionHandler, initialState);
    }


    private GameStateDecorator(Consumer<Exception> exceptionHandler, GameState initialState) {
        this.exceptionHandler = exceptionHandler;
        this.decorated = initialState;
    }

    @Override
    public void printTo(Consumer<String> output) {
        decorated.printTo(output);
    }

    @Override
    public GameState moveToNextState(Supplier<String> userInputProvider) {
        try {
            decorated = decorated.moveToNextState(userInputProvider);
        } catch (Exception e) {
            exceptionHandler.accept(e);
        }
        return this;
    }
}
