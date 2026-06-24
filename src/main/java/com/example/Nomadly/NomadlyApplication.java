package com.example.Nomadly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaServer
@EnableFeignClients
public class NomadlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NomadlyApplication.class, args);
	}

}
