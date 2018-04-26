package xoGame;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface GameState {

    void printTo(Consumer<String> output);

    GameState moveToNextState(String userInput);
}
