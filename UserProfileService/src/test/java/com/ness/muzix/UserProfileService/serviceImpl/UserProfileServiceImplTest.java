package com.ness.muzix.UserProfileService.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ness.muzix.UserProfileService.entity.UserProfile;
import com.ness.muzix.UserProfileService.exception.UserProfileException;
import com.ness.muzix.UserProfileService.model.UserProfileResponse;
import com.ness.muzix.UserProfileService.repo.UserProfileRepository;
import com.ness.muzix.UserProfileService.utils.UserProfileDTOMapper;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceImplTest {

	@Mock
	private UserProfileRepository repository;

	@InjectMocks
	private UserProfileServiceImpl service;

	@Mock
	PasswordEncoder encoder;

	@Mock
	ModelMapper modelMapper;
	
	@Mock
	UserProfileDTOMapper userProfileMapper;

	
	Optional<UserProfile> exist = null;
	Optional<UserProfile> empty = Optional.empty();
	Optional<UserProfile> userExist = Optional.of(new UserProfile());
	Optional<UserProfile> exists = Optional.of(new UserProfile());
	UserProfileResponse userProfileResponse = new UserProfileResponse();
	UserProfileResponse newProfile = new UserProfileResponse();
	UserProfile userProfile = new UserProfile();
	String string1 = "Profile saved successfully";

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
	

	}

	@Test
	public void testAddUserProfile() {
		
		//mock the behaviour
		when(repository.findById(userProfile.getUserEmail())).thenReturn(empty);
		assertThrows(UserProfileException.class,()->service.getUserProfile(userProfile.getUserEmail()));
		when(modelMapper.map(userProfileResponse, UserProfile.class)).thenReturn(userProfile);
		when(repository.save(userProfile)).thenReturn(userProfile);
		
		assertThrows(UserProfileException.class,()->service.getUserProfile(userProfile.getUserEmail()));
		
		String str = service.addUserProfile(userProfileResponse);
		
		assertAll(() -> {
			assertNotNull(userProfile);
		}, () -> {
			assertNotNull(string1);
		}, () -> {
			assertEquals(string1, "Profile saved successfully");
		});
	}
	
	@Test
	public void testAddUserProfileWhenUserExists() {
		//mock the behaviour
		when(repository.findById(userProfile.getUserEmail())).thenReturn(userExist);
		UserProfileException exc = Assertions.assertThrows(UserProfileException.class, ()->service.addUserProfile(userProfileResponse));
	}
	
	@Test
	public void testAddUserProfileWhenAddProfile() {
		//mock the behaviour
		when(repository.findById(userProfile.getUserEmail())).thenReturn(empty);
		when(modelMapper.map(userProfileResponse, UserProfile.class)).thenReturn(userProfile);
		when(repository.save(userProfile)).thenReturn(null);
		UserProfileException exc = Assertions.assertThrows(UserProfileException.class, ()->service.addUserProfile(userProfileResponse));
		
	}
	
	@Test
	public void testGetUserProfile() {
		when(repository.findById(userProfile.getUserEmail())).thenReturn(exists);
		when(modelMapper.map(exists, UserProfileResponse.class)).thenReturn(userProfileResponse);
		//assertThrows(UserProfileException.class,()->service.getUserProfile(userProfile.getUserEmail()));
		service.getUserProfile(userProfile.getUserEmail());
		assertNotNull(userProfileResponse);
	}

	@Test
	void testUpdateProfile() {
		when(repository.findById(userProfile.getUserEmail())).thenReturn(exists);
		when(userProfileMapper.getUpdatedEntityFromDTO(exists.get(), newProfile)).thenReturn(userProfile);
		when(repository.save(userProfile)).thenReturn(userProfile);
		assertThrows(UserProfileException.class,()->service.getUserProfile(userProfile.getUserEmail()));
		
		service.updateProfile(newProfile);
		assertAll(() -> {
			assertNotNull(newProfile);
		}, () -> {
			assertNotNull(userProfile);
		}, () -> {
			assertEquals(newProfile.getUserEmail(), userProfile.getUserEmail());
		});
		
	}
	
	@Test
	public void testUpdateProfileWhenUserExists() {
		//mock the behaviour
		when(repository.findById(null)).thenReturn(empty);
		UserProfileException exc = Assertions.assertThrows(UserProfileException.class, ()->service.updateProfile(userProfileResponse));
	}
	
	@Test
	public void testUpdateProfileWhenUpdateProfile() {
		//mock the behaviour
		when(repository.findById(userProfile.getUserEmail())).thenReturn(empty);
		UserProfileException exc = Assertions.assertThrows(UserProfileException.class, ()->service.updateProfile(userProfileResponse));
		
	}
	
	@Test
	void testChangePassword() {
		//Mock calls
		when(repository.findById("test@gmail.com")).thenReturn(exists);
		when(encoder.matches("qw@123",exists.get().getPassword())).thenReturn(true);
		
		service.changePassword("test@gmail.com", "qw@123", "test@1234");
		
		assertAll(() -> {
			assertNotNull(newProfile);
		}, () -> {
			assertNotNull(exists);
		});
	}
	
	@Test
	void testChangePasswordwithUserNotFoundException() {
		//Mock calls
		when(repository.findById("test@gmail.com")).thenReturn(empty);
		UserProfileException exc = Assertions.assertThrows(UserProfileException.class, ()->service.changePassword("test@gmail.com", "test@123", "test@1234"));
		
	}
	
	@Test
	void testChangePasswordwithWrongOldPasswordException() {
		//Mock calls
		when(repository.findById("qw@gmail.com")).thenReturn(exists);
		UserProfileException exc = Assertions.assertThrows(UserProfileException.class, ()->service.changePassword("qw@gmail.com", "qw@123", "test@1234"));
		
	}
	
	@Test
	void testForgetPassword() {
		when(repository.findById("test@gmail.com")).thenReturn(exists);
		service.forgetPassword("test@gmail.com", "test@123");
		
		assertNotNull(newProfile);
		assertEquals(exists.get().getUserEmail(), newProfile.getUserEmail());
	}
	
	@Test
	void testForgetPasswordwithException() {
		//Mock calls
		when(repository.findById("test@gmail.com")).thenReturn(empty);
		UserProfileException exc = Assertions.assertThrows(UserProfileException.class, ()->service.forgetPassword("test@gmail.com", "test@123"));
		
	}
	
}
