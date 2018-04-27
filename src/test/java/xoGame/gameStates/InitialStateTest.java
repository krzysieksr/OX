package xoGame.gameStates;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Supplier;

public class InitialStateTest {


    @Test
    public void testMoveToNextState() {
        //given
        GameState gameState = new InitialState();
        String player = "X";
        String boardDimension = "3 4";
        String winningCondition = "3";
        String input = player + "\n" + boardDimension + "\n" + winningCondition;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        gameState.printTo(System.out::println);
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;

        //when
        GameState resultGameState = gameState.moveToNextState(userInputProvider);

        //then
        Assert.assertEquals(resultGameState.getClass(), GameInProgress.class);
    }
}