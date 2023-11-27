package com.ness.muzix.AuthorizationService.controller;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ness.muzix.AuthorizationService.exception.AuthorizationException;
import com.ness.muzix.AuthorizationService.model.UserProfile;
import com.ness.muzix.AuthorizationService.model.UserProfileDto;
import com.ness.muzix.AuthorizationService.service.MuzixAuthenticationService;
@ExtendWith(MockitoExtension.class)
class MuzixAuthenticationControllerTest {

	@Mock
    PasswordEncoder encoder;

    @Mock
    MuzixAuthenticationService authService;
    
    @InjectMocks
    private MuzixAuthenticationController controller;
    
    
    String email = "test2@gmail.com";
    UserProfileDto userCredits=new UserProfileDto();
    UserProfileDto userCredits1=null;
    Optional<UserProfile> userProfile = Optional.of(new UserProfile());
    Optional<UserProfile> userProfile1 = Optional.empty();
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
	void testAuthenticate() {
		when(authService.getUserByEmail(email)).thenReturn(userCredits);
		when(encoder.matches("tom@123", "tom@123")).thenReturn(true);
		controller.authenticate(userCredits);
		}
	
	@Test
	void testAuthenticateWithAuthorizationException() {
		when(authService.getUserByEmail(email)).thenReturn(userCredits1);
		//when(encoder.matches("tom@123", "tom@123")).thenReturn(false);
		AuthorizationException exc = Assertions.assertThrows(AuthorizationException.class,()->controller.authenticate(userCredits));
		
	}
	
	@Test
	void testAuthenticateWhenUserNotAvailable() {
		when(authService.getUserByEmail(email)).thenReturn(userCredits);
		when(encoder.matches("tom@123", "tom@123")).thenReturn(false);
		AuthorizationException exc = Assertions.assertThrows(AuthorizationException.class,()->controller.authenticate(userCredits));
		
	}
	

}