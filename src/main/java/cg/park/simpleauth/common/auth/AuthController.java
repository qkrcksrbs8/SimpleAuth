package cg.park.simpleauth.common.auth;

import cg.park.simpleauth.common.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity<ApiResponse> token(@RequestBody @Valid Auth auth) {
        return new ResponseEntity<>(authService.token(auth), HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<ApiResponse> cert(@RequestHeader(value = "Authorization") String token) {
        return new ResponseEntity<>(authService.cert(token), HttpStatus.OK);
    }
}
