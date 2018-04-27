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


}