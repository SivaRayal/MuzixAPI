package com.ness.muzix.UserProfileService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ness.muzix.UserProfileService.dto.UserProfileDto;
import com.ness.muzix.UserProfileService.exception.UserProfileException;
import com.ness.muzix.UserProfileService.model.UserProfile;
import com.ness.muzix.UserProfileService.service.UserProfileService;

@RestController
@RequestMapping("/userProfile")
public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfileService;
	
	@GetMapping("/user/{userEmail}")
	public ResponseEntity<UserProfile> getUserProfileByUsername(@PathVariable String userEmail) throws UserProfileException {
		return new ResponseEntity<UserProfile>(userProfileService.getUserProfileByUserEmail(userEmail),
				HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerProfile(@RequestBody UserProfile userProfile) throws UserProfileException {
		return new ResponseEntity<String>(userProfileService.addUserProfile(userProfile),
				HttpStatus.CREATED);
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<UserProfile> loginProfile(@RequestParam(required = true) String  username, @RequestParam(required = true) String password) throws UserProfileException {
//		return new ResponseEntity<UserProfile>(userProfileService.loginProfile(username,password),
//				HttpStatus.OK);
//	}
	
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
