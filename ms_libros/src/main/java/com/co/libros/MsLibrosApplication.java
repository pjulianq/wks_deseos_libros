package com.co.libros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsLibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsLibrosApplication.class, args);
	}

}
