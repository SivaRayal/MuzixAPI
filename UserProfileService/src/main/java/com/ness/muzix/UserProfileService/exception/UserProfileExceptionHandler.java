package com.ness.muzix.UserProfileService.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ness.muzix.UserProfileService.model.ResponseDto;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class UserProfileExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserProfileExceptionHandler.class);

	@ExceptionHandler
	@ResponseBody
	ResponseDto handleException(UserProfileException e, HttpServletResponse htr) {
		LOG.error("Exception : ", e);
		ResponseDto output = new ResponseDto();
		output.setStatusCode(e.getStatusCode());
		output.setStatusMessage(e.getDescription());
		return output;
	}
}
