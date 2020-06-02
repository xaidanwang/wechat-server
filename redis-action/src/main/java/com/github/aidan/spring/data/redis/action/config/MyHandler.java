package com.github.aidan.spring.data.redis.action.config;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

/**
 * @author wang yi fei
 * @date 2019/8/17 16:41
 */
public class MyHandler implements HttpHandler {

	private String name;
	private int age;

	public MyHandler(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String response = "Welcome Real's HowTo test page" + name + " " + age;
		httpExchange.getRequestBody().available();
		httpExchange.sendResponseHeaders(200, response.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();

	}
}
