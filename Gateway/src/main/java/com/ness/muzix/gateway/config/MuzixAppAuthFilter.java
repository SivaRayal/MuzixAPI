package com.ness.muzix.gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class MuzixAppAuthFilter implements GatewayFilter {

    Environment environment;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if(!request.getHeaders().containsKey("Authorization")){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("Does not contain Authorization header");
            return response.setComplete();
        }

        String authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);

        if(!authHeader.startsWith("Bearer ")){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("Does not contain bearer");
            return response.setComplete();
        }

        String jwtToken = authHeader.substring(7);
        try {
            Claims claims = getClaims(jwtToken);
            String email = claims.getSubject();
            request.mutate().header("email",email);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("Does not contain valid token");
            return response.setComplete();
        }
        return chain.filter(exchange);
    }
    private Claims getClaims(String jwtToken) throws Exception {
        return Jwts.parser()
                .setSigningKey("team05")
                .parseClaimsJws(jwtToken)
                .getBody();
    }


}
