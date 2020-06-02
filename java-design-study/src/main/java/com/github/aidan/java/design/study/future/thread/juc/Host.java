package com.github.aidan.java.design.study.future.thread.juc;

import com.github.aidan.java.design.study.future.thread.ReadlData;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wang yi fei
 * @date 2020/5/19 11:35
 */
public class Host {

	public FutureData request(final int count, final char c){
		System.out.println("   request(" + count + " , " + c + ") BEGIN");
		//(1) 创建 FutureData
		FutureData future = new FutureData(new Callable<ReadlData>() {
			@Override
			public ReadlData call() throws Exception {
				return new ReadlData(count, c);
			}
		});
		new Thread(future).start();
		System.out.println("   request(" + count + " , " + c + ") END");

		FutureTask<Object> futureTask = new FutureTask(new Callable<ReadlData>() {
			@Override
			public ReadlData call() throws Exception {
				return new ReadlData(count, c);
			}
		});
		new Thread(futureTask).start();
		return future;
	}
}
