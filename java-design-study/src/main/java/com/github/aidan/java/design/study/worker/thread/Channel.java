package com.github.aidan.java.design.study.worker.thread;

/**
 * @author wang yi fei
 * @date 2020/5/11 17:03
 */
public class Channel {

	private static final int MAX_REQUEST = 100;
	private final Request[] requests;
	private int tail;
	private int head;
	private int count;
	private final WorkerThread[] threadPool;

	public Channel(int threads) {
		this.requests = new Request[MAX_REQUEST];
		this.head = 0;
		this.tail = 0;
		this.count = 0;
		threadPool = new WorkerThread[threads];
		for (int i = 0; i < threadPool.length; i++) {
			threadPool[i] = new WorkerThread("worker-" + i,this);
		}
	}
	public void startWorkers(){
		for (int i = 0; i < threadPool.length; i++) {
			threadPool[i].start();
		}
	}

	public synchronized void putRequset(Request request){
		while (count >= requests.length){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		requests[tail] = request;
		tail = (tail + 1) % requests.length;
		count++;
		notifyAll();
	}

	public synchronized Request takeRequest(){
		while (count <= 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Request request = requests[head];
		head = (head + 1) % requests.length;
		count--;
		notifyAll();
		return request;
	}
}
