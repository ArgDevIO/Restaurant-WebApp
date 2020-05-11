package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.exception.UserDoesntExistException;
import argmus.restaurantwebapp.exception.UserEmailAlreadyExistsException;
import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.Role;
import argmus.restaurantwebapp.model.User;
import argmus.restaurantwebapp.repository.AddressRepository;
import argmus.restaurantwebapp.repository.RoleRepository;
import argmus.restaurantwebapp.repository.UserRepository;
import argmus.restaurantwebapp.security.JwtTokenProvider;
import argmus.restaurantwebapp.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static argmus.restaurantwebapp.security.SecurityConstants.TOKEN_PREFIX;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User registerUser(User newUser, String role) {
        // Email has to be unique
        if (this.userRepository.existsUserByEmail(newUser.getEmail()))
            throw new UserEmailAlreadyExistsException("User email '" + newUser.getEmail() + "' already exists");

        // Encode password
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        // Get primary address from newUser
        Address primaryAddress = null;
        if (!newUser.getAddresses().isEmpty())
            primaryAddress = newUser.getAddresses().iterator().next();

        // Set ROLE_? to newUser
        Role roleUser = this.roleRepository.findRoleByName(role);
        roleUser.getUsers().add(newUser);
        newUser.getRoles().add(roleUser);

        // Save to DB
        User savedUser = this.userRepository.save(newUser);

        if (primaryAddress != null) {
            primaryAddress.setUser(savedUser);
            this.addressRepository.save(primaryAddress);
        }

        return savedUser;
    }

    @Override
    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new UserDoesntExistException("User with ID '" + userId + "' doesn't exist"));
    }

    @Override
    public Address newAddressToUser(Long userId, Address address, String token) {
        Long currentId = jwtTokenProvider.getUserIdFromJWT(token.substring(TOKEN_PREFIX.length()));
        if (!userId.equals(currentId)) throw new AccessDeniedException("Can't access other users details!");

        User user = getUserById(userId);
        address.setUser(user);
        return this.addressRepository.save(address);
    }

    @Override
    public void initAdmin(Role adminRole) {
        if (!this.userRepository.existsUserByEmail("admin@garden.com")) {
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User();
            admin.setFullName("Admin");
            admin.setEmail("admin@garden.com");
            admin.setPassword("admin@admin1");
            admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
            admin.setPhone("000");
            admin.setRoles(roles);

            this.userRepository.save(admin);
        }
    }
}
