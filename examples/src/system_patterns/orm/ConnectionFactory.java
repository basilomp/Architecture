package system_patterns.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Config config = ConfigFactory.create();
    private static final String url = config.getUrl();
    private static final String username = config.getUsername();

    public static Connection connect() {
    Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, null);
            connection.setAutoCommit(false);
            System.out.println("Connected to db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of line");
        return connection;
}
}
