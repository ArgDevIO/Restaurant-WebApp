package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.exception.UserEmailException;
import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.User;
import argmus.restaurantwebapp.repository.AddressRepository;
import argmus.restaurantwebapp.repository.UserRepository;
import argmus.restaurantwebapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveUser(User newUser) {
        // Encode password
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        // Get primary address from newUser
        Address primaryAddress = newUser.getAddresses().iterator().next();
        // Clear addresses of newUser
        newUser.setAddresses(new HashSet<>());

        try {
            User savedUser = this.userRepository.save(newUser);
            primaryAddress.setUser(savedUser);
            this.addressRepository.save(primaryAddress);
            return savedUser;
        } catch (Exception e) {
            throw new UserEmailException("User email '" + newUser.getEmail() + "' already exists");
        }

        // make sure that password & confirm password match
        // we don't persist or show the confirmPassword
    }
}
