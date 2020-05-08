package argmus.restaurantwebapp.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDoesntExistResponse {
    private String id;
}
