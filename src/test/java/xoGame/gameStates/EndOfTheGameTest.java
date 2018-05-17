package xoGame.gameStates;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.ScoreBoard;
import xoGame.results.GameResult;
import xoGame.results.MatchResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.function.Supplier;

public class EndOfTheGameTest {

    @Test()
    public void testMoveToNextStateAndMoveToInitialState() {
        String input = "Y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.addPointsForPlayer(MatchResult.X);

        GameResult gameResult = new GameResult(scoreBoard, new Properties());

        GameState gameState = new EndOfTheGame(gameResult, new Properties());
        gameState.printTo(System.out::println);


        GameState returnedGameState = gameState.moveToNextState(userInputProvider);

        Assert.assertTrue(returnedGameState instanceof InitialState);
    }

}
