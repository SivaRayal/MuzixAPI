package com.ness.muzix.gateway;

import com.ness.muzix.gateway.config.MuzixAppAuthFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
// @EnableZuulProxy
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "MUZIX API Gateway", version = "1.0", description = "Documentation of MUZIX API Gateway"))
public class GatewayApplication {

	@Autowired
	MuzixAppAuthFilter muzixAuthFilter;
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator apiRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r -> r.path("/auth/api-docs").and().method(HttpMethod.GET).uri("lb://auth-service"))
				.route("whishlist_route",route->route.path("/wishlist/**").filters(f->f.filter(muzixAuthFilter)).uri("lb://whishlist-service"))
				.route("lastFMService_route",route->route.path("/lastfm/**").filters(f->f.filter(muzixAuthFilter)).uri("lb://lastfm-service"))
				.route("userprofile_route", route->route.path("/userProfile/register").uri("lb://userprofile-service"))
				.route("userprofile_route", route->route.path("/userProfile/**").filters(f->f.filter(muzixAuthFilter)).uri("lb://userprofile-service"))
				.route("auth_route",route->route.path("/auth/**").uri("lb://auth-service"))
				.build();
	}

}
