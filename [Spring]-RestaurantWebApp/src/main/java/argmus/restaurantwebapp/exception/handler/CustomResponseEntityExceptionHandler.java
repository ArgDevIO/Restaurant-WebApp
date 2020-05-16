package argmus.restaurantwebapp.exception.handler;

import argmus.restaurantwebapp.exception.UserDoesntExistException;
import argmus.restaurantwebapp.exception.UserEmailAlreadyExistsException;
import argmus.restaurantwebapp.exception.UserPhoneAlreadyExistsException;
import argmus.restaurantwebapp.exception.response.UserDoesntExistResponse;
import argmus.restaurantwebapp.exception.response.UserEmailAlreadyExistsResponse;
import argmus.restaurantwebapp.exception.response.UserPhoneAlreadyExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserDoesntExist(UserDoesntExistException ex) {
        UserDoesntExistResponse exceptionResponse = new UserDoesntExistResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserEmailAlreadyExists(UserEmailAlreadyExistsException ex) {
        UserEmailAlreadyExistsResponse exceptionResponse = new UserEmailAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserPhoneAlreadyExists(UserPhoneAlreadyExistsException ex) {
        UserPhoneAlreadyExistsResponse exceptionResponse = new UserPhoneAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
