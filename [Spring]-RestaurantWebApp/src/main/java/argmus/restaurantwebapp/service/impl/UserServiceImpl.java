package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.exception.UserDoesntExistException;
import argmus.restaurantwebapp.exception.UserEmailAlreadyExistsException;
import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.User;
import argmus.restaurantwebapp.repository.AddressRepository;
import argmus.restaurantwebapp.repository.UserRepository;
import argmus.restaurantwebapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        // Email has to be unique
        if (this.userRepository.existsUserByEmail(newUser.getEmail()))
            throw new UserEmailAlreadyExistsException("User email '" + newUser.getEmail() + "' already exists");

        User savedUser = this.userRepository.save(newUser);
        
        primaryAddress.setUser(savedUser);
        this.addressRepository.save(primaryAddress);

        return savedUser;
    }

    @Override
    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new UserDoesntExistException("User with ID '" + userId + "' doesn't exist"));
    }

    @Override
    public Address newAddressToUser(Long userId, Address address) {
        User user = getUserById(userId);
        address.setUser(user);
        return this.addressRepository.save(address);
    }
}
