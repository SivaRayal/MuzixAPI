package com.ness.muzix.AuthorizationService.service;

import com.ness.muzix.AuthorizationService.model.UserCredentails;

public interface MuzixAuthenticationService {
    UserCredentails getUserByEmail(String email);
    String authSuccess(String email);
}
