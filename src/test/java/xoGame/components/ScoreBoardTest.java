package xoGame.components;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.Player;
import xoGame.components.ScoreBoard;
import xoGame.results.MatchResult;
import xoGame.results.Results;

public class ScoreBoardTest {

    @Test
    public void testAddPointsForPlayer() {
        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.addPointsForPlayer(MatchResult.O);

        Assert.assertEquals(scoreBoard.getPlayerPoints(Player.O), 3);
    }

    @Test
    public void testGetPlayerPointsX() {
        ScoreBoard scoreBoard = new ScoreBoard();
        Player player = Player.X;
        scoreBoard.addPointsForPlayer(MatchResult.X);
        scoreBoard.addPointsForPlayer(MatchResult.DRAW);

        int returnedPoints = scoreBoard.getPlayerPoints(player);

        Assert.assertEquals(returnedPoints, 4);
    }

    @Test
    public void testGetPlayerPointsO() {
        ScoreBoard scoreBoard = new ScoreBoard();
        Player player = Player.O;
        scoreBoard.addPointsForPlayer(MatchResult.X);
        scoreBoard.addPointsForPlayer(MatchResult.DRAW);
        scoreBoard.addPointsForPlayer(MatchResult.O);
        scoreBoard.addPointsForPlayer(MatchResult.O);

        int returnedPoints = scoreBoard.getPlayerPoints(player);

        Assert.assertEquals(returnedPoints, 7);
    }


    @Test
    public void testGetCurrentGameResult() {
        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.addPointsForPlayer(MatchResult.X);
        scoreBoard.addPointsForPlayer(MatchResult.DRAW);

        Assert.assertEquals(scoreBoard.getCurrentGameResult(),Results.X);
    }
}