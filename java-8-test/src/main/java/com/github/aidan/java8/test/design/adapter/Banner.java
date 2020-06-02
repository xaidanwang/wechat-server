package com.github.aidan.java8.test.design.adapter;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

/**
 * @author wang yi fei
 * @date 2019/5/11 10:00
 */
@Slf4j
public class Banner {
	private String string;

	public Banner(String string){
		this.string = string;
	}

	public void showWithParen(){
		System.out.println("("+ string + ")");
	}

	public void showWithAster(){
		System.out.println("*"+ string + "*");
	}

	public static void main(String[] args) throws InterruptedException {
/*		Print p = new PrintBanner("Hello");
		p.printWeak();
		p.printStrong();

		Print2 p2 = new PrintBanner2("Hello");
		p2.printWeak();
		p2.printStrong();*/
/*		Instant startTime = Instant.now();
		Thread.sleep(300L);
		log.info("服务调用,BuildingController.deleteInfrastructure,结束,耗时：{}ms", Instant.now().until(startTime, ChronoUnit.MILLIS));

		log.info("服务调用,BuildingController.deleteInfrastructure,结束,耗时：{}ms", startTime.until(Instant.now(), ChronoUnit.MILLIS));*/

		long startTime = System.currentTimeMillis();
		Thread.sleep(300L);
		log.info("服务调用,BuildingController.deleteInfrastructure,结束,耗时：{}ms", System.currentTimeMillis()-startTime);

	}
}
