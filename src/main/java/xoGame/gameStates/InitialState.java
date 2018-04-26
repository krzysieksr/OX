package xoGame.gameStates;

import xoGame.Player;
import xoGame.ScoreBoard;
import xoGame.results.VictoryChecker;
import xoGame.XOBoard;

import java.util.Scanner;
import java.util.function.Consumer;

public class InitialState implements GameState {
    Consumer<String> output;
    XOBoard xoBoard;

    @Override
    public void printTo(Consumer<String> output) {
        this.output = output;
        output.accept("Welcome to XO game, who shall start?");
    }


    @Override
    public GameState moveToNextState(String userInput) {
        Player startingPlayer = Player.valueOf(userInput);
        createXOBoard();
        return new GameInProgress(
                startingPlayer,
                xoBoard,
                new VictoryChecker(),
                new ScoreBoard());
    }

    private void createXOBoard() {
        try {
            output.accept("Give board dimensions:");
            xoBoard = XOBoard.parse(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Wrong board dimensions!");
            createXOBoard();
        }
    }
}

