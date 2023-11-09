package com.ness.muzix.UserProfileService.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ness.muzix.UserProfileService.model.UserProfile;

import jakarta.transaction.Transactional;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

	UserProfile findByUserEmail(String userEmail);

	UserProfile findByUserEmailAndPassword(String userEmail, String password);

	@Transactional
	@Modifying
	@Query("update UserProfile set password=?2 where userEmail=?1")
	int updateUserProfile(String userEmail, String password);

}
