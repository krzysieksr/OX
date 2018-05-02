package xoGame.gameStates;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.Player;
import xoGame.components.ScoreBoard;
import xoGame.components.XOBoard;
import xoGame.results.VictoryChecker;
import xoGame.xoGameExceptions.TooManyArgumentsException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameInProgressTest {

    @Test
    public void testMoveToNextStateMethodAndStayInTheSameState() throws TooManyArgumentsException {
        //given
        XOBoard xoBoard = XOBoard.parse("3 3");
        VictoryChecker victoryChecker = VictoryChecker.parse("3", xoBoard);
        ScoreBoard scoreBoard = new ScoreBoard();

        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Consumer<String> consumer = System.out::println;
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
        Player player = Player.X;

        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker, scoreBoard);
        gameInProgress.printTo(consumer);

        //when
        GameState gameState = gameInProgress.moveToNextState(userInputProvider);

        //then
        Assert.assertTrue(gameState instanceof GameInProgress);
    }


    @Test
    public void testMoveToNextStateMethodAndMoveToEndOfTheGameState() throws TooManyArgumentsException {
        //given
        XOBoard xoBoard = XOBoard.parse("4 4");
        VictoryChecker victoryChecker = VictoryChecker.parse("4", xoBoard);
        Player player = Player.X;
        ScoreBoard scoreBoard = new ScoreBoard();

        // when
        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker, scoreBoard);
        while (gameInProgress instanceof GameInProgress) {
            for (int i = 1; i < 14; i++) {
                String inputIndex = String.valueOf(i);

                InputStream in = new ByteArrayInputStream(inputIndex.getBytes());
                System.setIn(in);
                Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;

                gameInProgress.printTo(System.out::println);

                gameInProgress = gameInProgress.moveToNextState(userInputProvider);
            }
        }

        // then
        Assert.assertTrue(gameInProgress instanceof EndOfTheGame);
    }

}