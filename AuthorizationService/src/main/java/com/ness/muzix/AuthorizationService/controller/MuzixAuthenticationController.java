package com.ness.muzix.AuthorizationService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ness.muzix.AuthorizationService.exception.AuthorizationException;
import com.ness.muzix.AuthorizationService.model.UserCredentails;
import com.ness.muzix.AuthorizationService.service.MuzixAuthenticationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class MuzixAuthenticationController {
	private static final Logger log = LoggerFactory.getLogger(MuzixAuthenticationController.class);
	
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MuzixAuthenticationService authService;
    
    @GetMapping("/validate")
    public ResponseEntity<?> authenticate(@Validated @RequestBody UserCredentails userCredits){
        UserCredentails existingUser = authService.getUserByEmail(userCredits.getUserEmail());
        String token="";
        if(existingUser!=null){
            if( encoder.matches(userCredits.getPassword(), existingUser.getPassword())) {
                log.info("Started validating username pws");
                token = authService.authSuccess(userCredits.getUserEmail());
            }else{
                throw new AuthorizationException("Invalid Password. Kindly retry with valid password or rest your password");
            }
        }else{
            throw new AuthorizationException("User not found. Kindly register for new user login");
        }
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
