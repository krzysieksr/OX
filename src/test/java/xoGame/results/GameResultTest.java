package xoGame.results;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.ScoreBoard;
import xoGame.results.GameResult;
import xoGame.results.Results;

public class GameResultTest {

    @Test
    public void testGetResult() {
        GameResult gameResult = new GameResult(new ScoreBoard());

        Results returnedResult = gameResult.getResult();

        Assert.assertEquals(returnedResult, Results.X);
    }

    @Test
    public void testToString() {
        GameResult gameResult = new GameResult(new ScoreBoard());

        String returnedResult = gameResult.toString();

        Assert.assertEquals("Player 'X' wins", returnedResult);
    }
}