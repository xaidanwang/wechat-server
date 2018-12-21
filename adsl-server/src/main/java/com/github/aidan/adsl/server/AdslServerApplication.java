package com.github.aidan.adsl.server;

import com.github.aidan.adsl.server.socket.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdslServerApplication.class, args);

		SocketServer socketServer =new SocketServer(9000);
	//	SocketServer socketServer =new SocketServer(9001);
	//	SocketServer socketServer =new SocketServer(9002);
	//	SocketServer socketServer =new SocketServer(9003);
		socketServer.startSocket();
	}

}

