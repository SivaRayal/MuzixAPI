package com.ness.muzix.UserProfileService.controller;

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
	public ResponseEntity<UserProfileResponse> getUserProfile(@RequestHeader("email") String email) throws UserProfileException {
		log.info("Email from header - "+email);
		return new ResponseEntity<UserProfileResponse>(userProfileService.getUserProfile(email),
				HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerProfile(@RequestBody UserProfileResponse userProfile) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.addUserProfile(userProfile),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/updateUserProfile")
	public ResponseEntity<String> loginProfile(@RequestBody UserProfileResponse userProfile) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.updateProfile(userProfile),HttpStatus.OK);
	}
	
	@PutMapping("/changePassword")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public ResponseEntity<String> changePassword(@RequestBody UserProfileDto user) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.changePassword(user.getUserEmail(),user.getOldPassword(),user.getNewPassword()),
				HttpStatus.RESET_CONTENT);
	}
	
	@PutMapping("/forgetPassword")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public ResponseEntity<String> forgetPassword(@RequestBody UserProfileDto user) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.forgetPassword(user.getUserEmail(),user.getNewPassword()),
				HttpStatus.RESET_CONTENT);
	}

	

}
