package xoGame;

import java.util.function.Consumer;

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
    public GameState moveToNextState(String userInput) {
        return new InitialState();
    }
}
