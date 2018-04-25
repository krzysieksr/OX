package xoGame;

import java.util.function.Consumer;

public class EndOfTheGame implements GameState {
    @Override
    public void printTo(Consumer<String> output) {

    }

    @Override
    public GameState moveToNextState(String userInput) {
        return null;
    }
}
