package com.ness.muzix.AuthorizationService.model;

import lombok.Data;

@Data
public class UserProfileDto {

	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String userEmail;
	
	private String contactNumber;

}
