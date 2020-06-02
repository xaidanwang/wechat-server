package com.github.aidan.java8.test.producer.consumer.exchanger;

/**
 * @author wang yi fei
 * @date 2019/12/20 14:33
 */
public class Host {
	public static void execute(int count) throws InterruptedException {
		for (int i = 0;i < count;i++){
			if (Thread.interrupted()){
				throw new InterruptedException();
			}
			doHeavyJob();
		}
	}

	private static void doHeavyJob(){
		// 下面的代码用于表示 “无法取消的繁重处理”（循环处理约10s）
		System.out.println("doHeavyJon BEGIN");
		long start = System.currentTimeMillis();
		while (start + 1000 > System.currentTimeMillis()){
			//busy 10s
		}
		System.out.println("doHeaveyJon END");
	}
}
