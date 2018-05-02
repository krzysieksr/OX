package xoGame;

import org.testng.annotations.Test;

import java.util.Properties;
import java.util.function.Consumer;

import static org.testng.Assert.*;

public class GameLanguageTest {

    @Test
    public void testGetGameLangForExistingLanguage() {
        Consumer<String> consumer = System.out::println;
        GameLanguage gameLanguage = new GameLanguage(consumer);
        String plLang = "pl";

        gameLanguage.getGameLang(plLang);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetGameLangForNotExistingLanguage() {
        Consumer<String> consumer = System.out::println;
        GameLanguage gameLanguage = new GameLanguage(consumer);
        String abcLang = "abc";

        gameLanguage.getGameLang(abcLang);
    }
}