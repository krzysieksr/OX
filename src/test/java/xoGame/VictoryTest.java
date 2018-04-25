package xoGame;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VictoryTest {
    @Mock
    Player player;

    @Test
    public void testMoveToNextState() {
        //given
        Victory victory = new Victory(player);
        String input = "some input";

        //when
        GameState resultGameState = victory.moveToNextState(input);

        //then
        Assert.assertTrue(resultGameState instanceof InitialState);
    }
}