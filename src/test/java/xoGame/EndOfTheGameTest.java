package xoGame;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndOfTheGameTest {

    @Test
    public void testMoveToNextState() {
        //given
        GameResult gameResult = new GameResult(new ScoreBoard());
        String input = "some string";
        EndOfTheGame endOfTheGame = new EndOfTheGame(gameResult);

        // when
        GameState resultGameState = endOfTheGame.moveToNextState(input);

        // then
        Assert.assertTrue(resultGameState instanceof InitialState);
    }
}
