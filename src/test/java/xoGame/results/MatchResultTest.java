package xoGame.results;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MatchResultTest {

    @Test
    public void testGetMessage() {
        MatchResult matchResult = MatchResult.X;

        Assert.assertEquals(matchResult.getMessage(), "Match won by X player.");
    }
}