package xoGame;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

public class GameLanguage {
    private Map<String, String> langMap = new HashMap<>();
    ;
    private Consumer<String> output;

    public GameLanguage(Consumer<String> output) {
        this.output = output;
    }

    public Properties getGameLang(String lang) {
        loadLangMap();
        Properties properties = new Properties();
        if (langMap.containsKey(lang)) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(Main.class.getResourceAsStream(langMap.get(lang)), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                properties.load(bufferedReader);
                return properties;
            } catch (IOException e) {
                output.accept("Cannot load file: " + e.getMessage());
                System.exit(1);
            }
        } else {
            throw new IllegalArgumentException();
        }
        return properties;

    }


    private void loadLangMap() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(Main.class.getResourceAsStream("game_lang_map.properties"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            Properties properties = new Properties();
            properties.load(bufferedReader);
            for (final String name : properties.stringPropertyNames())
                langMap.put(name, properties.getProperty(name));
        } catch (IOException e) {
            output.accept("Cannot load file: " + e.getMessage());
            System.exit(1);
        }
    }
}
