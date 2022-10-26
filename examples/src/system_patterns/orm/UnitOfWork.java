package system_patterns.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {

    private Connection conn;

    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updatedUsers = new ArrayList<>();
    private final List<User> deletedUsers = new ArrayList<>();

    public UnitOfWork(Connection conn) {
        this.conn = conn;
    }

    public void registerNew(User user) {
            this.newUsers.add(user);
    }

    public void registerUpdate(User user) {
        this.updatedUsers.add(user);
    }

    public void registerDelete(User user) {
            this.deletedUsers.add(user);
    }

    public void commit() {
        insert();
        update();
        delete();
    }

    //TODO complete methods
    private void insert() {
        if (!newUsers.isEmpty()) {
            try {
                PreparedStatement ps = conn.prepareStatement(DBQuery.INSERT.query);
                for (User newUser : newUsers) {
                    ps.setLong(1, newUser.getId());
                    ps.setString(2, newUser.getLogin());
                    ps.setString(3, newUser.getPassword());
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
                deletedUsers.clear();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private void update() {
        if (!updatedUsers.isEmpty()) {
            try {
                PreparedStatement ps = conn.prepareStatement(DBQuery.UPDATE.query);
                for (User user : updatedUsers) {
                    ps.setString(1,user.getLogin());
                    ps.setString(2, user.getPassword());
                    ps.setLong(3, user.getId());
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
                updatedUsers.clear();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private void delete() {
        if (!deletedUsers.isEmpty()) {
            try {
                PreparedStatement ps = conn.prepareStatement(DBQuery.DELETE.query);
                for (User user : deletedUsers) {
                    ps.setLong(1, user.getId());
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
                deletedUsers.clear();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

