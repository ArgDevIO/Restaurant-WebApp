package argmus.restaurantwebapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserPhoneAlreadyExistsException extends RuntimeException {
    public UserPhoneAlreadyExistsException(String message) {
        super(message);
    }
}
