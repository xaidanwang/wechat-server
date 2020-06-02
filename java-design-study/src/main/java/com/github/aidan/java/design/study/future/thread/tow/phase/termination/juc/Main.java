package com.github.aidan.java.design.study.future.thread.tow.phase.termination.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wang yi fei
 * @date 2020/5/21 16:53
 */
public class Main {

	private static final ThreadLocal<String> map = new ThreadLocal();
	private static final int TASKS = 10;
	private static final Random randow = new Random(214159);
	public static void main(String[] args) {
		System.out.println("BEGIN");
		ExecutorService service = Executors.newFixedThreadPool(5);
		CountDownLatch countDownLatch = new CountDownLatch(10);
		try {
			for (int i = 0; i < TASKS; i++) {
				service.execute(new MyTask(countDownLatch,i));
			}
			System.out.println("await");
			// 等待完成

			countDownLatch.await();
		}catch (InterruptedException e){
			map.set("1");
		}finally {
			service.shutdown();
			System.out.println("END");
		}
	}

	static class MyTask implements Runnable{
		private final CountDownLatch countDownLatch;
		private final int context;
		public MyTask(CountDownLatch countDownLatch, int context) {
			this.countDownLatch = countDownLatch;
			this.context = context;
		}

		@Override
		public void run() {
			doTask();
			countDownLatch.countDown();
		}

		protected void doTask(){
			String name = Thread.currentThread().getName();
			System.out.println(name + ":MyTask:BEGIN:context = " + context);
			try {
				Thread.sleep(randow.nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				System.out.println(name + ":MyTask:END:context = " + context);
			}
		}
	}
}
