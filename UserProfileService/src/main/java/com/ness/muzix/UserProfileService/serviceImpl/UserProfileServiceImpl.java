package com.ness.muzix.UserProfileService.serviceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ness.muzix.UserProfileService.exception.UserProfileException;
import com.ness.muzix.UserProfileService.model.UserProfile;
import com.ness.muzix.UserProfileService.repo.UserProfileRepository;
import com.ness.muzix.UserProfileService.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceImpl.class);

	@Autowired
	private UserProfileRepository userProfileRepository;


	public String addUserProfile(UserProfile userProfile) throws UserProfileException {
		UserProfile response = userProfileRepository.save(userProfile);
		if(response==null) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}

		return "Profile saved successfully";
	}

	public UserProfile getUserProfileByUsername(String username) throws UserProfileException {
		UserProfile userProfile = null;
		try {
			userProfile = userProfileRepository.findByUserName(username);
		} catch (Exception e) {
			throw new UserProfileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		if (userProfile == null) {
			throw new UserProfileException(HttpStatus.BAD_REQUEST.value(), "User Not found");
		}
		return userProfile;

	}

	public UserProfile loginProfile(String username, String password) throws UserProfileException {
		UserProfile userProfile = null;
		try {
			userProfile = userProfileRepository.findByUserNameAndPassword(username, password);
		} catch (Exception e) {
			throw new UserProfileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		if (userProfile == null) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}
		return userProfile;

	}

	@Override
	public String changePassword(String username, String oldPassword, String newPassword) throws UserProfileException {
		// TODO Auto-generated method stub
		UserProfile userProfile = userProfileRepository.findByUserNameAndPassword(username, oldPassword);
		if (userProfile == null) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}
		if (userProfile != null) {
			userProfileRepository.updateUserProfile(userProfile.getUserId(), newPassword);
		}
		return "Password Updated Successfully";
	}

	@Override
	public String forgetPassword(String username, String password) throws UserProfileException {
		// TODO Auto-generated method stub
		UserProfile userProfile = userProfileRepository.findByUserName(username);
		if (userProfile == null) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}
		if (userProfile != null) {
			userProfileRepository.updateUserProfile(userProfile.getUserId(), password);
		}
		return "Password Updated Successfully";
	}
	
	
}
