package xoGame;

import java.util.function.Consumer;

public class Victory implements GameState {
    private Player winner;

    public Victory(Player winner) {
        this.winner = winner;
    }

    @Override
    public void printTo(Consumer<String> output) {
        output.accept("Winner is: " + winner + "Press Enter to reset");
    }

    @Override
    public GameState moveToNextState(String userInput) {
        return new InitialState();
    }
}
