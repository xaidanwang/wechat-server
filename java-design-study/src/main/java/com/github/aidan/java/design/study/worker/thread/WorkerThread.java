package com.github.aidan.java.design.study.worker.thread;

/**
 * @author wang yi fei
 * @date 2020/5/11 17:05
 */
public class WorkerThread extends Thread {
	private final Channel channel;

	public WorkerThread(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}
	public void run(){
		while (true){
			Request request = channel.takeRequest();
			request.execute();
		}
	}
}
