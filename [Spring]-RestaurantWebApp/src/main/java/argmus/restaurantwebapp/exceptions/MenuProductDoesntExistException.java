package argmus.restaurantwebapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MenuProductDoesntExistException extends RuntimeException {
    public MenuProductDoesntExistException() {
        super("Product doesn't exist!");
    }
}
