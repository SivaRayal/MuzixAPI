package com.ness.muzix.UserProfileService.service;

import com.ness.muzix.UserProfileService.exception.UserProfileException;
import com.ness.muzix.UserProfileService.model.UserProfile;


public interface UserProfileService{
	
	public String addUserProfile(UserProfile userProfile) throws UserProfileException;
	
	public UserProfile getUserProfileByUserEmail(String userEmail) throws UserProfileException;
	
	public UserProfile loginProfile(String userEmail, String password) throws UserProfileException;
	
	public String changePassword(String userEmail,String oldPassword, String newPassword) throws UserProfileException;
	
	public String forgetPassword(String userEmail, String password) throws UserProfileException;
	
}
