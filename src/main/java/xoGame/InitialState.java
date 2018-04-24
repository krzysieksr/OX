package xoGame;

import java.util.function.Consumer;

public class InitialState implements GameState {
    @Override
    public void printTo(Consumer<String> output) {
        output.accept("Welcome to XO, who shall start?");
    }

    @Override
    public GameState moveToNextState(String userInput) {
        Player startingPlayer = Player.valueOf(userInput);
        return new GameInProgress(
                startingPlayer,
                new XOBoard(),
                new VictoryChecker());
    }
}

