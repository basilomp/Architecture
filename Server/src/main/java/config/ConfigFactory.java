package config;

public class ConfigFactory {

    public static Config create(String[] args) {
        if(args.length == 2) {
            return new ConfigFromCli(args);
        } else {
//            return new ConfigFromFile("./../../../../server.properties");
            return new ConfigFromFile("server/src/main/resources/server.properties");
        }
    }
}