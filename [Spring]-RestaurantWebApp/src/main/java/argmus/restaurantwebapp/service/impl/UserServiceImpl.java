package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.exception.UserDoesntExistException;
import argmus.restaurantwebapp.exception.UserEmailAlreadyExistsException;
import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.model.OTP;
import argmus.restaurantwebapp.model.Role;
import argmus.restaurantwebapp.model.User;
import argmus.restaurantwebapp.repository.AddressRepository;
import argmus.restaurantwebapp.repository.OTPRepository;
import argmus.restaurantwebapp.repository.RoleRepository;
import argmus.restaurantwebapp.repository.UserRepository;
import argmus.restaurantwebapp.security.JwtTokenProvider;
import argmus.restaurantwebapp.service.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static argmus.restaurantwebapp.security.SecurityConstants.TOKEN_PREFIX;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final OTPRepository otpRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenProvider jwtTokenProvider, OTPRepository otpRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.otpRepository = otpRepository;
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

    @Override
    public Object sendCode(String phone) {
        // Check if current phone has already requested a code, if yes, delete it and send a new one
        // This enables this same method also for resending a code
        OTP otp = this.otpRepository.findOTPByPhone(phone);
        if (otp != null)
            this.otpRepository.delete(otp);

        String ACCOUNT_SID = "AC4d96225dbb8ef4ebcc7e384bf0e4c9c8";
        String AUTH_TOKEN = "6794028751aea00e942017ba577fee12";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Map<String, String> returnObj = new HashMap<>();

        int otp_code = randomOTP();
        String text = "--GardenWebApp--\nYour verification code is: ";

        try {
            // Temp save phone/code to db
            this.otpRepository.save(new OTP(phone, otp_code));

            Message.creator(
                    new PhoneNumber("+389" + phone.substring(1)),
                    new PhoneNumber("+15312042573"),
                    (text + otp_code))
                    .create();
        } catch (Exception e) {
            returnObj.put("message", e.getMessage().contains("'To'") ? e.getMessage().replace("'To'", "") : e.getMessage());
            return returnObj;
        }

        returnObj.put("message", "Sent");
        return returnObj;
    }

    @Override
    public Object verifyCode(String phone, int code) {
        Map<String, String> returnObj = new HashMap<>();
        OTP otp = this.otpRepository.findOTPByPhone(phone);

        Date now = new Date();

        if (otp == null) {
            returnObj.put("message", "Can't find code for this number: " + phone);
            return returnObj;
        } else if (otp.getCode() != code) {
            returnObj.put("message", "Incorrect code, try again!");
            return returnObj;
        } else if (otp.getExpiryDateTime().before(now)) {
            this.otpRepository.delete(otp);
            returnObj.put("message", "Code has expired, try sending a new one again");
            return returnObj;
        }
        this.otpRepository.delete(otp);
        returnObj.put("message", "Successfully verified");
        return returnObj;
    }

    private int randomOTP() {
        Random r = new Random();
        int low = 11111;
        int high = 99999;
        return r.nextInt(high - low) + low;
    }
}
