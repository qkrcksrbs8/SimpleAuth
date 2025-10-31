package cg.park.simpleauth.domain.user;

import cg.park.simpleauth.common.enums.ResultType;
import cg.park.simpleauth.common.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse findByUser(String userId) {
         User user = userRepository.findByUser(userId);
         if (null == user)  {
             return new ApiResponse(ResultType.INVALID_PARAMETER);
         }
        return new ApiResponse(ResultType.SUCCESS, user, "user");
    }

    public ApiResponse findAll() {
        return new ApiResponse(ResultType.SUCCESS, userRepository.findAll(), "users");
    }
}
