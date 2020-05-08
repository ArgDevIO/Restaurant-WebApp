package argmus.restaurantwebapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JWTLoginSuccessResponse {

    private boolean success;
    private String token;

    @Override
    public String toString() {
        return "JWTLoginSuccessResponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}
