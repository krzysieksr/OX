package xoGame.results;

import org.testng.annotations.Test;
import xoGame.gameStates.InitialState;

public class InitialStateTest {


    @Test
    public void testMoveToNextState() {
        //TODO method has changed, cannot perform tests
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoveToNextStateAndThrowIllegalArgumentException() {
        //given
        String input = "some string";
        InitialState initialState = new InitialState();

        //when and then
        initialState.moveToNextState(input);
    }
}