package system_patterns.orm;

public class ConfigFactory {

    public static Config create() {
            return new ConfigFromFile("examples/src/resources/db.properties");
        }
    }
