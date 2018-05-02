package xoGame.components;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.coordinates.Cell;
import xoGame.coordinates.Coordinates;
import xoGame.xoGameExceptions.CellBusyException;

import java.util.stream.IntStream;

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

    @Test
    public void testApplyMoveForExistingFreeCell() throws CellBusyException {
        //given
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        Cell cell = Cell.parse("7");
        Player player = Player.X;

        //when
        xoBoard.applyMove(cell, player);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testApplyMoveForNotExistingCell() throws CellBusyException {
        //given
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        Cell coordinates = Cell.parse("15");
        Player player = Player.X;

        //when and then
        xoBoard.applyMove(coordinates, player);

    }

    @Test(expectedExceptions = CellBusyException.class)
    public void testApplyMoveForExistingBusyCell() throws CellBusyException {
        //given
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        Cell cell = Cell.parse("6");
        Player playerX = Player.X;
        Player playerO = Player.O;


        //when and then
        xoBoard.applyMove(cell, playerX);
        xoBoard.applyMove(cell, playerO);
    }


    @Test
    public void testGetIndexAtMap() {
        int x1 = 3;
        int y1 = 2;

        int x2 = 2;
        int y2 = 3;
        String boardDimension = "3 3";
        XOBoard xoBoard = XOBoard.parse(boardDimension);

        int indexAtMap1 = xoBoard.getIndexAtMap(x1, y1);
        int indexAtMap2 = xoBoard.getIndexAtMap(x2, y2);

        Assert.assertEquals(indexAtMap1, 6);
        Assert.assertEquals(indexAtMap2, 8);
    }

    @Test
    public void testGetCoordinates() {
        int x = 3;
        int y = 2;
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);

        Coordinates coordinates = xoBoard.getCoordinates(6);

        Assert.assertEquals(coordinates.getX(), x);
        Assert.assertEquals(coordinates.getY(), y);
    }

    @Test
    public void testGetX() {
        int x = 3;
        int y = 4;
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);

        Assert.assertEquals(xoBoard.getX(), x);
    }

    @Test
    public void testGetY() {
        int x = 3;
        int y = 4;
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);

        Assert.assertEquals(xoBoard.getY(), y);
    }

    @Test
    public void testGetRecentTypedIndex() throws CellBusyException {
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        xoBoard.applyMove(Cell.parse("3"), Player.X);
        xoBoard.applyMove(Cell.parse("7"), Player.O);

        int index = xoBoard.getRecentTypedIndex();

        Assert.assertEquals(index, 7);
    }

    @Test
    public void testIsBoardFullForFullBord() {
        String boardDimension = "3 3";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        Player currentPlayer = Player.X;
        IntStream.range(1, 10).forEach(i -> {
            try {
                xoBoard.applyMove(Cell.parse(String.valueOf(i)), currentPlayer);
                currentPlayer.getOppositePlayer();
            } catch (CellBusyException e) {
                e.getCellIndex();
            }
        });

        Assert.assertTrue(xoBoard.isBoardFull());
    }


    @Test
    public void testIsBoardFullForNotBoard() throws CellBusyException {
        String boardDimension = "3 3";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        xoBoard.applyMove(Cell.parse("3"), Player.X);

        Assert.assertTrue(!xoBoard.isBoardFull());
    }

    @Test
    public void testGetCharAtIndexForExistingCell() throws CellBusyException {
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        xoBoard.applyMove(Cell.parse("3"), Player.X);
        xoBoard.applyMove(Cell.parse("7"), Player.O);

        Assert.assertEquals(xoBoard.getCharAtIndex(3), Player.X);
        Assert.assertEquals(xoBoard.getCharAtIndex(7), Player.O);
    }

    @Test
    public void testGetCharAtIndexForEmptyCells() throws CellBusyException {
        String boardDimension = "3 4";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        xoBoard.applyMove(Cell.parse("3"), Player.X);
        xoBoard.applyMove(Cell.parse("7"), Player.O);

        Assert.assertEquals(xoBoard.getCharAtIndex(8), Player.EMPTY);
        Assert.assertEquals(xoBoard.getCharAtIndex(9), Player.EMPTY);
    }


    @Test
    public void testGetCharAtCoor() throws CellBusyException {
        String boardDimension = "3 3";
        XOBoard xoBoard = XOBoard.parse(boardDimension);
        xoBoard.applyMove(Cell.parse("3"), Player.X);
        xoBoard.applyMove(Cell.parse("8"), Player.O);
        Coordinates coordinatesFor3 = Coordinates.parse("3 1");
        Coordinates coordinatesFor8 = Coordinates.parse("2 3");

        Assert.assertEquals(xoBoard.getCharAtCoor(coordinatesFor3), Player.X);
        Assert.assertEquals(xoBoard.getCharAtCoor(coordinatesFor8), Player.O);
    }

    @Test
    public void testGetShorterDimensionX() {
        String boardDimension = "3 5";
        XOBoard xoBoard = XOBoard.parse(boardDimension);


        Assert.assertEquals(xoBoard.getShorterDimension(), 3);
    }

    @Test
    public void testGetShorterDimensionY() {
        String boardDimension = "7 5";
        XOBoard xoBoard = XOBoard.parse(boardDimension);


        Assert.assertEquals(xoBoard.getShorterDimension(), 5);
    }
}