package xoGame;

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
//        output.accept("Winner is: " + winner + "Press Enter to reset");
    }

    @Override
    public GameState moveToNextState(String userInput) {
        return new InitialState();
    }
}
