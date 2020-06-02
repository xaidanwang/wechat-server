package com.github.aidan.java.design.study.future.thread.tow.phase.termination;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author wang yi fei
 * @date 2020/5/20 15:45
 */
public class CountupThread extends Thread{
	// 计数值
	private long counter = 0;

	// 发出终止请求后变为 true
	private volatile boolean shutdownRequested = false;

	// 终止请求
	public void shutdownRequested(){
		shutdownRequested = true;
		interrupt();
	}

	// 检查是否发出了终止请求
	public boolean isShutdownRequested(){
		return shutdownRequested;
	}

	// 线程体

	@Override
	public void run() {
		try {
			while (!isShutdownRequested()){
				doWork();
			}
		}catch (InterruptedException e){

		}finally {
			doShutdown();
		}
	}

	// 操作
	protected void doWork() throws InterruptedException {
		counter++;
		System.out.println("doWork: counter = " + counter);
		Thread.sleep(500);
	}
	// 终止处理
	protected void doShutdown(){
		System.out.println("doShutdown: counter = " + counter);
	}
}
