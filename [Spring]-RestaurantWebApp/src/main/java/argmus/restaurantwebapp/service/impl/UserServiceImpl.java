package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.repository.UserRepository;
import argmus.restaurantwebapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
