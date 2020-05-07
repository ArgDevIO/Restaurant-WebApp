package argmus.restaurantwebapp.service.impl;

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
    public User saveUser(User newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        // email has to be unique (exception)
        // make sure that password & confirm password match
        // we don't persist or show the confirmPassword
        return this.userRepository.save(newUser);
    }
}
