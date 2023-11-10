package com.ness.muzix.UserProfileService.model;

import lombok.Data;

@Data
public class UserProfileDto {
	
	private String userEmail;
	private String oldPassword;
	private String newPassword;
}
