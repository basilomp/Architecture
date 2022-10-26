package system_patterns.orm;

import java.sql.Connection;

public class UserApp {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.connect();
        UserRepository userRepository = new UserRepository(connection);
        User user = new User(4, "Pyotr", "pyotr");
        User user2 = new User(5, "Mikhail", "mikhail");

//        User user1 = new User(2, "Sergey", "new");
//        userRepository.update(user1);

//        User user4 = new User(2);
//        userRepository.delete(user3);
//        userRepository.insert(user);
//        userRepository.insert(user2);
        User user5 = new User(6, "t", "t");
        userRepository.insert(user5);
        User user6 = new User(6);
        userRepository.delete(user6);
        userRepository.commitTransaction();



    }

}
