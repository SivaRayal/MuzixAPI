package com.ness.muzix.UserProfileService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="auth-service")
public interface AuthorizationServiceClient {
    @GetMapping("/getUserFromToken/${token}")
    public String getUserEmail(@PathVariable String token);
}
