package argmus.restaurantwebapp.validator;

import argmus.restaurantwebapp.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        String regex = "^(?=.*[0-9]).{8,}$"; // at least 8 characters and 1 number
        if (!user.getPassword().matches(regex)) {
            errors.rejectValue("password", "Length", "Password must contain at least 8 characters and 1 number");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match" ,"Passwords must match");
        }
    }
}
