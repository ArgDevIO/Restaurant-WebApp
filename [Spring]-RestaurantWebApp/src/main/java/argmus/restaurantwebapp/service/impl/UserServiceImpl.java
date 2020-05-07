package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.User;
import argmus.restaurantwebapp.repository.UserRepository;
import argmus.restaurantwebapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveUser(String fullName, String email, String password, String confirmPassword, String phone, Address primaryAddress) {
        User newUser = new User(fullName, email, bCryptPasswordEncoder.encode(password), bCryptPasswordEncoder.encode(confirmPassword), phone, primaryAddress);

        // email has to be unique (exception)
        // make sure that password & confirm password match
        // we don't persist or show the confirmPassword
        return this.userRepository.save(newUser);
    }
}
