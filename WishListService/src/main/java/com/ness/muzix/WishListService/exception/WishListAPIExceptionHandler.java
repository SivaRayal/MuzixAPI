package com.ness.muzix.WishListService.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WishListAPIExceptionHandler {
	@ExceptionHandler(value= {WishListException.class})
	public ResponseEntity<?> handleWishListException(WishListException exception){
		return new ResponseEntity<>(exception,new HttpHeaders(),HttpStatus.CONFLICT);
	}
}
