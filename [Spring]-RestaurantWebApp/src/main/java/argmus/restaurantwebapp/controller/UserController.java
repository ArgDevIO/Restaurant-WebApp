package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.User;
import argmus.restaurantwebapp.payload.JWTLoginSuccessResponse;
import argmus.restaurantwebapp.payload.LoginRequest;
import argmus.restaurantwebapp.security.JwtTokenProvider;
import argmus.restaurantwebapp.service.MapValidationErrorService;
import argmus.restaurantwebapp.service.UserService;
import argmus.restaurantwebapp.validator.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static argmus.restaurantwebapp.security.SecurityConstants.HEADER_STRING;
import static argmus.restaurantwebapp.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping(value = "/api/users", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final MapValidationErrorService mapValidationErrorService;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, UserValidator userValidator, MapValidationErrorService mapValidationErrorService, JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.mapValidationErrorService = mapValidationErrorService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        // Validate passwords match
        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    @PostMapping("/{userId}/addresses/new")
    public ResponseEntity<?> newAddressToUser(@Valid @RequestBody Address address,
                                              @PathVariable Long userId,
                                              @RequestHeader(HEADER_STRING) String token,
                                              BindingResult result) {
        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(this.userService.newAddressToUser(userId, address, token), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    }
}
