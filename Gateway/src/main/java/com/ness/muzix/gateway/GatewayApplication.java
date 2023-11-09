package com.ness.muzix.gateway;

import com.ness.muzix.gateway.config.MuzixAppAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	MuzixAppAuthFilter muzixAuthFilter;
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator apiRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route("whishlist_route",route->route.path("/wishlist/**").filters(f->f.filter(muzixAuthFilter)).uri("lb://whishlist-service"))
				.route("lastFMService_route",route->route.path("/lastfm/**").filters(f->f.filter(muzixAuthFilter)).uri("lb://lastfm-service"))
				.route("userProfile_route", route->route.path("/userProfile/**").uri("lb://userprofile-service"))
				.route("auth_route",route->route.path("/auth/**").uri("lb://auth-service"))
				.build();
	}

}
