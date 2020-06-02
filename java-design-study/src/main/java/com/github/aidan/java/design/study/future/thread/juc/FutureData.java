package com.github.aidan.java.design.study.future.thread.juc;

import com.github.aidan.java.design.study.future.thread.Data;
import com.github.aidan.java.design.study.future.thread.ReadlData;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wang yi fei
 * @date 2020/5/19 11:32
 */
public class FutureData extends FutureTask<ReadlData> implements Data {
	public FutureData(Callable callable) {
		super(callable);
	}
	@Override
	public String getContent() {
		String string = null;
		try {
			string = get().getContent();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return string;
	}
}
