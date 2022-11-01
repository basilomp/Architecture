package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class ConfigFromFile implements Config {
    private final String wwwHome;

    private final int port;

    public ConfigFromFile(String fileName) {
        Properties prop = new Properties();
        FileInputStream in = null;
        try {
            //Improbability Factor – может возникнуть ошибка не закрытого ресурса, т.к. реализует AutoCloseable
            in = new FileInputStream(fileName);
            prop.load(in);
            in.close();
//            prop.load(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.wwwHome = prop.getProperty("www.home");
        this.port = Integer.parseInt(prop.getProperty("port"));
    }

    @Override
    public String getWwwHome() {
        return this.wwwHome;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
