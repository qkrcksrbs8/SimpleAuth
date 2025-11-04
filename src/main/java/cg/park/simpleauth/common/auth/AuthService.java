package cg.park.simpleauth.common.auth;

import cg.park.simpleauth.common.enums.ResultType;
import cg.park.simpleauth.common.util.*;
import cg.park.simpleauth.domain.user.User;
import cg.park.simpleauth.domain.user.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtProvider jwt;

    private final UserRepository userRepository;

    @Autowired
    public AuthService(JwtProvider jwt, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwt = jwt;
    }

    public ApiResponse token(Auth auth) {
        User user = userRepository.findByUser(auth.getUserId());
        return (user == null)
            ? new ApiResponse(ResultType.INVALID_LOGIN)
            : new ApiResponse(ResultType.SUCCESS, jwt.create(user), "token");
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
