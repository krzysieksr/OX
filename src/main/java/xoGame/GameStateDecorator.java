package xoGame;

import java.util.function.Consumer;

public class GameStateDecorator implements GameState {

    private final Consumer<Exception> exceptionHandler;
    private GameState decorated;

    static GameState decorator(GameState initialState, Consumer<Exception> exceptionHandler) {
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
    public GameState moveToNextState(String userInput) {
        try {
            decorated = decorated.moveToNextState(userInput);
        } catch (Exception e) {
            exceptionHandler.accept(e);
        }
        return this;
    }
}
