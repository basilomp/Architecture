package system_patterns.orm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFromFile implements Config{
    private final String url;

    private final String username;

    public ConfigFromFile(String fileName) {
        Properties prop = new Properties();
        try {
//            prop.load(getClass().getResourceAsStream(fileName));
            prop.load(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.url = prop.getProperty("db.url");
        this.username = prop.getProperty("db.username");
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getUsername() {
        return this.username;

    }
}

