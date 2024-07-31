package com.laggo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LaggoGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaggoGatewayApplication.class, args);
	}

}
