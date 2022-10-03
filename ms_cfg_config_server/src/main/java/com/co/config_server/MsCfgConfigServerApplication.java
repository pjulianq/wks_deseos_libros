package com.co.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MsCfgConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCfgConfigServerApplication.class, args);
	}

}
