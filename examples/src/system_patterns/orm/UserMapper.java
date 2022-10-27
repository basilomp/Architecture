package system_patterns.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection conn;
    private final PreparedStatement selectUser;

    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection conn) {
        this.conn = conn;
        try {
            this.selectUser = conn.prepareStatement("select id, username, password from users where id = ?;");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet resultSet = selectUser.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public void update(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement(DBQuery.UPDATE.query);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insert(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement(DBQuery.INSERT.query);
            ps.setLong(1, user.getId());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement(DBQuery.DELETE.query);
            ps.setLong(1, user.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

