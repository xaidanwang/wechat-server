package com.github.aidan.java8.test.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author wang yi fei
 * @date 2019/4/23 15:49
 */
public class Driver {

	public void main(int n) {
		CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(n);

		for (int i = 0; i < n; ++i) // create and start threads
         new Thread(new Worker(startSignal, doneSignal)).start();
		//执行 doSomethingElse 方法
		doSomethingElse();
		// startSignal 的计数 减1
		startSignal.countDown();

		try {
			//等待 doneSignal 计数归零后再次执行 doSomethingElse() 方法
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		doSomethingElse();
	}

	private void doSomethingElse(){
		System.out.println("执行doSomethingElse 方法 ");
	}

	 class Worker implements Runnable {

		private final CountDownLatch startSignal;
		private final CountDownLatch doneSignal;

		Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
			this.startSignal = startSignal;
			this.doneSignal = doneSignal;
		}

		@Override
		public void run() {

			try {
				//等待 startSignal 执行完毕后执行doWork() 方法，然后等待doneSignal 计数减一
				startSignal.await();
				doWork();
				doneSignal.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		private void doWork(){
			System.out.println("执行 doWork 方法");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Driver driver = new Driver();
		driver.main(3);

	//	Thread.sleep(3000L);
	}
}
