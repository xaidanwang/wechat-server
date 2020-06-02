package com.github.aidan.spring.data.redis.action;

import com.github.aidan.spring.data.redis.action.service.SimpleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataRedisActionApplication {

	public static void main(String[] args) {
	/*	ApplicationContext ctx = SpringApplication.run(SpringDataRedisActionApplication.class, args);
		SimpleService simpleService = ctx.getBean(SimpleService.class) ;
		simpleService.run();*/
		List<String> strings = new ArrayList<>();


		if (!strings.isEmpty()){
			strings.get(0);
		}

		System.out.println();
	}

}
