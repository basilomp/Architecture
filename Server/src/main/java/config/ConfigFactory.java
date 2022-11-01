package config;

import org.jetbrains.annotations.NotNull;

public class ConfigFactory {
    private static final int ARGS_FOR_CONFIG_FROM_CLI = 2;
    private static final String PATH_TO_CONFIG = "./server.properties";

    public static @NotNull Config create(String[] args) {
        //Magic number
        if (args.length == ARGS_FOR_CONFIG_FROM_CLI) {
            return new ConfigFromCli(args);
        } else {
            return new ConfigFromFile("server/src/main/resources/server.properties");
        }
    }
}