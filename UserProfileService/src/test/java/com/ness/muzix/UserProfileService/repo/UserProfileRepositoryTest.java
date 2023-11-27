package com.ness.muzix.UserProfileService.repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ness.muzix.UserProfileService.entity.UserProfile;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserProfileRepositoryTest {
	
	@Autowired
	private UserProfileRepository repository;
	
    @Mock
    PasswordEncoder encoder;
	
	@BeforeEach
	public void setup() {
		UserProfile userProfile = new UserProfile();
		userProfile.setContactNumber("3456789009");
		userProfile.setFirstName("kiran");
		userProfile.setLastName("A");
		userProfile.setUserEmail("testing1@gmail.com");
		userProfile.setPassword("kia@123");
		repository.save(userProfile);
	}

	@Test
	public void testUpdateUserProfile() {
		int userProfile =  repository.updateUserProfile("testing@gmail.com,", encoder.encode("kia@1234"));
		assertTrue(userProfile==0);
	}

}
