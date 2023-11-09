package com.ness.muzix.UserProfileService.serviceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
    @Autowired
    PasswordEncoder encoder;
	

	public String addUserProfile(UserProfile userProfile) throws UserProfileException {
		
		if(userProfile.getPassword()!=null && !(userProfile.getPassword().isEmpty())) {
			userProfile.setPassword(encoder.encode(userProfile.getPassword()));
		}
		UserProfile exists =  userProfileRepository.findByUserEmail(userProfile.getUserEmail());
		if(exists!=null) {
			throw new UserProfileException(HttpStatus.NOT_ACCEPTABLE.value(), "User already registered");
		}
		try {
			UserProfile response = userProfileRepository.save(userProfile);
			LOG.info("UserProfileServiceImpl.register API Resposne -> "+ response.toString());
		}catch (Exception e) {
			throw new UserProfileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return "Profile saved successfully";
	}

	public UserProfile getUserProfileByUserEmail(String userEmail) throws UserProfileException {
		UserProfile userProfile = null;
		try {
			userProfile = userProfileRepository.findByUserEmail(userEmail);
		} catch (Exception e) {
			throw new UserProfileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		if (userProfile == null) {
			throw new UserProfileException(HttpStatus.BAD_REQUEST.value(), "User Not found");
		}
		return userProfile;

	}

	public UserProfile loginProfile(String userEmail, String password) throws UserProfileException {
		UserProfile userProfile = null;
		try {
			userProfile = userProfileRepository.findByUserEmailAndPassword(userEmail, password);
		} catch (Exception e) {
			throw new UserProfileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		if (userProfile == null) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}
		return userProfile;
	}

	@Override
	public String changePassword(String userEmail, String oldPassword, String newPassword) throws UserProfileException {
		// TODO Auto-generated method stub
		UserProfile userProfile = userProfileRepository.findByUserEmail(userEmail);
		if (userProfile == null) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}
		if (userProfile != null) {
			if (encoder.matches(oldPassword, userProfile.getPassword())) {
				userProfileRepository.updateUserProfile(userProfile.getUserEmail(), encoder.encode(newPassword));
			} else {
				throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "Enter the Correct Old Password");
			}
		}
		return "Password Updated Successfully";
	}

	@Override
	public String forgetPassword(String userEmail, String password) throws UserProfileException {
		// TODO Auto-generated method stub
		UserProfile userProfile = userProfileRepository.findByUserEmail(userEmail);
		if (userProfile == null) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}
		if (userProfile != null) {
			userProfileRepository.updateUserProfile(userProfile.getUserEmail(), encoder.encode(password));
		}
		return "Password Updated Successfully";
	}
	
	
}
