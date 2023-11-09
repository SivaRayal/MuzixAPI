package com.ness.muzix.AuthorizationService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ness.muzix.AuthorizationService.model.UserProfile;

@Repository
@Transactional
public interface UserDetailsRepo extends JpaRepository<UserProfile,String>{

}
