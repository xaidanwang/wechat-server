package com.github.aidan.java.design.study.future.thread.tow.phase.termination.juc;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author wang yi fei
 * @date 2020/5/22 10:57
 */
public class Main2 {
	private static final int THREADS = 3;
	public static final Random random = new Random(314159);
	public static void main(String[] args) {
		System.out.println("BEGIN");
		// 由 ExecutorService 提供进行工作的线程
		ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

		// 屏障被解除时的操作
		Runnable barrierAction = new Runnable() {
			@Override
			public void run() {
				System.out.println("Barrier Action");
			}
		};

		// CyclicBarrier 用于使线程步调一致
		CyclicBarrier phaseBarrier = new CyclicBarrier(THREADS,barrierAction);

		// CountDownLatch 用于确认工作是否结束
		CountDownLatch downLatch = new CountDownLatch(THREADS);

		try {
			for (int i = 0; i <THREADS; i++) {
				executorService.execute(new MyTask(phaseBarrier,downLatch,i));
			}
			// 等待工作完成
			downLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
//			executorService.shutdown();
			System.out.println("END");
		}
	}
	public static class MyTask implements Runnable{
		private static final int PHASE = 5;
		private final CyclicBarrier phaseBarrier; // 3
		private final CountDownLatch doneLatch;
		private final int context;


		public MyTask(CyclicBarrier phaseBarrier, CountDownLatch doneLatch, int context) {
			this.phaseBarrier = phaseBarrier;
			this.doneLatch = doneLatch;
			this.context = context;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < PHASE; i++) {
					doPhase(i);
					phaseBarrier.await();
				}
			}catch (InterruptedException e){
				e.printStackTrace();
			}catch (BrokenBarrierException e){
				e.printStackTrace();
			}finally {
				doneLatch.countDown();
			}

		}

		protected void doPhase(int phase){
			String name = Thread.currentThread().getName();
			System.out.println(name + ":MyTask:BEGIN:context = " + context + ", phase = " +phase);
			try {
				Thread.sleep(random.nextInt(3000));
			}catch (InterruptedException e){
				e.printStackTrace();
			}finally {
				System.out.println(name + ":MyTask:END:context = " + context + ", phase = " +phase);
			}
		}
	}
}
