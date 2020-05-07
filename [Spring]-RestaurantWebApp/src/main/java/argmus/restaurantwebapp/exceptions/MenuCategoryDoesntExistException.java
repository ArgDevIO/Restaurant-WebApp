package argmus.restaurantwebapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MenuCategoryDoesntExistException extends RuntimeException {
    public MenuCategoryDoesntExistException() {
        super("Category doesn't exist!");
    }
}
