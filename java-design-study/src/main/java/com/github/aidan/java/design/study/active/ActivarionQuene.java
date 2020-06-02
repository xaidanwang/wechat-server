package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 17:04
 */
public class ActivarionQuene {
	private static final int MAX_METHOD = 100;
	private final MethodRequest[] requestQuene;
	private int tail;
	private int head;
	private int count;

	public ActivarionQuene() {
		this.requestQuene = new MethodRequest[MAX_METHOD];
		this.tail = 0;
		this.head = 0;
		this.count = 0;
	}
	public synchronized void putRequest(MethodRequest request){
		while (count >= requestQuene.length){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		requestQuene[tail] = request;
		head = (tail + 1) % requestQuene.length;
		count++;
		notifyAll();
	}
	public synchronized MethodRequest takeRequest(){
		while (count <= 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		MethodRequest request = requestQuene[head];
		head = (head + 1) % requestQuene.length;
		count--;
		notifyAll();
		return request;
	}
}
