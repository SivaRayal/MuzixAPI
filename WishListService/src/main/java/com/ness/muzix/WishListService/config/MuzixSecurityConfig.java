package com.ness.muzix.WishListService.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

@Configuration
@EnableWebSecurity
public class MuzixSecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf(csrf -> csrf.disable())
        .authorizeHttpRequests()
        .requestMatchers("/wishlist/**")
        .access((authentication, context) ->
            new AuthorizationDecision(new IpAddressMatcher("127.0.0.1").matches(context.getRequest())));
        return http.build();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
