package system_patterns.orm;

import java.sql.Connection;
import java.util.Optional;

public class UserRepository {
    private final Connection connection;
    private final UserMapper userMapper;
    private UnitOfWork unitOfWork;

    public UserRepository(Connection connection) {
        this.connection = connection;
        this.userMapper = new UserMapper(connection);
        this.unitOfWork = new UnitOfWork(userMapper);
    }

    public Optional<User> findById(long id) {
        return userMapper.findById(id);
    }

    public void insert(User user) {
        unitOfWork.registerNew(user);
    }

    public void beginTransaction() {
        this.unitOfWork = new UnitOfWork(userMapper);
    }

    public void update(User user) {
        unitOfWork.registerUpdate(user);
    }

    public void delete(User user) {
        unitOfWork.registerDelete(user);
    }

    public void commitTransaction() {
        unitOfWork.commit();
    }
}
