package com.ness.muzix.AuthorizationService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MuzixAppConfigs {
    @Value("token.expiration.time")
    private String tokenExpTime;
    @Value("token.secret")
    private String tokenSecret;
}
