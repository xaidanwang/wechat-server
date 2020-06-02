package com.github.aidan.spring.boot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2Application.class, args);
	}

}
