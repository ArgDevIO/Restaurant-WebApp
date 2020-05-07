package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.model.User;
import argmus.restaurantwebapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@Valid @RequestBody User user) {
        // Validate passwords match
        return this.userService.saveUser(user);
    }
}
