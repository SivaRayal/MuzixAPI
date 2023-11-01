package com.ness.muzix.LastFMService.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LastFMExceptionHandler {
	
	@ExceptionHandler(value={LastFMServiceException.class})
	public ResponseEntity<?> handleLastFMServiceException(LastFMServiceException exception){
		return new ResponseEntity<>(exception.getLocalizedMessage(),new HttpHeaders(),HttpStatus.CONFLICT);
	}

}
