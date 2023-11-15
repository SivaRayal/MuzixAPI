package com.ness.muzix.LastFMService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Muzix LastFM API", version = "1.0", description = " Muzix app integration with LastFM website"))
public class LastFmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LastFmServiceApplication.class, args);
	}

}
