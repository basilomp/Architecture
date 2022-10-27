package system_patterns.orm;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {

    private final UserMapper userMapper;
    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updatedUsers = new ArrayList<>();
    private final List<User> deletedUsers = new ArrayList<>();

    public UnitOfWork(UserMapper userMapper) {
        this.userMapper = userMapper;
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
        clear();

    }

    private void insert() {
        this.newUsers.forEach(userMapper::insert);
    }

    private void update() {
        this.updatedUsers.forEach(userMapper::update);
    }

    private void delete() {
        this.deletedUsers.forEach(userMapper::delete);
    }

    public void clear() {
        this.newUsers.clear();
        this.updatedUsers.clear();
        this.deletedUsers.clear();
    }
}

