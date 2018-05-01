package xoGame;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

public class GameLanguage {
    private Map<String, String> langMap = new HashMap<>();
//    private String prefix = "src/main/resources/";
    private Consumer<String> output;

    public GameLanguage(Consumer<String> output) {
        this.output = output;
    }

    public Properties getGameLang(String lang) {
        loadLangMap();
        Properties properties = new Properties();
        if (langMap.containsKey(lang)) {
            try {
                File configFile = new File(langMap.get(lang));
                InputStream inputStream = new FileInputStream(configFile);
                properties.load(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
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
            File configFile = new File("game_lang_map.properties");
            InputStream inputStream = new FileInputStream(configFile);
            Properties properties = new Properties();
            properties.load(inputStream);
            for (final String name : properties.stringPropertyNames())
                langMap.put(name, properties.getProperty(name));
        } catch (IOException e) {
            output.accept("Cannot load file: " + e.getMessage());
            System.exit(1);
        }
    }
}
