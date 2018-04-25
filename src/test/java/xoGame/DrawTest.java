package xoGame;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DrawTest {

    @Test
    public static void testMoveToNextState(){
        //given
        String input="some input";
        GameState gameState=new Draw();

        // when
        GameState resultGameState=gameState.moveToNextState(input);

        // then
        Assert.assertTrue(resultGameState instanceof InitialState);
    }
}
