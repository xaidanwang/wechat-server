package com.github.aidan.netty.spring.boot;

import com.github.aidan.netty.spring.boot.netty.DiscardServer;
import com.github.aidan.netty.spring.boot.netty.MasterServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class NettySpringBootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NettySpringBootApplication.class, args);
	}

	@Resource
	private DiscardServer discardServer;

	@Override
	public void run(String... args) throws Exception {
		discardServer.run(8080);
	}
}
