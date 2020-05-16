package argmus.restaurantwebapp.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPhoneAlreadyExistsResponse {
    private String phone;
}
