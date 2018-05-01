package xoGame.components;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.Player;
import xoGame.components.ScoreBoard;

public class ScoreBoardTest {

//    @Test
//    public void testAddPointsForPlayer() {
//        ScoreBoard scoreBoard = new ScoreBoard();
//
//        boolean addingPointResult = scoreBoard.addPointsForPlayer(MatchResult.X);
//
//        Assert.assertEquals(addingPointResult, true);
//    }

    @Test
    public void testGetPlayerPoints() {
        ScoreBoard scoreBoard = new ScoreBoard();
        Player player = Player.X;

        int returnedPoints = scoreBoard.getPlayerPoints(player);

        Assert.assertEquals(returnedPoints, 0);
    }
}