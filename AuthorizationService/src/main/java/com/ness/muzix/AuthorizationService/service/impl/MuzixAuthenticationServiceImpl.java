package com.ness.muzix.AuthorizationService.service.impl;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.muzix.AuthorizationService.config.MuzixAppConfigs;
import com.ness.muzix.AuthorizationService.exception.AuthorizationException;
import com.ness.muzix.AuthorizationService.model.UserCredentails;
import com.ness.muzix.AuthorizationService.model.UserCredentailsDTO;
import com.ness.muzix.AuthorizationService.repo.UserDetailsRepo;
import com.ness.muzix.AuthorizationService.service.MuzixAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("MuzixAuthenticationService")
public class MuzixAuthenticationServiceImpl implements MuzixAuthenticationService {


    @Autowired
	ModelMapper modelMapper;

    @Autowired
    UserDetailsRepo userRepo;
    
    @Autowired
    MuzixAppConfigs appConfig;

    @Override
    public UserCredentails getUserByEmail(String email) {
        Optional<UserCredentailsDTO> userCredentailsDTO =userRepo.findById(email);
        UserCredentails userCredits=null;
        if(userCredentailsDTO.isEmpty()){
            throw new AuthorizationException("User not found. Register as a new user");
        }else{
            userCredits=modelMapper.map(userCredentailsDTO,UserCredentails.class);
        }
        return userCredits;
    }

    @Override
    public String authSuccess(String email) {
        String token=Jwts.builder()
            .setSubject(email)
            .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(appConfig.getTokenExpTime())))
            .signWith(SignatureAlgorithm.HS512,appConfig.getTokenSecret())
            .compact();
        return token;
    }
    
}
