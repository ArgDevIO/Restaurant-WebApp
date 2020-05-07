package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.service.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorServiceImpl implements MapValidationErrorService {

    public ResponseEntity<?> MapValidationError(BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            result.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
