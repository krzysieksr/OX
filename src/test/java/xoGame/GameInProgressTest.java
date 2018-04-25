package xoGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

public class GameInProgressTest {

    @Test
    public void testMoveToNextStateMethodAndStayInTheSameState() {
        //given
        XOBoard xoBoard = new XOBoard();
        VictoryChecker victoryChecker = new VictoryChecker();
        Player player = Player.X;

        String input = "1 2";
        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker);

        //when
        GameState gameState = gameInProgress.moveToNextState(input);

        //then
        Assert.assertTrue(gameState instanceof GameInProgress);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testMoveToNextStateAndThrowIllegalArgumentException(){
        //given
        String input="some string";
        GameState initialState=new InitialState();

        //when and then
        initialState.moveToNextState(input);
    }

    @Test
    public void testMoveToNextStateMethodAndMoveToVictoryState() {
        //given
        XOBoard xoBoard = new XOBoard();
        VictoryChecker victoryChecker = new VictoryChecker();
        Player player = Player.X;

        // when
        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker);
        for (int i = 0; i <= 10; i++) {
            Random random = new Random();
            int x = random.nextInt();
            int y = random.nextInt();
            String input = x + " " + y;

            gameInProgress = gameInProgress.moveToNextState(input);
        }

        // then
        Assert.assertTrue(gameInProgress instanceof Victory);
    }
}