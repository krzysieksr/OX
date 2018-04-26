package xoGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class CoordinatesTest {

    @Test
    public void testGetX() {
        String input = "2 3";

        Coordinates coordinates = Coordinates.parse(input);

        Assert.assertEquals(2, coordinates.getX());
    }

    @Test
    public void testGetY() {
        String input = "2 3";

        Coordinates coordinates = Coordinates.parse(input);

        Assert.assertEquals(3, coordinates.getY());
    }

    @Test
    public void testParseForTrueCoordinates() {
        String input = "2 3";

        Coordinates coordinates = Coordinates.parse(input);

        Assert.assertTrue(coordinates instanceof Coordinates);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testParseFoWrongInputAndExpectException() {
        String input = "some string";

        Coordinates.parse(input);
    }
}