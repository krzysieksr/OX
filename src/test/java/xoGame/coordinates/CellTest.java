package xoGame.coordinates;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CellTest {

    @Test
    public void testGetCellIndex() {
    }

    @Test
    public void testParseForCorrectCell() {
        String cellNumber="4";
        Cell cell = Cell.parse(cellNumber);

        Assert.assertEquals(cell.getCellIndex(), 4);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testForCellLessThanOne() {
        String cellNumber="-1";
        Cell.parse(cellNumber);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testForIllegalArgument() {
        String cellNumber="some string";
        Cell.parse(cellNumber);
    }
}