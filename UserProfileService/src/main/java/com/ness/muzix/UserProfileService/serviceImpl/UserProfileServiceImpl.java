package com.ness.muzix.UserProfileService.serviceImpl;
import com.ness.muzix.UserProfileService.model.UserProfileResponse;
import com.ness.muzix.UserProfileService.service.AuthorizationServiceClient;
import com.ness.muzix.UserProfileService.utils.UserProfileDTOMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ness.muzix.UserProfileService.exception.UserProfileException;
import com.ness.muzix.UserProfileService.entity.UserProfile;
import com.ness.muzix.UserProfileService.repo.UserProfileRepository;
import com.ness.muzix.UserProfileService.service.UserProfileService;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceImpl.class);

	@Autowired
	private UserProfileRepository userProfileRepository;
	
    @Autowired
    PasswordEncoder encoder;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserProfileDTOMapper userProfileMapper;

	public String addUserProfile(UserProfileResponse userProfile) throws UserProfileException {
		
		if(userProfile.getPassword()!=null && !(userProfile.getPassword().isEmpty())) {
			userProfile.setPassword(encoder.encode(userProfile.getPassword()));
		}
		Optional<UserProfile> exists =  userProfileRepository.findById(userProfile.getUserEmail());
		if(!exists.isEmpty()) {
			throw new UserProfileException(HttpStatus.NOT_ACCEPTABLE.value(), "User already registered");
		}
		try {
			UserProfile newUser=modelMapper.map(userProfile,UserProfile.class);
//			newUser.setPassword(encoder.encode(newUser.getPassword()));
			UserProfile response = userProfileRepository.save(newUser);
			LOG.info("UserProfileServiceImpl.register API Resposne -> "+ response.toString());
		}catch (Exception e) {
			throw new UserProfileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return "Profile saved successfully";
	}

	public UserProfileResponse getUserProfile(String email) throws UserProfileException {
		UserProfileResponse resp = null;
		try {
			resp=modelMapper.map(userProfileRepository.findById(email),UserProfileResponse.class);
		} catch (Exception e) {
			throw new UserProfileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		if (resp == null) {
			throw new UserProfileException(HttpStatus.BAD_REQUEST.value(), "User Not found");
		}
		return resp;

	}

	public String updateProfile(UserProfileResponse newProfile) throws UserProfileException {
		Optional<UserProfile> existingProfile = null;
		try {
			existingProfile = userProfileRepository.findById(newProfile.getUserEmail());
		} catch (Exception e) {
			throw new UserProfileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		if (existingProfile.isEmpty()) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found. Kindly Register as new user");
		}else{
			UserProfile updatedProfile=userProfileMapper.getUpdatedEntityFromDTO(existingProfile.get(),newProfile);
			userProfileRepository.save(updatedProfile);
		}
		return "Profile updated successfully";
	}

	@Override
	public String changePassword(String userEmail, String oldPassword, String newPassword) throws UserProfileException {
		// TODO Auto-generated method stub
		Optional<UserProfile> userProfile = userProfileRepository.findById(userEmail);
		if (userProfile.isEmpty()) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}
		else{
			if (encoder.matches(oldPassword, userProfile.get().getPassword())) {
				userProfileRepository.updateUserProfile(userProfile.get().getUserEmail(), encoder.encode(newPassword));
			} else {
				throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "Enter the Correct Old Password");
			}
		}
		return "Password Updated Successfully";
	}

	@Override
	public String forgetPassword(String userEmail, String password) throws UserProfileException {
		// TODO Auto-generated method stub
		Optional<UserProfile> userProfile = userProfileRepository.findById(userEmail);
		if (userProfile.isEmpty()) {
			throw new UserProfileException(HttpStatus.NOT_FOUND.value(), "User Not found");
		}
		else{
			userProfileRepository.updateUserProfile(userProfile.get().getUserEmail(), encoder.encode(password));
		}
		return "Password Updated Successfully";
	}
	
	
}
