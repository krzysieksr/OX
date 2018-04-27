package xoGame;

import xoGame.gameStates.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class XOGame {
    private GameState currentState;
    private Supplier<String> userInputProvider;
    private Consumer<String> output;

    public XOGame(GameState currentState, Supplier<String> userInputProvider, Consumer<String> output) {
        this.currentState = currentState;
        this.userInputProvider = userInputProvider;
        this.output = output;
    }

    public void start() {
        mainLoop();
    }

    private void mainLoop() {
        while (true) {
            doOneCycleOfTheGame();
        }
    }

    private void doOneCycleOfTheGame() {
        this.currentState.printTo(output);
        this.currentState = currentState.moveToNextState(userInputProvider);
    }

}
