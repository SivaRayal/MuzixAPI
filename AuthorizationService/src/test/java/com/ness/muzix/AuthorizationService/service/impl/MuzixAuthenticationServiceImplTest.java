package com.ness.muzix.AuthorizationService.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.google.common.base.Optional;
import com.ness.muzix.AuthorizationService.config.MuzixAppConfigs;
import com.ness.muzix.AuthorizationService.model.UserProfile;
import com.ness.muzix.AuthorizationService.model.UserProfileDto;
import com.ness.muzix.AuthorizationService.repo.UserDetailsRepo;

@ExtendWith(MockitoExtension.class)
class MuzixAuthenticationServiceImplTest {

	@Mock
	ModelMapper modelMapper;
 
    @Mock
    UserDetailsRepo userRepo;
    @Mock
    MuzixAppConfigs appConfig;

    @InjectMocks
    private MuzixAuthenticationServiceImpl service;
    String email = "test@gmail.com";
    UserProfileDto userCredits=new UserProfileDto();
    Optional<UserProfile> userProfile = Optional.of(new UserProfile());
    Optional<UserProfile> userProfile1 = null;
    @BeforeEach
    public void setup() {
    	userProfile.get().setContactNumber("3456789009");
		userProfile.get().setFirstName("tom");
		userProfile.get().setLastName("stokes");
		userProfile.get().setUserEmail("test2@gmail.com");
		userProfile.get().setPassword("tom@123");
		userCredits.setContactNumber("3456789009");
		userCredits.setFirstName("tom");
		userCredits.setLastName("stokes");
		userCredits.setUserEmail("test2@gmail.com");
		userCredits.setPassword("tom@123");
    }
 
	@Test
	void testGetUserByEmail() {
		//Mock Behaviour
		when(userRepo.findById(email)).thenReturn(userProfile);
		//assertThrows(AuthorizationException.class,()->service.authSuccess(email));
		when(modelMapper.map(userProfile, UserProfileDto.class)).thenReturn(userCredits);
		service.getUserByEmail(email);
		assertNotNull(userCredits);
	}
}
