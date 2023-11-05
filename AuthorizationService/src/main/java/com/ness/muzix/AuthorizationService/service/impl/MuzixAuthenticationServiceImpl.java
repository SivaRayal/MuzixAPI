package com.ness.muzix.AuthorizationService.service.impl;

import java.util.Date;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MuzixAuthenticationServiceImpl implements MuzixAuthenticationService {


    @Autowired
	ModelMapper modelMapper;

    @Autowired
    UserDetailsRepo userRepo;
    
    @Autowired
    MuzixAppConfigs appConfig;

    @Override
    public UserCredentails getUserByEmail(String email) {
        log.info("User credentails service start");
        Optional<UserCredentailsDTO> userCredentailsDTO =userRepo.findById(email);
        UserCredentails userCredits=null;
        if(userCredentailsDTO.isEmpty()){
            log.info("User not found");
            throw new AuthorizationException("User not found. Register as a new user");
        }else{
            log.info("User credentails mapping back");
            userCredits=modelMapper.map(userCredentailsDTO,UserCredentails.class);
        }
        log.info("User credentails service start");
        return userCredits;
    }

    @Override
    public String authSuccess(String email) {
        log.info("authSuccess start");
        String token=Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(appConfig.getTokenExpTime())))
                .signWith(SignatureAlgorithm.HS512,appConfig.getTokenSecret())
                .setIssuedAt(new Date())
                .compact();
        return token;
    }
    
}
