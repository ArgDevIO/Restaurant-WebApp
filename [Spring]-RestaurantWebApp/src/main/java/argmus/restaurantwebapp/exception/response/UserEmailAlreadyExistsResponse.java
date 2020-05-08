package argmus.restaurantwebapp.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserEmailAlreadyExistsResponse {
    private String email;
}
