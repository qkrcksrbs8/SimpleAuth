package cg.park.simpleauth.domain.user;

import cg.park.simpleauth.common.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public Message users() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public Message user(@PathVariable String userId) {
        return userService.findByUser(userId);
    }

}
