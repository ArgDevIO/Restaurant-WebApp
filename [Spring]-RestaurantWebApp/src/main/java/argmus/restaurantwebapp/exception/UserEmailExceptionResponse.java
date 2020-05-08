package argmus.restaurantwebapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserEmailExceptionResponse {
    private String email;
}
