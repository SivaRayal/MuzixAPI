package com.ness.muzix.AuthorizationService.controller;


import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ness.muzix.AuthorizationService.exception.AuthorizationException;
import com.ness.muzix.AuthorizationService.model.UserProfileDto;
import com.ness.muzix.AuthorizationService.service.MuzixAuthenticationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/auth")
@Slf4j
public class MuzixAuthenticationController {
	
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MuzixAuthenticationService authService;

    @Operation(summary = "Muzix Authorization Service", description = "Validates user email and password and return JWT token for sucessfull validations")
    @PostMapping(path="/validate", consumes= {MediaType.APPLICATION_JSON_VALUE})
//    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema=@Schema(implementation = UserProfileDto.class)))
    public ResponseEntity<String> authenticate(@RequestBody UserProfileDto userCredits){
        UserProfileDto existingUser = authService.getUserByEmail(userCredits.getUserEmail());
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
        return new ResponseEntity<String>(token,HttpStatus.OK);
    }
    
}
