package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.Role;
import argmus.restaurantwebapp.model.User;

public interface UserService {

    User saveUser(User newUser);

    User getUserById(Long userId);

    Address newAddressToUser(Long userId, Address address, String token);

    void initAdmin(Role admin);
}
