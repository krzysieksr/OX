package xoGame.gameStates;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Supplier;

public class InitialStateTest {


    @Test
    public void testMoveToNextState() {
        //TODO method has changed, cannot perform tests
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMoveToNextStateAndThrowIllegalArgumentException() {
        //given
        String input = "some string";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InitialState initialState = new InitialState();
        Supplier<String> userInputProvider=new Scanner(System.in)::nextLine;

        //when and then
        initialState.moveToNextState(userInputProvider);
    }
}