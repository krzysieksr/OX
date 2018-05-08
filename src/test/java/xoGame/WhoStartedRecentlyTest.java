package xoGame;

import org.testng.Assert;
import org.testng.annotations.Test;
import xoGame.components.Player;

import static org.testng.Assert.*;

public class WhoStartedRecentlyTest {

    @Test
    public void testGetWhoStartedRecently() {
        Player beginPlayer = Player.X;
        WhoStartedRecently whoStartedRecently = new WhoStartedRecently(beginPlayer);
        beginPlayer = beginPlayer.getOppositePlayer();
        beginPlayer = beginPlayer.getOppositePlayer();
        beginPlayer = beginPlayer.getOppositePlayer();

        Assert.assertEquals(whoStartedRecently.getWhoStartedRecently(beginPlayer).name(), Player.X.name());
    }
}