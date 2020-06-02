package com.github.aidan.java.design.study.future.thread;

/**
 * @author wang yi fei
 * @date 2020/5/15 16:27
 */
public class FutureData implements Data {

	private ReadlData readlData;
	private boolean ready = false;
	public synchronized void setReadlData(ReadlData readlData) {
		if (ready){
			return;
		}
		this.readlData = readlData;
		this.ready = true;
		notifyAll();
	}

	@Override
	public synchronized String getContent() {
		while (!ready){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return readlData.getContent();
	}
}
