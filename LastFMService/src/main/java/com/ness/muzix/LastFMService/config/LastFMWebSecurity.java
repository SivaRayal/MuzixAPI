package com.ness.muzix.LastFMService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

@Configuration
@EnableWebSecurity
public class LastFMWebSecurity{

    @Autowired
    MuzixAppConfig appConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf(csrf -> csrf.disable())
                .authorizeHttpRequests()
                .requestMatchers("/lastfm/**")
                .access((authentication, context) ->
                    new AuthorizationDecision(new IpAddressMatcher(appConfig.getAccessIP()).matches(context.getRequest())));
        return http.build();
    }
}
