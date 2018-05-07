package xoGame.components;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.Player;

public class PlayerTest {

    @Test
    public void testGetOppositePlayerAndExpectO() {
        Player player = Player.X;

        Player returnedPlayer = player.getOppositePlayer();

        Assert.assertEquals(Player.O, returnedPlayer);
    }


    @Test
    public void testGetOppositePlayerAndExpectX() {
        Player player = Player.O;

        Player returnedPlayer = player.getOppositePlayer();

        Assert.assertEquals(Player.X, returnedPlayer);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetOppositePlayerAndExpectIllegalArgumentException() {
        Player.valueOf("some string");
    }


}