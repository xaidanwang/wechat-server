package com.github.aidan.java8.test.run;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SynchronizedStaticAndNormalExample {

	public synchronized void fun1() {
		log.info("lock this fun1");
		fun2();
		log.info("exit fun1");
	}

	public synchronized static void fun2() {
		log.info("lock class  fun2");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		log.info("exit fun2");
	}

	public static void main(String[] args) throws InterruptedException {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		String pid = name.split("@")[0];
		log.info("Pid is:" + pid);
		log.info("jstack " + pid);
		SynchronizedStaticAndNormalExample example = new SynchronizedStaticAndNormalExample();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(() -> {
			fun2();
		});
		TimeUnit.SECONDS.sleep(5);
		executorService.submit(() -> {
			example.fun1();
		});
		executorService.shutdown();
	}

}
