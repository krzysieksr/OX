package xoGame;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XOBoardTest {

    @Test
    public void testApplyMoveForExistingFreeCell() {
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testApplyMoveForNotExistingCell() {
//        XOBoard xoBoard=new XOBoard();
//        Coordinates coordinates=new Coordinates()
//        xoBoard.applyMove();
    }

    @Test
    public void testApplyMoveForExistingBusyCell() {
    }
}