package com.ness.muzix.gateway.config;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class MuzixAppAuthFilter extends BasicAuthenticationFilter {

    Environment environment;

    public MuzixAppAuthFilter(AuthenticationManager authManager, Environment environement ) {
        super(authManager);
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = req.getHeader( "Authorization" );
        if(authorizationHeader == null || authorizationHeader.startsWith( "Bearer" )) {
            chain.doFilter(req,res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req,res);
    }

    protected UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String authorizationHeader = req.getHeader("Authorization");
        if(authorizationHeader == null ) {
            return null;
        }

        String token = authorizationHeader.replace("Bearer" , "");
        String userID = Jwts.parser()
                .setSigningKey("team05") //  authservice / resources / applicaion.properties
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        if(userID == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userID, null, new ArrayList<>());
    }

}
