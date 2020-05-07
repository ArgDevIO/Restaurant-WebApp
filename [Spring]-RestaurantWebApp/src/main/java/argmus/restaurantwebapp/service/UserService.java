package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.User;

public interface UserService {

    User saveUser(String fullName, String email, String password, String confirmPassword, String phone, Address primaryAddress);
}
