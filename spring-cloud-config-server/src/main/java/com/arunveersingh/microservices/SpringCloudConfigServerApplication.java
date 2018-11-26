package com.arunveersingh.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
/**
 * Notes
 * http://localhost:8888/limits-service/qa
 * http://localhost:8888/limits-service/default
 * @author arunveersingh9@gmail.com
 *
 */
@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
	}
}
