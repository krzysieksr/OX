package xoGame.results;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.XOBoard;
import xoGame.xoGameExceptions.TooManyArgumentsException;

import java.util.Optional;

public class VictoryCheckerTest {

//    @Test
//    public void testDoWeHaveAWinnerAndXWins() throws TooManyArgumentsException {
//        VictoryChecker victoryChecker = VictoryChecker.parse("4");
//        XOBoard xoBoard = XOBoard.parse("1 4");
//        Optional<MatchResult> potentialWinner = Optional.empty();
//
//        for (int i = 0; i < 3; i++) {
//            potentialWinner = victoryChecker.tourResult(xoBoard);
//        }
//
//        Assert.assertEquals(potentialWinner.get(), MatchResult.X);
//    }
//
//    @Test
//    public void testDoWeHaveAWinnerAndYWins() {
//        //TODO
//    }
//
//    @Test
//    public void testDoWeHaveAWinnerAndReturnsDraw() {
//        //TODO
//    }
//
//    @Test
//    public void testDoWeHaveAWinnerMethodAndThereIsNoWinnerYet() throws TooManyArgumentsException {
//        VictoryChecker victoryChecker = VictoryChecker.parse("4");
//        XOBoard xoBoard = XOBoard.parse("1 4");
//
//        Optional<MatchResult> potentialWinner = victoryChecker.tourResult(xoBoard);
//
//
//        Assert.assertTrue(!potentialWinner.isPresent());
//    }
}