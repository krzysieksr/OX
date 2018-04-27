package xoGame.gameStates;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.Player;
import xoGame.ScoreBoard;
import xoGame.XOBoard;
import xoGame.results.VictoryChecker;
import xoGame.xoGameExceptions.TooManyArgumentsException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

public class GameInProgressTest {

    @Test
    public void testMoveToNextStateMethodAndStayInTheSameState() throws TooManyArgumentsException {
        //given
        XOBoard xoBoard = XOBoard.parse("3 3");
        VictoryChecker victoryChecker = VictoryChecker.parse("4");
        ScoreBoard scoreBoard = new ScoreBoard();

        String input = "1 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
        Player player = Player.X;

        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker, scoreBoard);

        //when
        GameState gameState = gameInProgress.moveToNextState(userInputProvider);

        //then
        Assert.assertTrue(gameState instanceof GameInProgress);
    }

    //TODO  
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public static void testMoveToNextStateAndThrowIllegalArgumentException() throws TooManyArgumentsException {
//        //given
//        XOBoard xoBoard = XOBoard.parse("3 3");
//        VictoryChecker victoryChecker = VictoryChecker.parse("4");
//        ScoreBoard scoreBoard = new ScoreBoard();
//
//        String input = "some string";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
//        Player player = Player.X;
//
//        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker, scoreBoard);
//        gameInProgress.printTo(System.out::println);
//
//        //when and then
//        gameInProgress.moveToNextState(userInputProvider);
//    }

//    @Test
//    public void testMoveToNextStateMethodAndMoveToVictoryState() throws TooManyArgumentsException {
//        //given
//        XOBoard xoBoard = XOBoard.parse("3 3");
//        VictoryChecker victoryChecker = VictoryChecker.parse("4");
//        Player player = Player.X;
//        ScoreBoard scoreBoard = new ScoreBoard();
//
//        // when
//        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker, scoreBoard);
//        for (int i = 0; i <= 8; i++) {
//            Random random = new Random();
//            int x = random.nextInt();
//            int y = random.nextInt();
//            String input = x + " " + y;
//
//            InputStream in = new ByteArrayInputStream(input.getBytes());
//            System.setIn(in);
//            Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
//
//
//            gameInProgress = gameInProgress.moveToNextState(userInputProvider);
//        }
//
//        // then
//        Assert.assertTrue(gameInProgress instanceof EndOfTheGame);
//    }
}