package cg.park.simpleauth.common.auth;

import cg.park.simpleauth.common.enums.ResultType;
import cg.park.simpleauth.common.util.*;
import cg.park.simpleauth.domain.user.User;
import cg.park.simpleauth.domain.user.UserRepository;
import cg.park.simpleauth.domain.user.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtProvider jwt;

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(JwtProvider jwt, UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwt = jwt;
    }

    public ApiResponse token(Auth auth) {
        User user = userRepository.findByUser(auth.getUserId());

        if (user == null) {
            return new ApiResponse(ResultType.INVALID_LOGIN);
        }
        String token = jwt.create(user);
        return new ApiResponse(ResultType.SUCCESS, token, "token");
    }

    public ApiResponse cert(String token) {
        try {
            Claims parserToken = jwt.parseJwtToken(token);
            return new ApiResponse(ResultType.SUCCESS, parserToken, "parserToken");
        }
        catch (Exception e) {
            return new ApiResponse(ResultType.INVALID_TOKEN);
        }
    }

}
