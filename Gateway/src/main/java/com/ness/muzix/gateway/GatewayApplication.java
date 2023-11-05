package com.ness.muzix.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @EnableZuulProxy
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator apiRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route("whishlist_route",route->route.path("/wishlist/**").uri("lb://whishlist-service"))
				.route("lastFMService_route",route->route.path("/lastfm/**").uri("lb://lastfm-service"))
				.route("auth_route",route->route.path("/auth/**").uri("lb://auth-service"))
				.build();
	}

}
