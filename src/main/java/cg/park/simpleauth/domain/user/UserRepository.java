package cg.park.simpleauth.domain.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {

    private User getUser(String userId) {
        return new User.Builder()
            .userId(userId)
            .userName("테스트")
            .userAge(99)
            .build();
    }

    private User getUser(String userId, String name, int age) {
        return new User.Builder()
            .userId(userId)
            .userName(name)
            .userAge(age)
            .build();
    }

    public User findByUser(String userId) {
        return getUser(userId);
    }

    private ArrayList<User> getFindAll(int count) {
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(getUser("tester_"+i, "name_"+i, i));
        }
        return list;
    }

    public ArrayList<User> findAll() {
        return getFindAll(10);
    }
}
