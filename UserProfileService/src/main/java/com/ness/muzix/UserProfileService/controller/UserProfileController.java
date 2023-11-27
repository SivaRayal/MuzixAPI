package com.ness.muzix.UserProfileService.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ness.muzix.UserProfileService.model.UserProfileDto;
import com.ness.muzix.UserProfileService.model.UserProfileResponse;
import com.ness.muzix.UserProfileService.exception.UserProfileException;
import com.ness.muzix.UserProfileService.entity.UserProfile;
import com.ness.muzix.UserProfileService.service.UserProfileService;

@RestController
@RequestMapping("/userProfile")
@Slf4j
public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfileService;


	@GetMapping("/getUserProfile")
	@Operation(summary = "Muzix Userprofile Service", description = "Retrieves user profile based on login authorization token.")
	public ResponseEntity<UserProfileResponse> getUserProfile(@RequestHeader("email") String email) throws UserProfileException {
		log.info("Email from header - "+email);
		return new ResponseEntity<UserProfileResponse>(userProfileService.getUserProfile(email),
				HttpStatus.OK);
	}

	@PostMapping("/register")
	@Operation(summary = "Muzix Userprofile Service", description = "Register new user in Muzix app DB.")
	public ResponseEntity<String> registerProfile(@RequestBody UserProfileResponse userProfile) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.addUserProfile(userProfile),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/updateUserProfile")
	@Operation(summary = "Muzix Userprofile Service", description = "Updates user profile in muzix app DB.")
	public ResponseEntity<String> updateUserProfile(@RequestBody UserProfileResponse userProfile) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.updateProfile(userProfile),HttpStatus.OK);
	}
	
	@PutMapping("/changePassword")
	@Operation(summary = "Muzix Userprofile Service", description = "Service for changing user password if previous password is known to user.")
	public ResponseEntity<String> changePassword(@RequestBody UserProfileDto user) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.changePassword(user.getUserEmail(),user.getOldPassword(),user.getNewPassword()),
				HttpStatus.OK);
	}
	
	@PutMapping("/forgetPassword")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	@Operation(summary = "Muzix Userprofile Service", description = "Service for updating password if previous password is unknown.")
	public ResponseEntity<String> forgetPassword(@RequestBody UserProfileDto user) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.forgetPassword(user.getUserEmail(),user.getNewPassword()),
				HttpStatus.RESET_CONTENT);
	}

	

}
