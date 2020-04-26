package argmus.restaurantwebapp.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MenuCategoryNotEmptyException extends RuntimeException {
    public MenuCategoryNotEmptyException(String message) {
        super(message);
    }
}
