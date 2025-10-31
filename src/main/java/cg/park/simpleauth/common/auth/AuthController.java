package cg.park.simpleauth.common.auth;

import cg.park.simpleauth.common.util.ApiResponse;
import cg.park.simpleauth.common.util.Message;
import cg.park.simpleauth.common.util.Param;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final ApiResponse apiResponse;

    private final AuthService authService;

    @Autowired
    public AuthController(ApiResponse apiResponse, AuthService authService) {
        this.apiResponse = apiResponse;
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity<Message> token(@RequestBody @Valid Auth auth) {
        return apiResponse.of(authService.token(auth));
    }

    @GetMapping("/token")
    public ResponseEntity<Message> cert(@RequestHeader(value = "Authorization") String token) {
        return apiResponse.of(authService.cert(token));
    }
}
