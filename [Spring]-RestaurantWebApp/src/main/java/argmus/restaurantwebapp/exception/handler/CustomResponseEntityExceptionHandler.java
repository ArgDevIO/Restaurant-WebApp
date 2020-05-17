package argmus.restaurantwebapp.exception.handler;

import argmus.restaurantwebapp.exception.UserDoesntExistException;
import argmus.restaurantwebapp.exception.UserEmailAlreadyExistsException;
import argmus.restaurantwebapp.exception.UserPhoneAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final String handleUserDoesntExist(UserDoesntExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final String handleUserEmailAlreadyExists(UserEmailAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final String handleUserPhoneAlreadyExists(UserPhoneAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
