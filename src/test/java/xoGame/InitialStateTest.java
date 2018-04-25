package xoGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InitialStateTest {



    @Test
    public void testMoveToNextState() {
        //given
        String input="X";
        GameState initialState=new InitialState();

        //when
        GameState gameState=initialState.moveToNextState(input);

        //then
        Assert.assertTrue(gameState instanceof GameInProgress);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoveToNextStateAndThrowIllegalArgumentException(){
        //given
        String input="some string";
        InitialState initialState=new InitialState();

        //when and then
       initialState.moveToNextState(input);
    }
}