package com.ness.muzix.UserProfileService.service;

import com.ness.muzix.UserProfileService.exception.UserProfileException;
import com.ness.muzix.UserProfileService.model.UserProfile;


public interface UserProfileService{
	
	public String addUserProfile(UserProfile userProfile) throws UserProfileException;
	
	public UserProfile getUserProfileByUsername(String username) throws UserProfileException;
	
	public UserProfile loginProfile(String username, String password) throws UserProfileException;
	
	public String changePassword(String username,String oldPassword, String newPassword) throws UserProfileException;
	
	public String forgetPassword(String username, String password) throws UserProfileException;
	
}
