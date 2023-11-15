package com.ness.muzix.WishListService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Muzix Wishlist API", version = "1.0", description = " Muzix app favourites list management for app users"))
public class WishListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishListServiceApplication.class, args);
	}

}
