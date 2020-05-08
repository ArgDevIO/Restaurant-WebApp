package argmus.restaurantwebapp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserExceptionResponse {
    @JsonInclude(Include.NON_NULL)
    private String email;
    @JsonInclude(Include.NON_NULL)
    private String id;
}
