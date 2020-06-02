package com.github.aidan.java.design.study.future.thread;

/**
 * @author wang yi fei
 * @date 2020/5/15 15:00
 */
public class Host {
	public Data request(final int count,final char c){
		System.out.println("   request(" + count + " , " + c + ") BEGIN");
		//(1) 创建 FutureData
		final FutureData future = new FutureData();

		//(2) 启动一个新线程，用于创建 RealData 的 实例
		new Thread(){
			@Override
			public void run() {
				ReadlData readlData = new ReadlData(count,c);
				// TODO do something
				future.setReadlData(readlData);
			}
		}.start();
		System.out.println("   request(" + count + " , " + c + ") END");
		return future;
	}
}
