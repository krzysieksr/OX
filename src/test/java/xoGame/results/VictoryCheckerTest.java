package xoGame.results;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.Player;
import xoGame.components.XOBoard;
import xoGame.coordinates.Cell;
import xoGame.xoGameExceptions.CellBusyException;
import xoGame.xoGameExceptions.TooManyArgumentsException;

import java.util.Optional;

public class VictoryCheckerTest {

    @Test
    public void testParseForCorrectWinningConditionInput() throws TooManyArgumentsException {
        String winningCondition = "3";
        XOBoard xoBoard = XOBoard.parse("3 4");

        VictoryChecker victoryChecker = VictoryChecker.parse(winningCondition, xoBoard);

        Assert.assertTrue(victoryChecker instanceof VictoryChecker);
    }

    @Test(expectedExceptions = TooManyArgumentsException.class)
    public void testParseForInputContainingToManyArguments() throws TooManyArgumentsException {
        String winningCondition = "4 5 6";
        XOBoard xoBoard = XOBoard.parse("3 4");

        VictoryChecker.parse(winningCondition, xoBoard);
    }


    @Test(expectedExceptions = NumberFormatException.class)
    public void testParseForInputContainingIllegalSigns() throws TooManyArgumentsException {
        String winningCondition = "s";
        XOBoard xoBoard = XOBoard.parse("3 4");

        VictoryChecker.parse(winningCondition, xoBoard);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testParseForInputWinningConditionLessThanOne() throws TooManyArgumentsException {
        String winningCondition = "-2";
        XOBoard xoBoard = XOBoard.parse("3 4");

        VictoryChecker.parse(winningCondition, xoBoard);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testParseForInputWinningConditionGreaterThanShorterBoardDimension() throws TooManyArgumentsException {
        String winningCondition = "8";
        XOBoard xoBoard = XOBoard.parse("3 4");

        VictoryChecker.parse(winningCondition, xoBoard);
    }


    @Test
    public void testTourResult() throws CellBusyException, TooManyArgumentsException {
        String boardDimension = "3 3";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        xoBoard.applyMove(Cell.parse("2"), Player.X);
        xoBoard.applyMove(Cell.parse("1"), Player.O);
        xoBoard.applyMove(Cell.parse("5"), Player.X);
        xoBoard.applyMove(Cell.parse("7"), Player.O);
        xoBoard.applyMove(Cell.parse("8"), Player.X);
        String winningCondition = "3";
        VictoryChecker victoryChecker = VictoryChecker.parse(winningCondition, xoBoard);

        Optional<MatchResult> potentialWinner = victoryChecker.tourResult(xoBoard);


        Assert.assertEquals(potentialWinner.get(), MatchResult.X);


    }
}