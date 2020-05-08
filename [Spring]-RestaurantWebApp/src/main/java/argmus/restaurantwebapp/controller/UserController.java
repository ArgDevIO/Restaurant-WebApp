package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.User;
import argmus.restaurantwebapp.service.MapValidationErrorService;
import argmus.restaurantwebapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final MapValidationErrorService mapValidationErrorService;

    public UserController(UserService userService, MapValidationErrorService mapValidationErrorService) {
        this.userService = userService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {

        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        // Validate passwords match
        return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/address/new")
    public ResponseEntity<?> newAddressToUser(@Valid @RequestBody Address address,
                                              @PathVariable Long userId,
                                              BindingResult result) {
        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(this.userService.newAddressToUser(userId, address), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    }
}
