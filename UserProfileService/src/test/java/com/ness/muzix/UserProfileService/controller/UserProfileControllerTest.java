package com.ness.muzix.UserProfileService.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ness.muzix.UserProfileService.entity.UserProfile;
import com.ness.muzix.UserProfileService.model.UserProfileDto;
import com.ness.muzix.UserProfileService.model.UserProfileResponse;
import com.ness.muzix.UserProfileService.service.UserProfileService;

@ExtendWith(MockitoExtension.class)
class UserProfileControllerTest {

	@Mock
	private UserProfileService userProfileService;
	
	@InjectMocks
	private UserProfileController controller;
	
	Optional<UserProfile> exist = null;
	Optional<UserProfile> exists = Optional.of(new UserProfile());
	UserProfileResponse userProfileResponse = new UserProfileResponse();
	UserProfileResponse newProfile = new UserProfileResponse();
	UserProfile userProfile = new UserProfile();
	UserProfileDto userDto = new UserProfileDto();
	
	//UserProfileResponse responseEntity = new ResponseEntity<UserProfileResponse>(userProfileResponse,HttpStatus.OK);
	
	String out = "Profile saved successfully";

	@BeforeEach
	public void setup() {

		userProfileResponse.setContactNumber("3456789009");
		userProfileResponse.setFirstName("tom");
		userProfileResponse.setLastName("stokes");
		userProfileResponse.setUserEmail("qw@gmail.com");
		userProfileResponse.setPassword("qw@123");

		userProfile.setContactNumber("3456789009");
		userProfile.setFirstName("tom");
		userProfile.setLastName("stokes");
		userProfile.setUserEmail("qw@gmail.com");
		userProfile.setPassword("qw@123");
		
		newProfile.setContactNumber("3456789009");
		newProfile.setFirstName("tom2");
		newProfile.setLastName("stokes1");
		newProfile.setUserEmail("qw@gmail.com");
		newProfile.setPassword("qw@123");
		
		exists.get().setContactNumber("3456789009");
		exists.get().setFirstName("tom2");
		exists.get().setLastName("stokes1");
		exists.get().setUserEmail("qw@gmail.com");
		exists.get().setPassword("qw@123");
		
		userDto.setNewPassword("test@123");
		userDto.setOldPassword("test@12");
		userDto.setUserEmail("test@gmail.com");

	}
	
	@Test
	void testGetUserProfile() {
		when(userProfileService.getUserProfile("qw@gmail.com")).thenReturn(userProfileResponse);
		controller.getUserProfile("qw@gmail.com");
		
		assertNotNull(userProfile);
	}

	@Test
	void testRegisterProfile() {
		when(userProfileService.addUserProfile(userProfileResponse)).thenReturn("Profile saved successfully");
		controller.registerProfile(userProfileResponse);
		
		assertNotNull(userProfileResponse);
		
	}

	@Test
	void testChangePassword() {
		//when(userProfileService.changePassword("test@gmail.com","test@123","test1@123")).thenReturn("Password Updated Successfully");
		controller.changePassword(userDto);
		
		assertNotNull(userDto);
	}

	@Test
	void testForgetPassword() {
		//when(userProfileService.forgetPassword("test@gmail.com","test@124")).thenReturn("Password Updated Successfully");
		controller.forgetPassword(userDto);
		
		assertNotNull(userDto);
	}
	
	@Test
	void testUpdateUserProfile() {
		controller.updateUserProfile(userProfileResponse);
		assertNotNull(userProfileResponse);
	}

}
