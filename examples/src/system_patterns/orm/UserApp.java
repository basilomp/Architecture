package system_patterns.orm;

import java.sql.Connection;

public class UserApp {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.connect();
        UserRepository userRepository = new UserRepository(connection);
        User user1 = new User(1, "Petr", "petr");
        User user2 = new User(2, "Mikhail", "mikhail");
        User user3 = new User(3, "Sergey", "new");
        User user4 = new User(4, "new_login", "new password");
        User user5 = new User(5, "t", "t");
        User user6 = new User(6);

//        userRepository.insert(user1);
//        userRepository.insert(user2);
//        userRepository.insert(user3);
//        userRepository.insert(user4);
//        userRepository.insert(user5);
//        userRepository.insert(user6);

//        userRepository.update(user1);
//        userRepository.update(user4);

        userRepository.delete(user1);
        userRepository.delete(user4);

        userRepository.commitTransaction();
    }
}
