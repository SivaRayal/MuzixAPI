package com.ness.muzix.WishListService.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WishListAPIExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value= {WishListException.class})
	public ResponseEntity<?> handleWishListException(WishListException exception){
		return new ResponseEntity<>(exception.getLocalizedMessage(),new HttpHeaders(),HttpStatus.CONFLICT);
	}
}
