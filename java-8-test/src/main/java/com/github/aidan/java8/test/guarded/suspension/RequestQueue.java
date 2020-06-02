package com.github.aidan.java8.test.guarded.suspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wang yi fei
 * @date 2019/11/30 15:24
 */
public class RequestQueue {

	private final Queue<Request> queue = new LinkedList<>();
	public synchronized Request getRequest(){
		while (queue.peek() == null){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return queue.remove();
	}

	public synchronized void putRequest(Request request){
		queue.offer(request);
		notifyAll();
	}
}
