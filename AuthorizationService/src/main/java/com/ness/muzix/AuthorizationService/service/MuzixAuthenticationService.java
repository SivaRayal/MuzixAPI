package com.ness.muzix.AuthorizationService.service;

import com.ness.muzix.AuthorizationService.model.UserProfileDto;

public interface MuzixAuthenticationService {
	UserProfileDto getUserByEmail(String email);
    String authSuccess(String email);
}
