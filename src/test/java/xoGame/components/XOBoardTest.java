package xoGame.components;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.XOBoard;

public class XOBoardTest {

    @Test(expectedExceptions = NegativeArraySizeException.class)
    public void testParseForNegativeXDimension() {
        //given
        String boardDimension = "-2 3";

        XOBoard.parse(boardDimension);
    }

    @Test(expectedExceptions = NegativeArraySizeException.class)
    public void testParseForNegativeYDimension() {
        //given
        String boardDimension = "2 -3";

        XOBoard.parse(boardDimension);
    }

    @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void testParseForNotEnoughtArumentsAmount() {
        //given
        String boardDimension = "2";

        XOBoard.parse(boardDimension);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testParseForStringWithoutNumbers() {
        //given
        String boardDimension = "some string";

        XOBoard.parse(boardDimension);
    }

    @Test()
    public void testParseForCorrectBoardDimensions() {
        //given
        String boardDimension = "3 4";

        XOBoard xoBoard = XOBoard.parse(boardDimension);

        Assert.assertEquals(xoBoard.getX(), 3);
        Assert.assertEquals(xoBoard.getY(), 4);
    }

//    @Test
//    public void testApplyMoveForExistingFreeCell() throws CellBusyException {
//        //given
//        String boardDimension = "3 4";
//        XOBoard xoBoard = XOBoard.parse(boardDimension);
//        Coordinates coordinates = new Coordinates(2, 3);
//        Player player = Player.X;
//
//        //when
//        xoBoard.applyMove(coordinates, player);
//
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void testApplyMoveForNotExistingCell() throws CellBusyException {
//        //given
//        String boardDimension = "3 4";
//        XOBoard xoBoard = XOBoard.parse(boardDimension);
//        Coordinates coordinates = new Coordinates(7, 3);
//        Player player = Player.X;
//
//        //when
//        xoBoard.applyMove(coordinates, player);
//
//    }
//
//    @Test(expectedExceptions = CellBusyException.class)
//    public void testApplyMoveForExistingBusyCell() throws CellBusyException {
//        //given
//        String boardDimension = "3 4";
//        XOBoard xoBoard = XOBoard.parse(boardDimension);
//        Coordinates coordinates = new Coordinates(2, 4);
//        Player playerX = Player.X;
//        Player playerO = Player.O;
//
//
//        //when
//        xoBoard.applyMove(coordinates, playerX);
//        xoBoard.applyMove(coordinates, playerO);
//
//    }
}