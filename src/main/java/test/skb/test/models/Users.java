package test.skb.test.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

public class Users {
    private static HashMap<String, User> userHashMap = new HashMap<>();
    static {
        addUser(new User("1", "Александр Михайлов", LocalDateTime.of(1998, 3, 28, 0, 0, 0)));
        addUser(new User("2", "Иван Михайлов", LocalDateTime.of(1997, 3, 28, 0, 0, 0)));

    }

    private static void addUser(User user) {
        userHashMap.put(user.getId(), user);
    }

    public static Optional<User> findById(String id) {
        Optional<User> optionalUser = Optional.empty();

        if(id != null) {
            User user = userHashMap.get(id);
            if(user != null) {
                optionalUser = Optional.of(user);
            }
        }
        return optionalUser;
    }
}
