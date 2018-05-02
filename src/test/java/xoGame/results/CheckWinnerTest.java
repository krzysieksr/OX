package xoGame.results;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.Player;
import xoGame.components.XOBoard;
import xoGame.coordinates.Cell;
import xoGame.xoGameExceptions.CellBusyException;

import static org.testng.Assert.*;

public class CheckWinnerTest {

    @Test
    public void testDoWeHaveAWinnerAndThereIsNoWinnerYet() throws CellBusyException {
        XOBoard xoBoard = XOBoard.parse("3 3");
        xoBoard.applyMove(Cell.parse("1"), Player.X);
        xoBoard.applyMove(Cell.parse("5"), Player.X);
        int winCondition = 3;

        CheckWinner checkWinner = new CheckWinner(winCondition, xoBoard);

        Assert.assertTrue(!checkWinner.doWeHaveAWinner());
    }

    @Test
    public void testDoWeHaveAWinnerAndExpectWinnerHorizontal() throws CellBusyException {
        XOBoard xoBoard = XOBoard.parse("3 3");
        xoBoard.applyMove(Cell.parse("4"), Player.X);
        xoBoard.applyMove(Cell.parse("5"), Player.X);
        xoBoard.applyMove(Cell.parse("6"), Player.X);

        int winCondition = 3;

        CheckWinner checkWinner = new CheckWinner(winCondition, xoBoard);

        Assert.assertTrue(checkWinner.doWeHaveAWinner());
    }

    @Test
    public void testDoWeHaveAWinnerAndExpectWinnerVertical() throws CellBusyException {
        XOBoard xoBoard = XOBoard.parse("3 3");
        xoBoard.applyMove(Cell.parse("2"), Player.X);
        xoBoard.applyMove(Cell.parse("5"), Player.X);
        xoBoard.applyMove(Cell.parse("8"), Player.X);

        int winCondition = 3;

        CheckWinner checkWinner = new CheckWinner(winCondition, xoBoard);

        Assert.assertTrue(checkWinner.doWeHaveAWinner());
    }

    @Test
    public void testDoWeHaveAWinnerAndExpectWinnerDiagonalFromDownToUp() throws CellBusyException {
        XOBoard xoBoard = XOBoard.parse("3 3");
        xoBoard.applyMove(Cell.parse("7"), Player.X);
        xoBoard.applyMove(Cell.parse("5"), Player.X);
        xoBoard.applyMove(Cell.parse("3"), Player.X);

        int winCondition = 3;

        CheckWinner checkWinner = new CheckWinner(winCondition, xoBoard);

        Assert.assertTrue(checkWinner.doWeHaveAWinner());
    }


    @Test
    public void testDoWeHaveAWinnerAndExpectWinnerDiagonalFromUpToDown() throws CellBusyException {
        XOBoard xoBoard = XOBoard.parse("3 3");
        xoBoard.applyMove(Cell.parse("1"), Player.X);
        xoBoard.applyMove(Cell.parse("5"), Player.X);
        xoBoard.applyMove(Cell.parse("9"), Player.X);

        int winCondition = 3;

        CheckWinner checkWinner = new CheckWinner(winCondition, xoBoard);

        Assert.assertTrue(checkWinner.doWeHaveAWinner());
    }
}