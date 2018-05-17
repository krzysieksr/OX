package xoGame.components;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayerParser {
    private Player player = Player.X;
    private Supplier<String> userInputProvider;
    private Consumer<String> output;
    private Properties language;


    public PlayerParser(Supplier<String> userInputProvider, Consumer<String> output, Properties language) {
        this.userInputProvider = userInputProvider;
        this.output = output;
        this.language = language;
    }

    public Player parse() {
        output.accept(language.getProperty("selectNameForX","Select player name for X sign:"));
        player.setPlayerName(userInputProvider.get());
        output.accept(language.getProperty("selectNameForO","Select player name for O sign:"));
        player.getOppositePlayer().setPlayerName(userInputProvider.get());
        parseWhoStarts();
        return player;
    }

    private void parseWhoStarts() {
        try {
            output.accept(language.getProperty("whoStarts", "Who shall start (X or O) ?"));
            player = Player.valueOf(userInputProvider.get().toUpperCase());
        } catch (IllegalArgumentException e) {
            output.accept(language.getProperty("wrongTypeXorO","Wrong, type X or O."));
            parseWhoStarts();
        }
    }
}
