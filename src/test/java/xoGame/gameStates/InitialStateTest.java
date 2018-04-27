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
        //given
        GameState gameState = new InitialState();

        String input = "X\n2 3\n3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        gameState.printTo(System.out::println);
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;

        //then
        gameState.moveToNextState(userInputProvider);


    }

    //TODO
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void testMoveToNextStateAndThrowIllegalArgumentException() {
//        //given
//        String input = "some string";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        InitialState initialState = new InitialState();
//        initialState.printTo(System.out::println);
//        Supplier<String> userInputProvider=new Scanner(System.in)::nextLine;
//
//        //when and then
//        initialState.moveToNextState(userInputProvider);
//    }
}