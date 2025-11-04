package cg.park.simpleauth.domain.user;

import cg.park.simpleauth.common.annotaion.ValidJwt;
import cg.park.simpleauth.common.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ValidJwt
    @GetMapping
    public ResponseEntity<ApiResponse> users() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @ValidJwt
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> user(@PathVariable String userId) {
        return new ResponseEntity<>(userService.findByUser(userId), HttpStatus.OK);
    }

}
