package com.ness.muzix.AuthorizationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthorizationExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value={AuthorizationException.class})
    public ResponseEntity<?> handleAuthorizationException(AuthorizationException exception){
        return new ResponseEntity<>(exception.getLocalizedMessage(),HttpStatus.CONFLICT);
    }
}
