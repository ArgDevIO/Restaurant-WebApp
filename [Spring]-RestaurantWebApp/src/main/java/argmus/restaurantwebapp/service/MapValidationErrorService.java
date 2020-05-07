package argmus.restaurantwebapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface MapValidationErrorService {
    ResponseEntity<?> MapValidationError(BindingResult result);
}
