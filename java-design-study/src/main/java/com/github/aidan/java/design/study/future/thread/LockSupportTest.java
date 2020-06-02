package com.github.aidan.java.design.study.future.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wang yi fei
 * @date 2020/5/18 14:07
 */
public class LockSupportTest {

	public static class MyThread extends Thread{
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(getName() + " 进入线程");
			LockSupport.park();
			System.out.println("运行结束");
			System.out.println("是否中断：" + Thread.currentThread().isInterrupted());
		}
	}

	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.start();
		System.out.println("myThread线程已经启动了，但是在内部LockSupport进行了park");
		LockSupport.unpark(myThread);
//		myThread.interrupt();
		System.out.println("main线程结束");
	}
}
