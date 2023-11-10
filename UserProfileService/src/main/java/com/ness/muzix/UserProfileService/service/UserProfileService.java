package com.ness.muzix.UserProfileService.service;

import com.ness.muzix.UserProfileService.exception.UserProfileException;
import com.ness.muzix.UserProfileService.entity.UserProfile;
import com.ness.muzix.UserProfileService.model.UserProfileResponse;


public interface UserProfileService{
	
	public String addUserProfile(UserProfileResponse userProfile) throws UserProfileException;
	
	public UserProfileResponse getUserProfile(String token) throws UserProfileException;
	
	public String updateProfile(UserProfileResponse userProfile) throws UserProfileException;
	
	public String changePassword(String userEmail,String oldPassword, String newPassword) throws UserProfileException;
	
	public String forgetPassword(String userEmail, String password) throws UserProfileException;
	
}
