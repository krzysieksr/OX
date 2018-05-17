package xoGame.results;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.ScoreBoard;

import java.util.Properties;

public class GameResultTest {


    @Test
    public void testGetResult() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.addPointsForPlayer(MatchResult.X);
        GameResult gameResult = new GameResult(scoreBoard, new Properties());

        Results returnedResult = gameResult.getResult();

        Assert.assertEquals(returnedResult, Results.X);
    }

    @Test
    public void testToString() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.addPointsForPlayer(MatchResult.X);
        GameResult gameResult = new GameResult(scoreBoard, new Properties());

        String returnedResult = gameResult.toString();

        Assert.assertEquals(returnedResult, "Player X has won the game!");
    }
}