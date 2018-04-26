package xoGame.gameStates;

import java.util.function.Consumer;

public interface GameState {

    void printTo(Consumer<String> output);

    GameState moveToNextState(String userInput);
}
