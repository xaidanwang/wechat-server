package com.github.aidan.java.design.study.future.thread.practice.content;

/**
 * @author wang yi fei
 * @date 2020/5/19 16:02
 */
public class AsyncContentImpl implements Content {
	private SyncContentImpl syncContent;
	private  boolean ready = false;

	public AsyncContentImpl(SyncContentImpl syncContent) {
		this.syncContent = syncContent;
		this.ready = true;
		notifyAll();
	}

	@Override
	public byte[] getBytes() {
		while (!ready){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return syncContent.getBytes();
	}
}
