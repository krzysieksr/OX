package xoGame.gameStates;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.Player;
import xoGame.ScoreBoard;
import xoGame.XOBoard;
import xoGame.gameStates.EndOfTheGame;
import xoGame.gameStates.GameInProgress;
import xoGame.gameStates.GameState;
import xoGame.gameStates.InitialState;
import xoGame.results.VictoryChecker;

import java.util.Random;

public class GameInProgressTest {

    @Test
    public void testMoveToNextStateMethodAndStayInTheSameState() {
        //given
        XOBoard xoBoard = XOBoard.parse("3 3");
        VictoryChecker victoryChecker = new VictoryChecker();
        ScoreBoard scoreBoard = new ScoreBoard();
        Player player = Player.X;

        String input = "1 2";
        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker, scoreBoard);

        //when
        GameState gameState = gameInProgress.moveToNextState(input);

        //then
        Assert.assertTrue(gameState instanceof GameInProgress);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testMoveToNextStateAndThrowIllegalArgumentException() {
        //given
        String input = "some string";
        GameState initialState = new InitialState();

        //when and then
        initialState.moveToNextState(input);
    }

    @Test
    public void testMoveToNextStateMethodAndMoveToVictoryState() {
        //given
        XOBoard xoBoard = XOBoard.parse("3 3");
        VictoryChecker victoryChecker = new VictoryChecker();
        Player player = Player.X;
        ScoreBoard scoreBoard = new ScoreBoard();

        // when
        GameState gameInProgress = new GameInProgress(player, xoBoard, victoryChecker, scoreBoard);
        for (int i = 0; i <= 8; i++) {
            Random random = new Random();
            int x = random.nextInt();
            int y = random.nextInt();
            String input = x + " " + y;

            gameInProgress = gameInProgress.moveToNextState(input);
        }

        // then
        Assert.assertTrue(gameInProgress instanceof EndOfTheGame);
    }
}