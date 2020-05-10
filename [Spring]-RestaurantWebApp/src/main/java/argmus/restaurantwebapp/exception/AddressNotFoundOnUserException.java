package argmus.restaurantwebapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddressNotFoundOnUserException extends RuntimeException {
    public AddressNotFoundOnUserException() {
        super("Address not found on user");
    }
}
