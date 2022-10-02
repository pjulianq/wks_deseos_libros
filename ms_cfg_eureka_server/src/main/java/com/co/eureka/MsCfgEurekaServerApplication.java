package com.co.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsCfgEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCfgEurekaServerApplication.class, args);
	}

}
