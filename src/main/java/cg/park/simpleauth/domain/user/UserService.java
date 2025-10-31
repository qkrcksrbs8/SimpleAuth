package cg.park.simpleauth.domain.user;

import cg.park.simpleauth.common.auth.Auth;
import cg.park.simpleauth.common.enums.ResultType;
import cg.park.simpleauth.common.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Message findByUser(String userId) {
         User user = userRepository.findByUser(userId);
         if (null == user)  {
             return new Message(ResultType.INVALID_PARAMETER);
         }
        return new Message(ResultType.SUCCESS, user, "user");
    }

    public Message findAll() {
        return new Message(ResultType.SUCCESS, userRepository.findAll(), "users");
    }
}
